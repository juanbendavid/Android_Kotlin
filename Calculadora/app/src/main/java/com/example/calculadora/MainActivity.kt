package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    var tvRes:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvRes = findViewById(R.id.bigual)


    }
    fun calcular(view:View){

    }
}

