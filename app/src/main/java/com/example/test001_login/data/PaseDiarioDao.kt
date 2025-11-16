package com.example.test001_login.data

import android.content.ContentValues
import android.content.Context
import com.example.test001_login.model.PaseDiario

class PaseDiarioDao(context: Context) {

    private val dbHelper = AppDbHelper(context)
    private val rdb = dbHelper.readableDatabase
    private val wdb = dbHelper.writableDatabase

    /** Registrar pase diario */
    fun registrarPase(p: PaseDiario): Long {
        val cv = ContentValues().apply {
            put("dni", p.dni)
            put("fecha", p.fecha)
            put("monto", p.monto)
        }
        return wdb.insert("pases_diarios", null, cv)
    }

    /** Listar todos los pases del día actual */
    fun obtenerPasesDelDia(fechaHoy: String): List<PaseDiario> {
        val lista = mutableListOf<PaseDiario>()
        val sql = """
            SELECT id, dni, fecha, monto
            FROM pases_diarios
            WHERE fecha = DATE(?)
            ORDER BY id DESC
        """

        rdb.rawQuery(sql, arrayOf(fechaHoy)).use { c ->
            while (c.moveToNext()) {
                lista.add(
                    PaseDiario(
                        id = c.getLong(0),
                        dni = c.getString(1),
                        fecha = c.getString(2),
                        monto = c.getDouble(3)
                    )
                )
            }
        }
        return lista
    }

    /** Obtener historial de pases por DNI */
    fun obtenerHistorialPorDni(dni: String): List<PaseDiario> {
        val lista = mutableListOf<PaseDiario>()
        val sql = """
            SELECT id, dni, fecha, monto
            FROM pases_diarios
            WHERE dni = ?
            ORDER BY fecha DESC
        """

        rdb.rawQuery(sql, arrayOf(dni)).use { c ->
            while (c.moveToNext()) {
                lista.add(
                    PaseDiario(
                        id = c.getLong(0),
                        dni = c.getString(1),
                        fecha = c.getString(2),
                        monto = c.getDouble(3)
                    )
                )
            }
        }
        return lista
    }


    fun listarPasesDelDia(fecha: String): List<PaseDiario> {
        val db = dbHelper.readableDatabase
        val lista = mutableListOf<PaseDiario>()

        val sql = """
        SELECT id, dni, fecha, monto
        FROM pases_diarios
        WHERE fecha = ?
        ORDER BY id DESC
    """

        val cursor = db.rawQuery(sql, arrayOf(fecha))

        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    PaseDiario(
                        id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        dni = cursor.getString(cursor.getColumnIndexOrThrow("dni")),
                        fecha = cursor.getString(cursor.getColumnIndexOrThrow("fecha")),
                        monto = cursor.getDouble(cursor.getColumnIndexOrThrow("monto"))
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }


}
