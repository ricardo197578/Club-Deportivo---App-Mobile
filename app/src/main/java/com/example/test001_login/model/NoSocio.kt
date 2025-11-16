package com.example.test001_login.model

data class NoSocio(
    val id: Int = 0,
    val dni: String,
    val nombre: String,
    val apellido: String = "",
    val telefono: String?,
    val fechaRegistro: String    // yyyy-MM-dd
)

