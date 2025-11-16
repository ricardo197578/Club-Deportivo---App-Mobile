package com.example.test001_login.data

import android.content.ContentValues
import android.content.Context
import com.example.test001_login.model.NoSocio

class NoSocioRepository(context: Context) {

    private val dbHelper = AppDbHelper(context)

    // INSERTAR
    fun insertNoSocio(noSocio: NoSocio): Boolean {
        val db = dbHelper.writableDatabase
        val cv = ContentValues().apply {
            put("nombre", noSocio.nombre)
            put("apellido", noSocio.apellido)
            put("dni", noSocio.dni)
            put("telefono", noSocio.telefono)
            put("fecha_registro", noSocio.fechaRegistro)
        }
        val id = db.insert("no_socios", null, cv)
        return id != -1L
    }

    // BUSCAR POR DNI
    fun getNoSocioByDni(dni: String): NoSocio? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM no_socios WHERE dni = ?",
            arrayOf(dni)
        )

        if (cursor.moveToFirst()) {
            val obj = NoSocio(
                id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                dni = cursor.getString(cursor.getColumnIndexOrThrow("dni")),
                telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")),
                fechaRegistro = cursor.getString(cursor.getColumnIndexOrThrow("fecha_registro"))
            )
            cursor.close()
            return obj
        }

        cursor.close()
        return null
    }

    // LISTAR TODOS
    fun getAllNoSocios(): List<NoSocio> {
        val lista = mutableListOf<NoSocio>()
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery("SELECT * FROM no_socios ORDER BY nombre", null)

        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    NoSocio(
                        id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                        apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                        dni = cursor.getString(cursor.getColumnIndexOrThrow("dni")),
                        telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")),
                        fechaRegistro = cursor.getString(cursor.getColumnIndexOrThrow("fecha_registro"))
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    // ACTUALIZAR
    fun updateNoSocio(noSocio: NoSocio): Boolean {
        val db = dbHelper.writableDatabase
        val cv = ContentValues().apply {
            put("nombre", noSocio.nombre)
            put("apellido", noSocio.apellido)
            put("telefono", noSocio.telefono)
        }
        val rows = db.update("no_socios", cv, "dni = ?", arrayOf(noSocio.dni))
        return rows > 0
    }

    // ELIMINAR
    fun deleteNoSocio(dni: String): Boolean {
        val db = dbHelper.writableDatabase
        return db.delete("no_socios", "dni = ?", arrayOf(dni)) > 0
    }
}
