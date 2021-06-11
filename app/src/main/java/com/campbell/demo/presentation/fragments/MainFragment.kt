package com.campbell.demo.presentation.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.campbell.demo.R
import com.campbell.demo.data.services.BluetoothState
import com.campbell.demo.presentation.utils.Constants
import com.campbell.demo.presentation.viewmodels.SharedViewModel
import kotlinx.coroutines.flow.collectLatest

class MainFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val adapterView = (view.findViewById<TextView>(R.id.adapter_view))
        lifecycleScope.launchWhenStarted {
            checkPermissions()
            sharedViewModel.btAdapterState().collectLatest {
                adapterView.text = it.javaClass.simpleName
                when (it) {
                    BluetoothState.Off -> {
                        adapterView.isEnabled = false
                    }
                    BluetoothState.On -> {
                        adapterView.isEnabled = true
                    }
                    else -> Unit
                }
            }
        }

        return view
    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity?.let {
                if (ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.ACCESS_BACKGROUND_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                    ||
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        it,
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION
                        ),
                        Constants.LOCATION_PERM_CODE
                    )
                }
            }

        }
    }

}