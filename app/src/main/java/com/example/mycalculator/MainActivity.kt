package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var firstOperand = 0.0
    private var operator = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

    }


    fun clickNumber(clickedView: View) {
        if (clickedView is TextView) {
            if ((tvResult.text.toString() != "Infinity") and (tvResult.text.toString() != "NaN")) {
                var currentText = tvResult.text.toString()
                val addedNumber = clickedView.text.toString()

                if (currentText == "0") {
                    currentText = ""
                }

                val newNumber = currentText + addedNumber

                tvResult.text = newNumber
            }
        }
    }

    fun clickOperator(clickedView: View) {
        if (clickedView is TextView){
            if (tvResult.text.toString() != "") {
                firstOperand = tvResult.text.toString().toDouble()
                tvResult.text = ""
                operator = clickedView.text.toString()
            }

        }

    }

    fun clickEquals(clickedView: View) {
        if (clickedView is TextView){
            if (tvResult.text.toString() != "") {
                var finalResult = 0.0
                val secondOperand = tvResult.text.toString().toDouble()
                when(operator){
                    "+" -> finalResult = firstOperand + secondOperand
                    "÷" -> finalResult = firstOperand / secondOperand
                    "×" -> finalResult = firstOperand * secondOperand
                    "-" -> finalResult = firstOperand - secondOperand
                }
                if (finalResult.toString().slice(finalResult.toString().length - 2 until finalResult.toString().length) == ".0"){
                    tvResult.text = finalResult.toString().slice(0 .. finalResult.toString().length - 3)
                }
                else { tvResult.text = finalResult.toString() }

                operator = ""
                firstOperand = 0.0
            }
        }
    }

    fun clickClear(clickedView: View) {
        if (clickedView is TextView) {
            operator = ""
            firstOperand = 0.0
            tvResult.text = "0"
        }
    }

    fun clickBackspace(clickedView: View) {
        if (clickedView is TextView) {
            if ((tvResult.text.toString() != "Infinity") and (tvResult.text.toString() != "NaN")) {
                if (tvResult.text.toString() != "0") {
                    tvResult.text = tvResult.text.toString().dropLast(1)
                }
                if (tvResult.text.toString() == "") {
                    tvResult.text = "0"
                }
            }
        }
    }

    fun clickDecimal(clickedView: View) {
        if ((tvResult.text.toString() != "Infinity") and (tvResult.text.toString() != "NaN")) {
            if (clickedView is TextView) {
                if (tvResult.text.toString() == "") {
                    tvResult.text = "0."
                } else if ("." !in tvResult.text.toString()) {
                    val tempText = tvResult.text.toString() + "."
                    tvResult.text = tempText
                }
            }
        }
    }
}