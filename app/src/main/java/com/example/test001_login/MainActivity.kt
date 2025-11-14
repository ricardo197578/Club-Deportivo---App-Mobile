package com.example.test001_login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.home.HomeAdminActivity
import com.example.test001_login.home.HomeNoSocioActivity
import com.example.test001_login.home.HomeProfesorActivity
import com.example.test001_login.home.HomeSocioActivity
import com.example.test001_login.model.UserRole

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // ¿Hay sesión guardada? -> ir directo al Home según rol
        val prefs = getSharedPreferences("mySession", MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            val roleStr = prefs.getString("role", UserRole.SOCIO.name)!!
            when (UserRole.valueOf(roleStr)) {
                UserRole.ADMIN     -> startActivity(Intent(this, HomeAdminActivity::class.java))
                UserRole.SOCIO     -> startActivity(Intent(this, HomeSocioActivity::class.java))
                UserRole.NO_SOCIO  -> startActivity(Intent(this, HomeNoSocioActivity::class.java))
                UserRole.PROFESOR  -> startActivity(Intent(this, HomeProfesorActivity::class.java))
            }
            finish()
            return
        }

        // Sin sesión -> mostrar botón que lleva a Login
        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
}
