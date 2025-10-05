package com.example.test001_login.admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.google.android.material.textview.MaterialTextView

class ReporteDetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reporte_detalle)

        val titulo = intent.getStringExtra("titulo") ?: "Reporte"
        val tipo = intent.getStringExtra("tipo") ?: ""

        val tvTitulo = findViewById<MaterialTextView>(R.id.tvTituloReporte)
        val tvContenido = findViewById<MaterialTextView>(R.id.tvContenidoReporte)

        tvTitulo.text = titulo

        val lineas = when (tipo) {
            "SOCIOS_ACTIVOS" -> listOf(
                "Total socios: 120",
                "Activos: 98",
                "Inactivos: 22"
            )
            "CUOTAS_VENCIDAS" -> listOf(
                "Socios con deuda: 14",
                "Monto estimado: $250.000",
                "Promedio deuda: $17.800"
            )
            "INGRESOS_ACTIVIDAD" -> listOf(
                "Yoga: $120.000",
                "CrossFit: $180.000",
                "Spinning: $90.000"
            )
            "ASISTENCIAS" -> listOf(
                "Asistencias (semana): 230",
                "Clases dictadas: 42",
                "Ocupación promedio: 78%"
            )
            else -> listOf("Sin datos")
        }

        tvContenido.text = lineas.joinToString(separator = "\n")
    }
}
