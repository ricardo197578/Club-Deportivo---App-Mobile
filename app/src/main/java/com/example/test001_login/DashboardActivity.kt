package com.example.test001_login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test001_login.home.HomeAdminActivity
import com.example.test001_login.home.HomeNoSocioActivity
import com.example.test001_login.home.HomeProfesorActivity
import com.example.test001_login.home.HomeSocioActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_dashboard)

        // Insets para que el título no quede debajo de la status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.titleDashboard)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Estilo barra de estado transparente + iconos oscuros
        val colorTransparente = ContextCompat.getColor(this, android.R.color.transparent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            window.statusBarColor = colorTransparente
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            @Suppress("DEPRECATION")
            window.statusBarColor = colorTransparente
        }

        // === Navegación a cada Home ===
        findViewById<Button>(R.id.btnRolAdmin).setOnClickListener {
            startActivity(Intent(this, HomeAdminActivity::class.java))
        }
        findViewById<Button>(R.id.btnRolSocio).setOnClickListener {
            startActivity(Intent(this, HomeSocioActivity::class.java))
        }
        findViewById<Button>(R.id.btnRolNoSocio).setOnClickListener {
            startActivity(Intent(this, HomeNoSocioActivity::class.java))
        }
        findViewById<Button>(R.id.btnRolProfesor).setOnClickListener {
            startActivity(Intent(this, HomeProfesorActivity::class.java))
        }
    }
}
