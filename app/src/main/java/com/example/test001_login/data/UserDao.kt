package com.example.test001_login.data

import android.content.ContentValues
import android.content.Context
import com.example.test001_login.model.User
import com.example.test001_login.model.UserRole

/**
 * DAO de acceso a la tabla `users`.
 * NOTA: Para la demo guardamos password en texto plano.
 */
class UserDao(context: Context) {

    private val dbHelper = AppDbHelper(context)
    private val rdb = dbHelper.readableDatabase
    private val wdb = dbHelper.writableDatabase

    fun userExists(username: String): Boolean {
        rdb.rawQuery(
            "SELECT 1 FROM users WHERE username = ? LIMIT 1",
            arrayOf(username)
        ).use { c -> return c.moveToFirst() }
    }

    fun createUser(user: User): Long {
        val cv = ContentValues().apply {
            put("username", user.username)
            put("password", user.password)
            put("role", user.role.name)
            put("is_active", if (user.isActive) 1 else 0)
        }
        return wdb.insert("users", null, cv)
    }

    /**
     * Crea un usuario por defecto (user = dni, pass = dni) si no existía.
     * Devuelve true si lo creó; false si ya existía.
     */
    fun createDefaultIfMissing(dni: String, role: UserRole): Boolean {
        if (userExists(dni)) return false
        val u = User(username = dni, password = dni, role = role, isActive = true)
        return createUser(u) > 0
    }

    /**
     * Autenticación simple para la demo.
     * Retorna el User si coincide username/password y está activo; si no, null.
     */
    fun getByCredentials(username: String, password: String): User? {
        val sql = """
            SELECT id, username, password, role, is_active
            FROM users
            WHERE username = ? AND password = ?
            LIMIT 1
        """.trimIndent()

        rdb.rawQuery(sql, arrayOf(username, password)).use { c ->
            if (c.moveToFirst()) {
                val active = c.getInt(4) == 1
                if (!active) return null
                return User(
                    id = c.getLong(0),
                    username = c.getString(1),
                    password = c.getString(2),
                    role = UserRole.valueOf(c.getString(3)),
                    isActive = active
                )
            }
        }
        return null
    }

    // Opcional
    fun updateUsername(oldDni: String, newDni: String): Int {
        val cv = ContentValues().apply {
            put("username", newDni)
            put("password", newDni)
        }
        return wdb.update("users", cv, "username = ?", arrayOf(oldDni))
    }

    // Opcional
    fun deactivateByUsername(username: String): Int {
        val cv = ContentValues().apply { put("is_active", 0) }
        return wdb.update("users", cv, "username = ?", arrayOf(username))
    }

    // Opcional
    fun deleteByUsername(username: String): Int {
        return wdb.delete("users", "username = ?", arrayOf(username))
    }
}
