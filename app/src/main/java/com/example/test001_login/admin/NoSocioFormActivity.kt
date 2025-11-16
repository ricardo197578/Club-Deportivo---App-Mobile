package com.example.test001_login.admin

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.example.test001_login.data.NoSocioRepository
import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDate

class NoSocioFormActivity : AppCompatActivity() {

    private lateinit var repo: NoSocioRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_no_socio_form)

        repo = NoSocioRepository(this)

        val etNombre = findViewById<TextInputEditText>(R.id.etNombreNoSocio)
        val etDni = findViewById<TextInputEditText>(R.id.etDniNoSocio)
        val etTel = findViewById<TextInputEditText>(R.id.etTelNoSocio)

        findViewById<Button>(R.id.btnGuardarNoSocio).setOnClickListener {
            val nombre = etNombre.text.toString()
            val dni = etDni.text.toString()
            val tel = etTel.text.toString()

            if (nombre.isEmpty() || dni.isEmpty()) {
                Toast.makeText(this, "Completar campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val ok = repo.insertNoSocio(
                dni = dni,
                nombre = nombre,
                telefono = tel,
                fecha = LocalDate.now().toString()
            )

            if (ok) {
                Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnCancelarNoSocio).setOnClickListener { finish() }
    }
}
