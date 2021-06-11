package com.campbell.demo.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.campbell.demo.R
import com.campbell.demo.presentation.dependency_injection.Injector
import com.campbell.demo.presentation.viewmodels.SharedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = ViewModelProvider(
            this@MainActivity,
            Injector.provideSharedViewModelFactory(this@MainActivity)
        ).get(SharedViewModel::class.java)

        setContentView(R.layout.activity_main)
    }
}