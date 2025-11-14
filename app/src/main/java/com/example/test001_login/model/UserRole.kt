package com.example.test001_login.model

/**
 * Roles admitidos en la app.
 * - ADMIN: acceso total (panel Admin, CRUDs, reportes).
 * - SOCIO: acceso a su Home de socio.
 * - NO_SOCIO: acceso a su Home de no socio.
 * - PROFESOR: acceso a su Home de profesor.
 */
enum class UserRole {
    ADMIN,
    SOCIO,
    NO_SOCIO,
    PROFESOR
}
