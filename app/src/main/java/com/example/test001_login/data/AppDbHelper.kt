package com.example.test001_login.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Helper de SQLite para crear/actualizar la base local.
 * DB: club_deportivo.db
 * Tablas iniciales:
 *  - users: credenciales + rol
 */
class AppDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        // Tabla de usuarios (login)
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
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Si en el futuro cambiámos  el esquema, hacér migraciones acá.
        // db.execSQL("DROP TABLE IF EXISTS users")
        // onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "club_deportivo.db"
        const val DATABASE_VERSION = 1
    }
}
