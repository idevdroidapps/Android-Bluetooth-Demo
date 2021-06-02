package com.campbell.demo.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.campbell.demo.R
import com.campbell.demo.presentation.dependency_injection.Injector
import com.campbell.demo.presentation.viewmodels.SharedViewModel

class MainFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.let {
            sharedViewModel = ViewModelProvider(
                it,
                Injector.provideSharedViewModelFactory()
            ).get(SharedViewModel::class.java)
        }

        val view = inflater.inflate(R.layout.fragment_main, container, false)
        view.findViewById<TextView>(R.id.textView).text = sharedViewModel.getData().id
        return view
    }

}