package com.uth_diseno.convertidor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var temperature: String = ""
    private var selectedOption: Int = 0
    private var conversion: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val result = findViewById<TextView>(R.id.textView2)
        val option = findViewById<Spinner>(R.id.spinner)
        val temp = findViewById<EditText>(R.id.editTextNumberDecimal)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {

            temperature = temp.text.toString()

            result.setText("La conversión es: " + conversion(selectedOption).toString())

        }

        if(option != null) {

            val adapter = ArrayAdapter(

                this,
                android.R.layout.simple_spinner_item,
                resources.getStringArray(R.array.covert_options)

            )

            option.adapter = adapter

            option.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    selectedOption = position
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }

    private fun conversion(option: Int): Double {
        when(option) {
            0 -> {
                //Traer las formulas de física para convertor las temperaturas

                //<item>°C -> °F</item>
                // °F = 1.8°C + 32
                val grados = Integer.parseInt(temperature)
                conversion = grados * 1.8 + 32
            }
            1 -> {
                //<item>°C -> °K</item>
                // °K = °C + 273
                val grados = Integer.parseInt(temperature)
                conversion = grados + 273.0
            }
            2 -> {
                //<item>°F -> °C</item>
                // 5/9(°F - 32)
                val grados = Integer.parseInt(temperature)
                conversion = (grados - 32.0) / (5/9)
            }
            3 -> {
                //<item>°F -> °K</item>
                val grados = Integer.parseInt(temperature)
                conversion = ((grados - 32.0) / (1.8)) + 273.0
            }
            4 -> {
                //<item>°K -> °C</item>
                val grados = Integer.parseInt(temperature)
                conversion = grados - 273.0
            }
            5 -> {
                //<item>°K -> °F</item>
                val grados = Integer.parseInt(temperature)
                conversion = (grados - 273) * 1.8 + 32
            }

        }
        return conversion.toDouble()
    }
}