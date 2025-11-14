package com.example.test001_login.service

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.test001_login.MainActivity
import com.example.test001_login.model.UserRole

/**
 * Guardia de rol simple: valida sesión y rol permitido.
 * Si no cumple, redirige al inicio (MainActivity) y cierra la Activity actual.
 */
object RoleGuard {
    fun require(context: Context, allowed: Set<UserRole>): Boolean {
        val logged = SessionManager.isLoggedIn(context)
        val role = SessionManager.role(context)

        if (!logged || role == null || role !in allowed) {
            context.startActivity(Intent(context, MainActivity::class.java))
            if (context is Activity) context.finish()
            return false
        }
        return true
    }
}
