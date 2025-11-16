package com.example.test001_login.admin

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.test001_login.R
import com.example.test001_login.data.PaseDiarioDao
import com.example.test001_login.data.PaymentDao
import java.text.SimpleDateFormat
import java.util.*

class PagosDelDiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagos_del_dia)

        // Referencias a los TextViews
        val tvSocios = findViewById<TextView>(R.id.tvPagosSocios)
        val tvNoSocios = findViewById<TextView>(R.id.tvPagosNoSocios)

        // DAOs
        val pagoDao = PaymentDao(this)
        val paseDao = PaseDiarioDao(this)

        // Fecha actual
        val fechaHoy = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        // Consultas
        val pagosSocios = pagoDao.listarPagosDelDia(fechaHoy)
        val pasesNoSocios = paseDao.listarPasesDelDia(fechaHoy)

        // --- Mostrar pagos de socios ---
        if (pagosSocios.isEmpty()) {
            tvSocios.text = "Pagos socios: (ninguno)"
        } else {
            val texto = StringBuilder("Pagos socios:\n\n")
            pagosSocios.forEach {
                texto.append("DNI: ${it.socioDni}\n")
                texto.append("Monto: \$${it.monto}\n")
                texto.append("Fecha: ${it.fechaPago}\n")
                texto.append("----------------------\n")
            }
            tvSocios.text = texto.toString()
        }

        // --- Mostrar pagos NO socios ---
        if (pasesNoSocios.isEmpty()) {
            tvNoSocios.text = "Pagos no socios: (ninguno)"
        } else {
            val texto = StringBuilder("Pagos no socios:\n\n")
            pasesNoSocios.forEach {
                texto.append("DNI: ${it.dni}\n")
                texto.append("Monto: \$${it.monto}\n")
                texto.append("Fecha: ${it.fecha}\n")
                texto.append("----------------------\n")
            }
            tvNoSocios.text = texto.toString()
        }
    }
}
