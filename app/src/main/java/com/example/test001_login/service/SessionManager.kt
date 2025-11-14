package com.example.test001_login.service

import android.content.Context
import com.example.test001_login.model.User
import com.example.test001_login.model.UserRole

object SessionManager {
    private const val PREFS = "mySession"
    private const val K_LOGGED = "isLoggedIn"
    private const val K_USERNAME = "username"
    private const val K_ROLE = "role"

    fun saveLogin(context: Context, user: User) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean(K_LOGGED, true)
            .putString(K_USERNAME, user.username)
            .putString(K_ROLE, user.role.name)
            .apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        return prefs.getBoolean(K_LOGGED, false)
    }

    fun role(context: Context): UserRole? {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val r = prefs.getString(K_ROLE, null) ?: return null
        return runCatching { UserRole.valueOf(r) }.getOrNull()
    }

    fun logout(context: Context) {
        val prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit().clear().apply()
    }
}
