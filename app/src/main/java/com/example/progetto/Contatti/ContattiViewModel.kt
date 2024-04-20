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
            MyDatabaseHelper.COLUMN_EMAILL,
            MyDatabaseHelper.COLUMN_PASSWORDL
        )
    return ""
    }

    fun getCognome1(): String {
        // Sostituisci questa logica con il codice per ottenere il cognome del contatto 1 dalla base di dati
        return "Cognome Contatto 1"
    }

    fun getNumTelefono1(): String {
        // Sostituisci questa logica con il codice per ottenere il numero di telefono del contatto 1 dalla base di dati
        return "Numero di telefono Contatto 1"
    }

    fun getNome2(): String {
        // Sostituisci questa logica con il codice per ottenere il nome del contatto 2 dalla base di dati
        return "Nome Contatto 2"
    }

    fun getCognome2(): String {
        // Sostituisci questa logica con il codice per ottenere il cognome del contatto 2 dalla base di dati
        return "Cognome Contatto 2"
    }

    fun getNumTelefono2(): String {
        // Sostituisci questa logica con il codice per ottenere il numero di telefono del contatto 2 dalla base di dati
        return "Numero di telefono Contatto 2"
    }

    fun getNome3(): String {
        // Sostituisci questa logica con il codice per ottenere il nome del contatto 3 dalla base di dati
        return "Nome Contatto 3"
    }

    fun getCognome3(): String {
        // Sostituisci questa logica con il codice per ottenere il cognome del contatto 3 dalla base di dati
        return "Cognome Contatto 3"
    }

    fun getNumTelefono3(): String {
        // Sostituisci questa logica con il codice per ottenere il numero di telefono del contatto 3 dalla base di dati
        return "Numero di telefono Contatto 3"
    }

}