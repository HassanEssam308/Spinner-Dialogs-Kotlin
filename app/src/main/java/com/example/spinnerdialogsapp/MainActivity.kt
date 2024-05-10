package com.example.spinnerdialogsapp

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var selectTypeOfDialog :Spinner




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        selectTypeOfDialog=findViewById(R.id.Main_Spinner_SelectTypeOfDialog)

       val spinnerAdapter=ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.optionOfDialog)

        )

        selectTypeOfDialog.adapter=spinnerAdapter


        selectTypeOfDialog.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val selectedValue=parent?.getItemAtPosition(position)
                when(selectedValue){
                    "Alert Dialog" -> alertDialog()
                    "Custom Dialog" -> customDialog()

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


    }

    private fun alertDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Hi Developer")
        builder.setMessage("Are You Mobile Developer ?")

        builder.setPositiveButton("Yes") { dialog, which ->
            Toast.makeText(this, " ok ,Mobile Developer ", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(this, " No Mobile Developer ", Toast.LENGTH_SHORT).show()
        }
        builder.setNeutralButton("Ask My Later") { dialog, which ->
            Toast.makeText(this, " Ask My Later ", Toast.LENGTH_SHORT).show()
        }
        builder.create().show()
    }

    private fun customDialog() {

            val view = layoutInflater.inflate(R.layout.custom_dialog, null)
            val builder = AlertDialog.Builder(this)
            builder.setView(view)
            val customDialog = builder.create()
        customDialog.show()
        customDialog.setCancelable(false)

            val et_feedBack = view.findViewById<TextInputEditText>(R.id.CustomLayout_Et_feedBack)
            val btn_save = customDialog.findViewById<Button>(R.id.CustomLayout_Btn_Save)

            btn_save?.setOnClickListener {
                customDialog.dismiss()
                Toast.makeText(this, et_feedBack.text, Toast.LENGTH_SHORT).show()
            }
        }
}