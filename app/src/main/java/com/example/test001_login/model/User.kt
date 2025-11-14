package com.example.test001_login.model

/**
 * Modelo de usuario del sistema de login.
 * Para demo inicial, la contraseña se guarda en texto plano.
 * En producción debe almacenarse un HASH (p.ej. BCrypt).
 */
data class User(
    val id: Long? = null,
    val username: String,
    val password: String,
    val role: UserRole,
    val isActive: Boolean = true
)
