package com.example.test001_login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import com.example.test001_login.service.LoginService
import com.google.android.material.textfield.TextInputEditText
import android.widget.Button
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private val loginService = LoginService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.titleDashboard)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var btnBackMain = findViewById<ImageButton>(R.id.btnBack);
        var btnInicarSesion = findViewById<Button>(R.id.btnLogin);

        val btnIrARegistro = findViewById<Button>(R.id.btnIrARegistro)
        btnIrARegistro.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }



        btnInicarSesion.setOnClickListener {
            val username = findViewById<TextInputEditText>(R.id.etUsuario).text.toString().trim()
            val password = findViewById<TextInputEditText>(R.id.etPassword).text.toString().trim()
            val msg = loginService.registrarse(username, password)
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            if (msg == "Inicio exitoso!") {
                val prefs = getSharedPreferences("mySession", MODE_PRIVATE);
                val editor = prefs.edit();

                editor.putString("username", username)
                editor.putBoolean("isLoggedIn", true)
                editor.apply()

                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            } else {
                finish()
            }
        }

        btnBackMain.setOnClickListener {
            finish()
        }
    }
}
