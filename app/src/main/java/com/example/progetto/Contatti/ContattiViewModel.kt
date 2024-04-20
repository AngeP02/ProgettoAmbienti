package com.example.progetto.Contatti

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.progetto.MyDatabaseHelper

class ContattiViewModel(private val applicationContext: Context) : ViewModel(){
    private lateinit var dbHelper: MyDatabaseHelper

    // Metodi per ottenere i dati dalla base di dati

    fun getNome1(): String {
        dbHelper = MyDatabaseHelper(applicationContext)
        val db1 = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.COLUMN_NOMECONTATTO1
        )
        // Definisci i criteri per la query
        val selection = null // Non ci sono criteri in questo esempio
        val selectionArgs = null // Non ci sono argomenti per la selezione
        val sortOrder = null // Non c'è ordinamento specificato

        // Esegui la query
        val cursor = db1.query(
            MyDatabaseHelper.TABLE_CONTATTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // Estrai il valore dalla prima riga del cursore
        var nomeContatto1 = ""
        if (cursor.moveToFirst()) {
            nomeContatto1 = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_NOMECONTATTO1))
        }

        // Chiudi il cursore dopo aver finito
        cursor.close()

        return nomeContatto1
    }

    fun getCognome1(): String {
        dbHelper = MyDatabaseHelper(applicationContext)
        val db1 = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.COLUMN_COGNOMECONTATTO1
        )
        // Definisci i criteri per la query
        val selection = null // Non ci sono criteri in questo esempio
        val selectionArgs = null // Non ci sono argomenti per la selezione
        val sortOrder = null // Non c'è ordinamento specificato

        // Esegui la query
        val cursor = db1.query(
            MyDatabaseHelper.TABLE_CONTATTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // Estrai il valore dalla prima riga del cursore
        var cognomeContatto1 = ""
        if (cursor.moveToFirst()) {
            cognomeContatto1 = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_COGNOMECONTATTO1))
        }

        // Chiudi il cursore dopo aver finito
        cursor.close()

        return cognomeContatto1
    }

    fun getNumTelefono1(): String {
        dbHelper = MyDatabaseHelper(applicationContext)
        val db1 = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.COLUMN_TELEFONOCONTATTO1
        )
        // Definisci i criteri per la query
        val selection = null // Non ci sono criteri in questo esempio
        val selectionArgs = null // Non ci sono argomenti per la selezione
        val sortOrder = null // Non c'è ordinamento specificato

        // Esegui la query
        val cursor = db1.query(
            MyDatabaseHelper.TABLE_CONTATTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // Estrai il valore dalla prima riga del cursore
        var telefonoContatto1 = ""
        if (cursor.moveToFirst()) {
            telefonoContatto1 = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_TELEFONOCONTATTO1))
        }

        // Chiudi il cursore dopo aver finito
        cursor.close()

        return telefonoContatto1
    }

    fun getNome2(): String {
        dbHelper = MyDatabaseHelper(applicationContext)
        val db1 = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.COLUMN_NOMECONTATTO2
        )
        // Definisci i criteri per la query
        val selection = null // Non ci sono criteri in questo esempio
        val selectionArgs = null // Non ci sono argomenti per la selezione
        val sortOrder = null // Non c'è ordinamento specificato

        // Esegui la query
        val cursor = db1.query(
            MyDatabaseHelper.TABLE_CONTATTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // Estrai il valore dalla prima riga del cursore
        var nomeContatto2 = ""
        if (cursor.moveToFirst()) {
            nomeContatto2 = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_NOMECONTATTO2))
        }
        // Chiudi il cursore dopo aver finito
        cursor.close()
        if (nomeContatto2.isEmpty()) {
            // Se nomeContatto2 è vuoto, restituisci un valore predefinito o gestisci il caso a tuo piacimento
            return ""
        } else {
            // Altrimenti, restituisci il valore estratto dal database
            return nomeContatto2
        }

    }

    fun getCognome2(): String {
        dbHelper = MyDatabaseHelper(applicationContext)
        val db1 = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.COLUMN_COGNOMECONTATTO2
        )
        // Definisci i criteri per la query
        val selection = null // Non ci sono criteri in questo esempio
        val selectionArgs = null // Non ci sono argomenti per la selezione
        val sortOrder = null // Non c'è ordinamento specificato

        // Esegui la query
        val cursor = db1.query(
            MyDatabaseHelper.TABLE_CONTATTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // Estrai il valore dalla prima riga del cursore
        var cognomeContatto2 = ""
        if (cursor.moveToFirst()) {
            cognomeContatto2 = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_COGNOMECONTATTO2))
        }
        // Chiudi il cursore dopo aver finito
        cursor.close()
        if (cognomeContatto2.isEmpty()) {
            // Se nomeContatto2 è vuoto, restituisci un valore predefinito o gestisci il caso a tuo piacimento
            return ""
        } else {
            // Altrimenti, restituisci il valore estratto dal database
            return cognomeContatto2
        }
    }

    fun getNumTelefono2(): String {
        dbHelper = MyDatabaseHelper(applicationContext)
        val db1 = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.COLUMN_TELEFONOCONTATTO2
        )
        // Definisci i criteri per la query
        val selection = null // Non ci sono criteri in questo esempio
        val selectionArgs = null // Non ci sono argomenti per la selezione
        val sortOrder = null // Non c'è ordinamento specificato

        // Esegui la query
        val cursor = db1.query(
            MyDatabaseHelper.TABLE_CONTATTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // Estrai il valore dalla prima riga del cursore
        var telefonoContatto2 = ""
        if (cursor.moveToFirst()) {
            telefonoContatto2 = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_TELEFONOCONTATTO2))
        }
        // Chiudi il cursore dopo aver finito
        cursor.close()
        if (telefonoContatto2.isEmpty()) {
            // Se nomeContatto2 è vuoto, restituisci un valore predefinito o gestisci il caso a tuo piacimento
            return ""
        } else {
            // Altrimenti, restituisci il valore estratto dal database
            return telefonoContatto2
        }
    }

    fun getNome3(): String {
        dbHelper = MyDatabaseHelper(applicationContext)
        val db1 = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.COLUMN_NOMECONTATTO3
        )
        // Definisci i criteri per la query
        val selection = null // Non ci sono criteri in questo esempio
        val selectionArgs = null // Non ci sono argomenti per la selezione
        val sortOrder = null // Non c'è ordinamento specificato

        // Esegui la query
        val cursor = db1.query(
            MyDatabaseHelper.TABLE_CONTATTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // Estrai il valore dalla prima riga del cursore
        var nomeContatto3 = ""
        if (cursor.moveToFirst()) {
            nomeContatto3 = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_NOMECONTATTO3))
        }
        // Chiudi il cursore dopo aver finito
        cursor.close()
        if (nomeContatto3.isEmpty()) {
            // Se nomeContatto2 è vuoto, restituisci un valore predefinito o gestisci il caso a tuo piacimento
            return ""
        } else {
            // Altrimenti, restituisci il valore estratto dal database
            return nomeContatto3
        }
    }

    fun getCognome3(): String {
        dbHelper = MyDatabaseHelper(applicationContext)
        val db1 = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.COLUMN_COGNOMECONTATTO3
        )
        // Definisci i criteri per la query
        val selection = null // Non ci sono criteri in questo esempio
        val selectionArgs = null // Non ci sono argomenti per la selezione
        val sortOrder = null // Non c'è ordinamento specificato

        // Esegui la query
        val cursor = db1.query(
            MyDatabaseHelper.TABLE_CONTATTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // Estrai il valore dalla prima riga del cursore
        var cognomeContatto3 = ""
        if (cursor.moveToFirst()) {
            cognomeContatto3 = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_COGNOMECONTATTO3))
        }
        // Chiudi il cursore dopo aver finito
        cursor.close()
        if (cognomeContatto3.isEmpty()) {
            // Se nomeContatto2 è vuoto, restituisci un valore predefinito o gestisci il caso a tuo piacimento
            return ""
        } else {
            // Altrimenti, restituisci il valore estratto dal database
            return cognomeContatto3
        }
    }

    fun getNumTelefono3(): String {
        dbHelper = MyDatabaseHelper(applicationContext)
        val db1 = dbHelper.readableDatabase

        val projection = arrayOf(
            MyDatabaseHelper.COLUMN_TELEFONOCONTATTO3
        )
        // Definisci i criteri per la query
        val selection = null // Non ci sono criteri in questo esempio
        val selectionArgs = null // Non ci sono argomenti per la selezione
        val sortOrder = null // Non c'è ordinamento specificato

        // Esegui la query
        val cursor = db1.query(
            MyDatabaseHelper.TABLE_CONTATTI,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )

        // Estrai il valore dalla prima riga del cursore
        var telefonoContatto3 = ""
        if (cursor.moveToFirst()) {
            telefonoContatto3 = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_TELEFONOCONTATTO3))
        }
        // Chiudi il cursore dopo aver finito
        cursor.close()
        if (telefonoContatto3.isEmpty()) {
            // Se nomeContatto2 è vuoto, restituisci un valore predefinito o gestisci il caso a tuo piacimento
            return ""
        } else {
            // Altrimenti, restituisci il valore estratto dal database
            return telefonoContatto3
        }
    }

}