// app/src/main/java/com/example/test001_login/admin/SocioFormActivity.kt
package com.example.test001_login.admin

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.google.android.material.textfield.TextInputEditText
import android.widget.AutoCompleteTextView
import android.widget.Toast

class SocioFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_socio_form)

        val etNombre = findViewById<TextInputEditText>(R.id.etNombre)
        val etDni    = findViewById<TextInputEditText>(R.id.etDni)
        val ddEstado = findViewById<AutoCompleteTextView>(R.id.ddEstado)

        // Relleno si viene desde la lista (modo edición - mock)
        etNombre.setText(intent.getStringExtra("socioNombre") ?: "")
        etDni.setText(intent.getStringExtra("socioDni") ?: "")
        ddEstado.setText(intent.getStringExtra("socioEstado") ?: "", false)

        // Dropdown de estado (mock)
        val estados = listOf("Activo", "Inactivo", "Vencido")
        ddEstado.setAdapter(ArrayAdapter(this, android.R.layout.simple_list_item_1, estados))

        findViewById<Button>(R.id.btnGuardar).setOnClickListener {
            Toast.makeText(this, "Guardado (mock)", Toast.LENGTH_SHORT).show()
            finish()
        }

        findViewById<Button>(R.id.btnCancelar).setOnClickListener { finish() }
    }
}
