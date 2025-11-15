package com.example.test001_login.admin

import android.os.Bundle
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

        val tvSocios = findViewById<TextView>(R.id.tvPagosSocios)
        val tvNoSocios = findViewById<TextView>(R.id.tvPagosNoSocios)

        val hoy = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        val pagosSocios = PaymentDao(this).listarPagosDelDia(hoy)
        val pagosNoSocios = PaseDiarioDao(this).listarPasesDelDia(hoy)

        tvSocios.text = "Pagos socios:\n" +
                pagosSocios.joinToString("\n") { "${it.socioDni} - $${it.monto}" }

        tvNoSocios.text = "Pagos no socios:\n" +
                pagosNoSocios.joinToString("\n") { "${it.dni} - $${it.monto}" }
    }
}
