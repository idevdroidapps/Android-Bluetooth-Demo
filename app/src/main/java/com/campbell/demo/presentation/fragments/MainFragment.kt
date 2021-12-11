package com.campbell.demo.presentation.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.campbell.demo.data.services.BluetoothState
import com.campbell.demo.databinding.FragmentMainBinding
import com.campbell.demo.presentation.utils.Constants
import com.campbell.demo.presentation.viewmodels.SharedViewModel
import kotlinx.coroutines.flow.collectLatest

class MainFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        lifecycleScope.launchWhenStarted {
            bindSharedViewModel()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermissions()
    }

    private suspend fun bindSharedViewModel() {
        sharedViewModel.btAdapterState().collectLatest {
            binding.mainBluetoothIcon.text = it.javaClass.simpleName
            when (it) {
                BluetoothState.Off -> {
                    binding.mainBluetoothIcon.isEnabled = false
                }
                BluetoothState.On -> {
                    binding.mainBluetoothIcon.isEnabled = true
                }
                else -> Unit
            }
        }
    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            activity?.let {
                if (ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        it,
                        arrayOf(
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                        Constants.LOCATION_PERM_CODE
                    )
                }
            }
        }
    }

}