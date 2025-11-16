package com.example.test001_login.home

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.MainActivity
import com.example.test001_login.R
import com.example.test001_login.admin.*
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

        // ---------------------------
        // CRUD SOCIOS
        // ---------------------------
        findViewById<Button>(R.id.btnSocios).setOnClickListener {
            startActivity(Intent(this, SociosListActivity::class.java))
        }

        // ---------------------------
        // CRUD NO SOCIOS
        // ---------------------------
        findViewById<Button>(R.id.btnNoSocios).setOnClickListener {
            startActivity(Intent(this, NoSociosListActivity::class.java))
        }

        // ---------------------------
        // CRUD ACTIVIDADES
        // ---------------------------
        findViewById<Button>(R.id.btnActividades).setOnClickListener {
            startActivity(Intent(this, ActividadesListActivity::class.java))
        }

        // ---------------------------
        // PAGO DE CUOTA (SOCIOS)
        // ---------------------------
        findViewById<Button>(R.id.btnPagarCuota).setOnClickListener {
            startActivity(Intent(this, PagoCuotaActivity::class.java))
        }

        // ---------------------------
        // PASE DIARIO (NO SOCIOS)
        // ---------------------------
        findViewById<Button>(R.id.btnPaseDiario).setOnClickListener {
            startActivity(Intent(this, PaseDiarioActivity::class.java))
        }

        // ---------------------------
        // REPORTES
        // ---------------------------
        findViewById<Button>(R.id.btnReportes).setOnClickListener {
            startActivity(Intent(this, ReportesActivity::class.java))
        }

        findViewById<Button>(R.id.btnPagosDia).setOnClickListener {
            startActivity(Intent(this, PagosDelDiaActivity::class.java))
        }

        findViewById<Button>(R.id.btnCuotasVencidas).setOnClickListener {
            startActivity(Intent(this, CuotasVencidasActivity::class.java))
        }


        // ---------------------------
        // LOGOUT
        // ---------------------------
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            SessionManager.logout(this)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
