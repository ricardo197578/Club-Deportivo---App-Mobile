package com.example.test001_login.data

import android.content.ContentValues
import android.content.Context
import com.example.test001_login.model.Socio

class SocioRepository(context: Context) {

    private val dbHelper = AppDbHelper(context)

    // INSERTAR SOCIO
    fun insertSocio(socio: Socio): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("dni", socio.dni)
            put("nombre", socio.nombre)
            put("apellido", socio.apellido)
            put("telefono", socio.telefono)
            put("direccion", socio.direccion)
            put("fecha_alta", socio.fechaAlta)
            put("fecha_ultimo_pago", socio.fechaUltimoPago)
            put("activo", socio.activo)
        }
        val id = db.insert("socios", null, values)
        return id != -1L
    }

    // CONSULTAR POR DNI
    fun getSocioByDni(dni: String): Socio? {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM socios WHERE dni = ?",
            arrayOf(dni)
        )
        if (cursor.moveToFirst()) {
            val socio = Socio(

                id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),

                dni = cursor.getString(cursor.getColumnIndexOrThrow("dni")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")),
                direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion")),
                fechaAlta = cursor.getString(cursor.getColumnIndexOrThrow("fecha_alta")),
                fechaUltimoPago = cursor.getString(cursor.getColumnIndexOrThrow("fecha_ultimo_pago")),
                activo = cursor.getInt(cursor.getColumnIndexOrThrow("activo"))
            )
            cursor.close()
            return socio
        }
        cursor.close()
        return null
    }

    fun getAllSocios(): List<Socio> {
        val db = dbHelper.readableDatabase
        val lista = mutableListOf<Socio>()

        val cursor = db.rawQuery("SELECT * FROM socios ORDER BY nombre", null)

        if (cursor.moveToFirst()) {
            do {
                val item = Socio(

                    id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),

                    dni = cursor.getString(cursor.getColumnIndexOrThrow("dni")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                    telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")),
                    direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion")),
                    fechaAlta = cursor.getString(cursor.getColumnIndexOrThrow("fecha_alta")),
                    fechaUltimoPago = cursor.getString(cursor.getColumnIndexOrThrow("fecha_ultimo_pago")),
                    activo = cursor.getInt(cursor.getColumnIndexOrThrow("activo"))
                )
                lista.add(item)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    fun updateSocio(socio: Socio): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", socio.nombre)
            put("apellido", socio.apellido)
            put("telefono", socio.telefono)
            put("direccion", socio.direccion)
            put("fecha_ultimo_pago", socio.fechaUltimoPago)
            put("activo", socio.activo)
        }

        val result = db.update(
            "socios",
            values,
            "dni = ?",
            arrayOf(socio.dni)
        )

        return result > 0
    }

    fun deleteSocio(dni: String): Boolean {
        val db = dbHelper.writableDatabase
        val result = db.delete("socios", "dni = ?", arrayOf(dni))
        return result > 0
    }

    fun actualizarFechaUltimoPago(dni: String, fecha: String) {
        val db = dbHelper.writableDatabase
        val cv = ContentValues().apply {
            put("fecha_ultimo_pago", fecha)
            put("activo", 1)
        }
        db.update("socios", cv, "dni = ?", arrayOf(dni))
    }


}
