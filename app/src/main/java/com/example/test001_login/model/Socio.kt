package com.example.test001_login.model

data class Socio(
    val id: Long,
    val dni: String,
    val nombre: String,
    val apellido: String,
    val telefono: String,
    val direccion: String,
    val fechaAlta: String?,
    val fechaUltimoPago: String?,
    val activo: Int
)
