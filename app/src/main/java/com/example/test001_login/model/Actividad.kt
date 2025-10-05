package com.example.test001_login.model

data class Actividad(
    val id: Int,
    val nombre: String,
    val profesor: String,
    val diaHora: String,   // Ej: "Lun 19:00"
    val precio: String     // Ej: "$15.000"
)
