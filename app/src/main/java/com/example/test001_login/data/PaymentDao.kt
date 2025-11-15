package com.example.test001_login.data

import android.content.ContentValues
import android.content.Context
import com.example.test001_login.model.Payment

class PaymentDao(context: Context) {

    private val dbHelper = AppDbHelper(context)
    private val rdb = dbHelper.readableDatabase
    private val wdb = dbHelper.writableDatabase

    /** Registrar un pago mensual */
    fun registrarPago(p: Payment): Long {
        val cv = ContentValues().apply {
            put("socio_dni", p.socioDni)
            put("fecha_pago", p.fechaPago)
            put("monto", p.monto)
        }
        return wdb.insert("payments", null, cv)
    }

    /** Devuelve todos los pagos de un socio ordenados por fecha DESC */
    fun obtenerPagosDeSocio(dni: String): List<Payment> {
        val lista = mutableListOf<Payment>()
        val sql = """
            SELECT id, socio_dni, fechaPago, monto
            FROM payments
            WHERE socio_dni = ?
            ORDER BY fecha_pago DESC
        """

        rdb.rawQuery(sql, arrayOf(dni)).use { c ->
            while (c.moveToNext()) {
                lista.add(
                    Payment(
                        id = c.getLong(0),
                        socioDni = c.getString(1),
                        fechaPago = c.getString(2),
                        monto = c.getDouble(3)
                    )
                )
            }
        }
        return lista
    }

    /** Obtener el último pago (para saber si está vencido) */
    fun obtenerUltimoPago(dni: String): Payment? {
        val sql = """
            SELECT id, socio_dni, fecha_pago, monto
            FROM payments
            WHERE socio_dni = ?
            ORDER BY fecha_pago DESC
            LIMIT 1
        """

        rdb.rawQuery(sql, arrayOf(dni)).use { c ->
            return if (c.moveToFirst()) {
                Payment(
                    id = c.getLong(0),
                    socioDni = c.getString(1),
                    fechaPago = c.getString(2),
                    monto = c.getDouble(3)
                )
            } else null
        }
    }

    /**
     * Devuelve lista de socios cuya cuota vence HOY.
     * Regla: cuota válida = fecha_pago + 30 días
     * Se compara con la fecha actual YYYY-MM-DD
     */
    fun sociosConCuotaVencida(fechaHoy: String): List<String> {
        val lista = mutableListOf<String>()

        val sql = """
            SELECT socio_dni
            FROM payments
            GROUP BY socio_dni
            HAVING DATE(MAX(fecha_pago), '+30 days') = DATE(?)
        """

        rdb.rawQuery(sql, arrayOf(fechaHoy)).use { c ->
            while (c.moveToNext()) {
                lista.add(c.getString(0))
            }
        }

        return lista
    }
    fun ultimaFechaPago(dni: String): String? {
        val sql = """
        SELECT fecha_pago FROM payments
        WHERE socio_dni = ?
        ORDER BY fecha_pago DESC LIMIT 1
    """
        rdb.rawQuery(sql, arrayOf(dni)).use { c ->
            return if (c.moveToFirst()) c.getString(0) else null
        }
    }


    fun listarPagosDelDia(fecha: String): List<Payment> {
        val lista = mutableListOf<Payment>()
        val sql = """
        SELECT id, socio_dni, fecha_pago, monto
        FROM payments
        WHERE fecha_pago = ?
    """
        rdb.rawQuery(sql, arrayOf(fecha)).use { c ->
            while (c.moveToNext()) {
                lista.add(
                    Payment(
                        id = c.getLong(0),
                        socioDni = c.getString(1),
                        fechaPago = c.getString(2),
                        monto = c.getDouble(3)
                    )
                )
            }
        }
        return lista
    }

}
