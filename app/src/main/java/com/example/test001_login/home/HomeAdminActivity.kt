// app/src/main/java/com/example/test001_login/home/HomeAdminActivity.kt
package com.example.test001_login.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.MainActivity
import com.example.test001_login.R
import com.example.test001_login.admin.ActividadesListActivity
import com.example.test001_login.admin.SociosListActivity
import com.example.test001_login.admin.NoSociosListActivity
import com.example.test001_login.admin.ProfesoresListActivity
import com.example.test001_login.admin.ReportesActivity
import com.example.test001_login.model.UserRole
import com.example.test001_login.service.RoleGuard
import com.example.test001_login.service.SessionManager

class HomeAdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val ok = RoleGuard.require(this, setOf(UserRole.ADMIN))
        if (!ok) return

        setContentView(R.layout.activity_home_admin)

        //SOCIOS
        findViewById<Button>(R.id.btnSocios).setOnClickListener {
            startActivity(Intent(this, SociosListActivity::class.java))
        }
        // NO SOCIOS
        findViewById<Button>(R.id.btnNoSocios).setOnClickListener {
            startActivity(Intent(this, NoSociosListActivity::class.java))
        }

        // Actividades
        findViewById<Button>(R.id.btnActividades).setOnClickListener {
             startActivity(Intent(this, ActividadesListActivity::class.java))
        }
        //PROFESORES
        findViewById<Button>(R.id.btnProfesores).setOnClickListener {
             startActivity(Intent(this, ProfesoresListActivity::class.java))
         }

        //REPORTES
        findViewById<Button>(R.id.btnReportes).setOnClickListener {
            startActivity(Intent(this@HomeAdminActivity, ReportesActivity::class.java))
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            SessionManager.logout(this)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }
}
