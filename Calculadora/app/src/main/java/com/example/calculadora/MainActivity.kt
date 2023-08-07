package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    var tvRes:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvRes = findViewById(R.id.resultado)


    }
    fun calcular(view:View){
        var boton = view as Button
        var textoBoton = boton.text.toString()
        var concatenar = tvRes?.text.toString()+textoBoton
        tvRes?.text = concatenar
    }
    fun quitarCerosIzquierda(){
        //falta por realizar
    }
}

