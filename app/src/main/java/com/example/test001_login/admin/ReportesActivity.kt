package com.example.test001_login.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R

class ReportesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reportes)

        fun abrir(tipo: String, titulo: String) {
            val i = Intent(this, ReporteDetalleActivity::class.java)
            i.putExtra("tipo", tipo)
            i.putExtra("titulo", titulo)
            startActivity(i)
        }

        findViewById<Button>(R.id.btnRepSociosActivos)
            .setOnClickListener { abrir("SOCIOS_ACTIVOS", "Socios activos") }

        findViewById<Button>(R.id.btnRepCuotasVencidas)
            .setOnClickListener { abrir("CUOTAS_VENCIDAS", "Cuotas vencidas") }

        findViewById<Button>(R.id.btnRepIngresosActividad)
            .setOnClickListener { abrir("INGRESOS_ACTIVIDAD", "Ingresos por actividad") }

        findViewById<Button>(R.id.btnRepAsistencias)
            .setOnClickListener { abrir("ASISTENCIAS", "Asistencias por clase") }
    }
}
