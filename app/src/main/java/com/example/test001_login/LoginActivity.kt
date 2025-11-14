package com.example.test001_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test001_login.home.HomeAdminActivity
import com.example.test001_login.home.HomeNoSocioActivity
import com.example.test001_login.home.HomeProfesorActivity
import com.example.test001_login.home.HomeSocioActivity
import com.example.test001_login.model.UserRole
import com.example.test001_login.service.LoginService
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    private lateinit var loginService: LoginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.titleDashboard)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Servicio con contexto (para SQLite)
        loginService = LoginService(this)

        val btnBackMain = findViewById<ImageButton>(R.id.btnBack)
        val btnInicarSesion = findViewById<Button>(R.id.btnLogin)
        val btnIrARegistro = findViewById<Button>(R.id.btnIrARegistro) // si existe en el XML

        btnIrARegistro?.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnInicarSesion.setOnClickListener {
            val username = findViewById<TextInputEditText>(R.id.etUsuario).text.toString().trim()
            val password = findViewById<TextInputEditText>(R.id.etPassword).text.toString().trim()

            val user = loginService.authenticate(username, password)

            if (user != null) {
                // Guardar sesión
                getSharedPreferences("mySession", MODE_PRIVATE).edit().apply {
                    putString("username", user.username)
                    putString("role", user.role.name)
                    putBoolean("isLoggedIn", true)
                    apply()
                }

                // Navegar según rol
                when (user.role) {
                    UserRole.ADMIN     -> startActivity(Intent(this, HomeAdminActivity::class.java))
                    UserRole.SOCIO     -> startActivity(Intent(this, HomeSocioActivity::class.java))
                    UserRole.NO_SOCIO  -> startActivity(Intent(this, HomeNoSocioActivity::class.java))
                    UserRole.PROFESOR  -> startActivity(Intent(this, HomeProfesorActivity::class.java))
                }
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña inválidos", Toast.LENGTH_SHORT).show()
            }
        }

        btnBackMain.setOnClickListener { finish() }
    }
}


