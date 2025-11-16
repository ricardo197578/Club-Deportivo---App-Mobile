package com.example.test001_login.data

import android.content.Context
import com.example.test001_login.model.Payment

class PaymentDao(context: Context) {

    private val dbHelper = AppDbHelper(context)

    // -------------------------------------------
    // A) Registrar pago
    // -------------------------------------------
    fun registrarPago(pago: Payment): Long {
        val db = dbHelper.writableDatabase

        val sql = """
            INSERT INTO payments (socio_dni, fecha_pago, monto)
            VALUES (?, ?, ?)
        """

        val stmt = db.compileStatement(sql)
        stmt.bindString(1, pago.socioDni)
        stmt.bindString(2, pago.fechaPago)
        stmt.bindDouble(3, pago.monto)

        return stmt.executeInsert()
    }

    // -------------------------------------------
    // B) Socios con cuota vencida (sí tienen pagos)
    // -------------------------------------------
    fun sociosConCuotaVencida(fechaHoy: String): List<String> {
        val db = dbHelper.readableDatabase
        val lista = mutableListOf<String>()

        val sql = """
            SELECT socio_dni, MAX(fecha_pago) AS ultimo_pago
            FROM payments
            GROUP BY socio_dni
            HAVING ultimo_pago < ?
        """

        val cursor = db.rawQuery(sql, arrayOf(fechaHoy))

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    // -------------------------------------------
    // C) Socios sin ningún pago registrado
    // -------------------------------------------
    fun sociosSinPagos(): List<String> {
        val db = dbHelper.readableDatabase
        val lista = mutableListOf<String>()

        val sql = """
            SELECT dni 
            FROM socios
            WHERE dni NOT IN (SELECT socio_dni FROM payments)
        """

        val cursor = db.rawQuery(sql, null)

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }


    fun listarPagosDelDia(fecha: String): List<Payment> {
        val db = dbHelper.readableDatabase
        val lista = mutableListOf<Payment>()

        val sql = """
        SELECT id, socio_dni, fecha_pago, monto
        FROM payments
        WHERE fecha_pago = ?
        ORDER BY id DESC
    """

        val cursor = db.rawQuery(sql, arrayOf(fecha))

        if (cursor.moveToFirst()) {
            do {
                lista.add(
                    Payment(
                        id = cursor.getLong(cursor.getColumnIndexOrThrow("id")),
                        socioDni = cursor.getString(cursor.getColumnIndexOrThrow("socio_dni")),
                        fechaPago = cursor.getString(cursor.getColumnIndexOrThrow("fecha_pago")),
                        monto = cursor.getDouble(cursor.getColumnIndexOrThrow("monto"))
                    )
                )
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

}

