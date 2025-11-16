package com.example.test001_login.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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

        // Usuario admin por defecto
        db.execSQL(
            """
            INSERT OR IGNORE INTO users(username, password, role, is_active)
            VALUES('admin', '1234', 'ADMIN', 1);
            """.trimIndent()
        )

        // -------------------------
        // TABLA SOCIOS
        // -------------------------
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS socios(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                dni TEXT NOT NULL UNIQUE,
                nombre TEXT NOT NULL,
                apellido TEXT,
                telefono TEXT,
                direccion TEXT,
                fecha_alta TEXT NOT NULL,
                fecha_ultimo_pago TEXT,
                activo INTEGER NOT NULL
            );
            """.trimIndent()
        )

        // -------------------------
        // TABLA NO_SOCIOS
        // -------------------------
        db.execSQL(
            """
            CREATE TABLE no_socios (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                apellido TEXT NOT NULL,
                dni TEXT UNIQUE NOT NULL,
                telefono TEXT,
                fecha_registro TEXT NOT NULL
            );

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
                fecha_pago TEXT NOT NULL,
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
                fecha TEXT NOT NULL,
                monto REAL NOT NULL
            );
            """.trimIndent()
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { }

    companion object {
        const val DATABASE_NAME = "club_deportivo.db"
        const val DATABASE_VERSION = 1
    }
}
