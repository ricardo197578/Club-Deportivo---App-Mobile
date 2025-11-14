package com.example.test001_login.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.MainActivity
import com.example.test001_login.R
import com.example.test001_login.model.UserRole
import com.example.test001_login.service.RoleGuard
import com.example.test001_login.service.SessionManager

class HomeNoSocioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val ok = RoleGuard.require(this, setOf(UserRole.NO_SOCIO))
        if (!ok) return

        setContentView(R.layout.activity_home_no_socio)

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            SessionManager.logout(this)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
