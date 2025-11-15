package com.example.test001_login.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Helper de SQLite para crear/actualizar la base local.
 * DB: club_deportivo.db
 * Tablas:
 *  - users (credenciales y roles)
 *  - payments (pagos mensuales de socios)
 *  - pases_diarios (no socios — pase diario)
 */
class AppDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        // -------------------------
        // TABLA USERS (LOGIN)
        // -------------------------
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS users(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                role TEXT NOT NULL,
                is_active INTEGER NOT NULL DEFAULT 1
            );
            """.trimIndent()
        )

        // Semilla opcional: usuario admin
        db.execSQL(
            """
            INSERT OR IGNORE INTO users(username, password, role, is_active)
            VALUES('admin', '1234', 'ADMIN', 1);
            """.trimIndent()
        )

        // -------------------------
        // TABLA PAYMENTS (SOCIOS)
        // -------------------------
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS payments(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                socio_dni TEXT NOT NULL,
                fecha_pago TEXT NOT NULL,     -- formato yyyy-MM-dd
                monto REAL NOT NULL
            );
            """.trimIndent()
        )

        // -------------------------
        // TABLA PASES DIARIOS (NO SOCIOS)
        // -------------------------
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS pases_diarios(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                dni TEXT NOT NULL,
                fecha TEXT NOT NULL,          -- yyyy-MM-dd
                monto REAL NOT NULL
            );
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // En caso de cambios de esquema, migraciones aquí.
        // Ejemplo:
        // db.execSQL("DROP TABLE IF EXISTS payments")
        // db.execSQL("DROP TABLE IF EXISTS pases_diarios")
        // onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "club_deportivo.db"
        const val DATABASE_VERSION = 1
    }
}
