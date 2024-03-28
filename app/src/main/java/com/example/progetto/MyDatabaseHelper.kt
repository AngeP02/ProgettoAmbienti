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

        const val TABLE_CONTATTI = "datiUtente"
        const val COLUMN_NOME = "nome"
        const val COLUMN_COGNOME = "cognome"
        const val COLUMN_DATANASCITA = "data di nascita"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_NOMECONTATTO1 = "nome contatto 1"
        const val COLUMN_NOMECONTATTO2 = "nome contatto 2"
        const val COLUMN_NOMECONTATTO3 = "nome contatto 3"
        const val COLUMN_COGNOMECONTATTO1 = "cognome contatto 1"
        const val COLUMN_COGNOMECONTATTO2 = "cognome contatto 2"
        const val COLUMN_COGNOMECONTATTO3 = "cognome contatto 3"
        const val COLUMN_TELEFONOCONTATTO1 = "telefono contatto 1"
        const val COLUMN_TELEFONOCONTATTO2 = "telefono contatto 2"
        const val COLUMN_TELEFONOCONTATTO3 = "telefono contatto 3"

    }


    override fun onCreate(db: SQLiteDatabase) {
        val DatiUtente = ("CREATE TABLE " +
                TABLE_CONTATTI + "("
                + COLUMN_EMAIL + " TEXT PRIMARY KEY,"
                + COLUMN_NOME + " TEXT,"
                + COLUMN_COGNOME + " TEXT"
                + COLUMN_DATANASCITA + " TEXT,"
                + COLUMN_PASSWORD + " TEXT"
                + COLUMN_NOMECONTATTO1 + " TEXT,"
                + COLUMN_COGNOMECONTATTO1 + " TEXT"
                + COLUMN_TELEFONOCONTATTO1 + " TEXT,"
                + COLUMN_NOMECONTATTO2 + " TEXT"
                + COLUMN_COGNOMECONTATTO2 + " TEXT,"
                + COLUMN_TELEFONOCONTATTO2 + " TEXT"
                + COLUMN_NOMECONTATTO3 + " TEXT,"
                + COLUMN_COGNOMECONTATTO3 + " TEXT"
                + COLUMN_TELEFONOCONTATTO3 + " TEXT"
                + ")")
        db.execSQL(DatiUtente)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Aggiorna il database quando viene incrementata la versione
        // Qui puoi gestire eventuali migrazioni dei dati se necessario
    }
}
