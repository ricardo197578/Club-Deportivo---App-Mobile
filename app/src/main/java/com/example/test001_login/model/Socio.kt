package com.example.test001_login.model

data class Socio(
    val id: Int,
    val nombre: String,
    val dni: String,
    val estado: String // "Activo" / "Inactivo" / "Vencido"
)
