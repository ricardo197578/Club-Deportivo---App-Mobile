package com.example.test001_login.admin

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.google.android.material.textfield.TextInputEditText

class ProfesorFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profesor_form)

        val etNombre = findViewById<TextInputEditText>(R.id.etNombreProfesor)
        val etEspecialidad = findViewById<TextInputEditText>(R.id.etEspecialidadProfesor)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmailProfesor)
        val etTel = findViewById<TextInputEditText>(R.id.etTelProfesor)

        // Edición (si viene con extras)
        etNombre.setText(intent.getStringExtra("nombre") ?: "")
        etEspecialidad.setText(intent.getStringExtra("especialidad") ?: "")
        etEmail.setText(intent.getStringExtra("email") ?: "")
        etTel.setText(intent.getStringExtra("telefono") ?: "")

        findViewById<Button>(R.id.btnGuardarProfesor).setOnClickListener {
            Toast.makeText(this, "Guardado (mock)", Toast.LENGTH_SHORT).show()
            finish()
        }
        findViewById<Button>(R.id.btnCancelarProfesor).setOnClickListener { finish() }
    }
}
