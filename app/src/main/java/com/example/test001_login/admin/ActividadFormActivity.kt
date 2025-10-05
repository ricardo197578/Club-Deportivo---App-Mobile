package com.example.test001_login.admin

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.google.android.material.textfield.TextInputEditText

class ActividadFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actividad_form)

        val etNombre = findViewById<TextInputEditText>(R.id.etNombreActividad)
        val etProfesor = findViewById<TextInputEditText>(R.id.etProfesorActividad)
        val etDiaHora = findViewById<TextInputEditText>(R.id.etDiaHoraActividad)
        val etPrecio = findViewById<TextInputEditText>(R.id.etPrecioActividad)

        // Edición (si viene con extras)
        etNombre.setText(intent.getStringExtra("nombre") ?: "")
        etProfesor.setText(intent.getStringExtra("profesor") ?: "")
        etDiaHora.setText(intent.getStringExtra("diaHora") ?: "")
        etPrecio.setText(intent.getStringExtra("precio") ?: "")

        findViewById<Button>(R.id.btnGuardarActividad).setOnClickListener {
            Toast.makeText(this, "Guardado (mock)", Toast.LENGTH_SHORT).show()
            finish()
        }
        findViewById<Button>(R.id.btnCancelarActividad).setOnClickListener { finish() }
    }
}
