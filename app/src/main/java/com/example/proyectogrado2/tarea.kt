package com.example.proyectogrado2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.proyectogrado2.databinding.ActivityTareaBinding
import java.text.SimpleDateFormat
import java.util.*

class tarea : AppCompatActivity() {

    lateinit var binding: ActivityTareaBinding
    private lateinit var item: String
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTareaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        /*----LLENA SPINNER CON LAS OPCIONES DE LAS GATEGORÍAS----*/
        spinner()

        /*----ACTION DEL ICONO DE RETURNS----*/
        binding.iconReturns.setOnClickListener {
            intent = Intent (this, MainActivity::class.java)
            startActivity (intent)
            finish()
        }

        /*----ACTION DEL ICONO DE AGENDA PARA SELLECCIONAR LA FECHA----*/
        binding.clickFecha.setOnClickListener { clickFecha() }
        binding.clickHora.setOnClickListener { clickHora() }

        /*----ACTION DEL BOTÓN GUARDAR----*/
        binding.btnGuardar.setOnClickListener { save() }
    }

    fun spinner(){
        var list_categorias = listOf("Reunion", "Salud", "Trabajo", "Ejercicio", "Cumpleaños")
        val adapterA: ArrayAdapter<String> = ArrayAdapter<String>(this,  R.layout.color_spinner, list_categorias)
        adapterA.setDropDownViewResource(R.layout.spinner_dropdown_item)
        binding.spinnerCategorias.setAdapter(adapterA)
        binding.spinnerCategorias.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                item = parent.getItemAtPosition(pos) as String
                Log.e("SPINNER CATEGORIAS", "onCreate: ${item}")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    fun save(){
            val admin = AdminSQLiteOpenHelper(this, "bd_tareas", null, 1)
            val bd = admin.writableDatabase
            val registros = ContentValues()
            registros.put("titulo", binding.edtTarea.getText().toString())
            registros.put("descripcion", binding.edtDescripcion.getText().toString())
            registros.put("categoria", item)
            registros.put("fecha", binding.fecha.getText().toString())
            registros.put("hora", binding.hora.getText().toString())
            bd.insert("tareas", null, registros)
            Toast.makeText(this, "Guardado Exitosamente!!", Toast.LENGTH_SHORT).show()
    }

    /*----DatePicker----*/
    fun clickFecha(){
        val DialogFecha = DatePickerFragment{year, month, day -> MostarFecha(year, month, day) }
        DialogFecha.show(supportFragmentManager, "DatePicker")
    }

    private fun MostarFecha(year: Int, month: Int, day: Int) {
        binding.fecha.setText("$day/$month/$year")
    }

    private fun clickHora() {
      val timePicker = TimePickerFragment{onSelectedtime(it)}
        timePicker.show(supportFragmentManager, "TimePicker")
    }

    private fun onSelectedtime(time: String) {
        binding.hora.setText("$time")
    }


}