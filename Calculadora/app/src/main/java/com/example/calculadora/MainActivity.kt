package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    var tvRes:TextView?=null
    var btnReset:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvRes = findViewById(R.id.resultado)
        btnReset = findViewById(R.id.bReset)



    }
    fun calcular(view:View){
        var boton = view as Button
        var textoBoton = boton.text.toString()
        var concatenar = tvRes?.text.toString()+textoBoton


        tvRes?.text = quitarCerosIzquierda(concatenar)

    }


    fun quitarCerosIzquierda(str:String):String{
        var i=0
        while (i<str.length && str[i]=='0')i++
        val sb = StringBuffer(str)
        sb.replace(0,i,"")
        return sb.toString()
    }
}

