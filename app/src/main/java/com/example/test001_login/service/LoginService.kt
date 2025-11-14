// app/src/main/java/com/example/test001_login/service/LoginService.kt
package com.example.test001_login.service

import android.content.Context
import com.example.test001_login.data.UserDao
import com.example.test001_login.model.User

class LoginService(private val context: Context) {
    private val userDao = UserDao(context)

    /** Devuelve el usuario si (username,password) es válido y está activo; si no, null. */
    fun authenticate(username: String, password: String): User? {
        return userDao.getByCredentials(username, password)
    }
}
