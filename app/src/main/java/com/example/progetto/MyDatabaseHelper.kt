package com.example.progetto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Definisci il nome del database
        private const val DATABASE_NAME = "my_database.db"
        // Definisci la versione del database
        private const val DATABASE_VERSION = 1

        const val TABLE_CONTATTI = "datiUtente"
        const val COLUMN_NOME = "nome"
        const val COLUMN_COGNOME = "cognome"
        const val COLUMN_DATANASCITA = "datadinascita"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_NOMECONTATTO1 = "nomecontatto1"
        const val COLUMN_NOMECONTATTO2 = "nomecontatto2"
        const val COLUMN_NOMECONTATTO3 = "nomecontatto3"
        const val COLUMN_COGNOMECONTATTO1 = "cognomecontatto1"
        const val COLUMN_COGNOMECONTATTO2 = "cognomecontatto2"
        const val COLUMN_COGNOMECONTATTO3 = "cognomecontatto3"
        const val COLUMN_TELEFONOCONTATTO1 = "telefonocontatto1"
        const val COLUMN_TELEFONOCONTATTO2 = "telefonocontatto2"
        const val COLUMN_TELEFONOCONTATTO3 = "telefonocontatto3"

        private const val TAG = "MyDatabaseHelper"
        /*private const val CREATE_TABLE_USER =
            "CREATE TABLE user (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "password TEXT" +
                    ")"*/

        const val TABLE_USER = "user"
        const val COLUMN_EMAILL = "email"
        const val COLUMN_PASSWORDL = "password"
    }


    override fun onCreate(db: SQLiteDatabase) {
        val datiUtente = ("CREATE TABLE " +
                TABLE_CONTATTI + "("
                + COLUMN_EMAIL + " TEXT PRIMARY KEY,"
                + COLUMN_NOME + " TEXT,"
                + COLUMN_COGNOME + " TEXT,"
                + COLUMN_DATANASCITA + " TEXT,"
                + COLUMN_PASSWORD + " TEXT,"
                + COLUMN_NOMECONTATTO1 + " TEXT,"
                + COLUMN_COGNOMECONTATTO1 + " TEXT,"
                + COLUMN_TELEFONOCONTATTO1 + " TEXT,"
                + COLUMN_NOMECONTATTO2 + " TEXT,"
                + COLUMN_COGNOMECONTATTO2 + " TEXT,"
                + COLUMN_TELEFONOCONTATTO2 + " TEXT,"
                + COLUMN_NOMECONTATTO3 + " TEXT,"
                + COLUMN_COGNOMECONTATTO3 + " TEXT,"
                + COLUMN_TELEFONOCONTATTO3 + " TEXT"
                + ")")

        val user = ("CREATE TABLE " +
                TABLE_USER + "("
                + COLUMN_EMAILL + " TEXT PRIMARY KEY,"
                + COLUMN_PASSWORDL + " TEXT"
                + ")")


        Log.d("MyDatabaseHelper", "Query di creazione della tabella: $datiUtente")
        db.execSQL(user)
        db.execSQL(datiUtente)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Aggiorna il database quando viene incrementata la versione
        // Qui puoi gestire eventuali migrazioni dei dati se necessario
    }
}
