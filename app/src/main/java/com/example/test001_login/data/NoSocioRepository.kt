package com.example.test001_login.data

import android.content.ContentValues
import android.content.Context
import com.example.test001_login.model.NoSocio

class NoSocioRepository(context: Context) {

    private val dbHelper = AppDbHelper(context)

    fun insertNoSocio(dni: String, nombre: String, telefono: String, fecha: String): Boolean {
        val db = dbHelper.writableDatabase

        val cv = ContentValues().apply {
            put("dni", dni)
            put("nombre", nombre)
            put("apellido", "")
            put("telefono", telefono)
            put("fecha_registro", fecha)
        }

        val res = db.insert("no_socios", null, cv)
        return res != -1L
    }

    fun getAllNoSocios(): List<NoSocio> {
        val db = dbHelper.readableDatabase
        val lista = mutableListOf<NoSocio>()

        val cursor = db.rawQuery("SELECT * FROM no_socios", null)

        if (cursor.moveToFirst()) {
            do {
                val item = NoSocio(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    dni = cursor.getString(cursor.getColumnIndexOrThrow("dni")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                    telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")),
                    fechaRegistro = cursor.getString(cursor.getColumnIndexOrThrow("fecha_registro"))
                )
                lista.add(item)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    fun getNoSocioByDni(dni: String): NoSocio? {
        val sql = "SELECT id, dni, nombre, apellido, telefono, fecha_registro FROM no_socios WHERE dni = ?"
        dbHelper.readableDatabase.rawQuery(sql, arrayOf(dni)).use { c ->
            return if (c.moveToFirst()) {
                NoSocio(

                    id = c.getLong(0).toInt(),
                    dni = c.getString(1),
                    nombre = c.getString(2),
                    apellido = c.getString(3),
                    telefono = c.getString(4),
                    fechaRegistro = c.getString(5)
                )
            } else null
        }
    }


}
