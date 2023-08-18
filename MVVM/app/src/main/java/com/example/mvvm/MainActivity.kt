package com.example.mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.ui.theme.MVVMTheme

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

