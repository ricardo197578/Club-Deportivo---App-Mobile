package com.example.test001_login.model

data class Payment(
    val id: Long? = null,
    val socioDni: String,
    val fechaPago: String,   // yyyy-MM-dd
    val monto: Double
)
