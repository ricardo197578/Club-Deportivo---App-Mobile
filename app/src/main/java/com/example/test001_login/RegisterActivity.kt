package com.example.test001_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        val etNombre   = findViewById<TextInputEditText>(R.id.etNombre)
        val etEmail    = findViewById<TextInputEditText>(R.id.etEmail)
        val etUsuario  = findViewById<TextInputEditText>(R.id.etUsuario)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)

        findViewById<Button>(R.id.btnRegistrar).setOnClickListener {
            // Solo UI (mock)
            Toast.makeText(this, "Registro enviado (mock)", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.btnIrLogin).setOnClickListener { finish() }
    }
}
