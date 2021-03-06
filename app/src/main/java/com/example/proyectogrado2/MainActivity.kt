package com.example.proyectogrado2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyectogrado2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tarea.setOnClickListener {
            intent = Intent (this, tarea::class.java)
            startActivity (intent)
        }
        binding.perfil.setOnClickListener {
            intent = Intent (this, perfil::class.java)
            startActivity (intent)
        }
        binding.info.setOnClickListener {
            intent = Intent (this, information::class.java)
            startActivity (intent)
        }

    }
}