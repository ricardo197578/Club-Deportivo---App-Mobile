package com.example.test001_login.admin

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.google.android.material.textfield.TextInputEditText

class NoSocioFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_no_socio_form)

        val etNombre = findViewById<TextInputEditText>(R.id.etNombreNoSocio)
        val etDni = findViewById<TextInputEditText>(R.id.etDniNoSocio)
        val etTel = findViewById<TextInputEditText>(R.id.etTelNoSocio)

        // Si viene de la lista, precargo datos (edición)
        etNombre.setText(intent.getStringExtra("nombre") ?: "")
        etDni.setText(intent.getStringExtra("dni") ?: "")
        etTel.setText(intent.getStringExtra("telefono") ?: "")

        findViewById<Button>(R.id.btnGuardarNoSocio).setOnClickListener {
            Toast.makeText(this, "Guardado (mock)", Toast.LENGTH_SHORT).show()
            finish()
        }

        findViewById<Button>(R.id.btnCancelarNoSocio).setOnClickListener { finish() }
    }
}
