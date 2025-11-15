package com.example.test001_login.model

data class PaseDiario(
    val id: Long? = null,
    val dni: String,
    val fecha: String,        // yyyy-MM-dd
    val monto: Double
)
