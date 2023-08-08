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
        // concatenamos los numeros con los operadores
        var concatenar = tvRes?.text.toString()+textoBoton


        if (textoBoton.equals("=")){
            var resultado = 0.0
            try {
                resultado = eval(tvRes?.text.toString())
                tvRes?.text = resultado.toString()
            }catch (e:Exception){
                tvRes?.text = e.toString()
            }
        }else if (textoBoton.equals("RESET")){
            tvRes?.text ="0"
        }else{
            // calcula la expresion matematica sin antes  sustraer   los ceros a la izquierda
            tvRes?.text = quitarCerosIzquierda(concatenar)
        }



    }


    fun quitarCerosIzquierda(str:String):String{
        var i=0
        while (i<str.length && str[i]=='0')i++
        val sb = StringBuffer(str)
        sb.replace(0,i,"")
        return sb.toString()
    }

    //funcion eval para calcular una expresion matematica
    fun eval(str: String): Double {
        return object : Any() {
            var pos = -1
            var ch = 0
            fun nextChar() {
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            fun eat(charToEat: Int): Boolean {
                while (ch == ' '.toInt()) nextChar()
                if (ch == charToEat) {
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double {
                nextChar()
                val x = parseExpression()
                if (pos < str.length) throw RuntimeException("Unexpected: " + ch.toChar())
                return x
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor
            fun parseExpression(): Double {
                var x = parseTerm()
                while (true) {
                    if (eat('+'.code)) x += parseTerm() // addition
                    else if (eat('-'.toInt())) x -= parseTerm() // subtraction
                    else return x
                }
            }

            fun parseTerm(): Double {
                var x = parseFactor()
                while (true) {
                    if (eat('*'.toInt())) x *= parseFactor() // multiplication
                    else if (eat('/'.toInt())) x /= parseFactor() // division
                    else return x
                }
            }

            fun parseFactor(): Double {
                if (eat('+'.toInt())) return parseFactor() // unary plus
                if (eat('-'.toInt())) return -parseFactor() // unary minus
                var x: Double
                val startPos = pos
                if (eat('('.toInt())) { // parentheses
                    x = parseExpression()
                    eat(')'.toInt())
                } else if (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) { // numbers
                    while (ch >= '0'.toInt() && ch <= '9'.toInt() || ch == '.'.toInt()) nextChar()
                    x = str.substring(startPos, pos).toDouble()
                } else if (ch >= 'a'.toInt() && ch <= 'z'.toInt()) { // functions
                    while (ch >= 'a'.toInt() && ch <= 'z'.toInt()) nextChar()
                    val func = str.substring(startPos, pos)
                    x = parseFactor()
                    x = if (func == "sqrt") Math.sqrt(x) else if (func == "sin") Math.sin(
                        Math.toRadians(x)
                    ) else if (func == "cos") Math.cos(Math.toRadians(x)) else if (func == "tan") Math.tan(
                        Math.toRadians(x)
                    ) else throw RuntimeException("Unknown function: $func")
                } else {
                    throw RuntimeException("Unexpected: " + ch.toChar())
                }
                if (eat('^'.toInt())) x = Math.pow(x, parseFactor()) // exponentiation
                return x
            }
        }.parse()

    }
}

