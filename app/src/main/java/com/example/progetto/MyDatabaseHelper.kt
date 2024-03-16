package com.example.progetto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Definisci il nome del database
        private const val DATABASE_NAME = "my_database.db"
        // Definisci la versione del database
        private const val DATABASE_VERSION = 1

        // Definisci la struttura della tabella utente
        private const val CREATE_TABLE_USER =
            "CREATE TABLE user (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "password TEXT" +
                    ")"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crea la tabella utente quando il database viene creato per la prima volta
        db.execSQL(CREATE_TABLE_USER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Aggiorna il database quando viene incrementata la versione
        // Qui puoi gestire eventuali migrazioni dei dati se necessario
    }
}
