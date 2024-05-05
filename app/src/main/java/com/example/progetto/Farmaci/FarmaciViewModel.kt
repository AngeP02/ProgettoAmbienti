package com.example.progetto.Farmaci

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.progetto.MyDatabaseHelper
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_NOMEFARMACO
import com.example.progetto.MyDatabaseHelper.Companion.TABLE_FARMACI

class FarmaciViewModel(context: Context) : ViewModel() {
    private val dbHelper = MyDatabaseHelper(context)

    private val _farmaci = MutableLiveData<List<String>>()  // Lista dei nomi dei farmaci
    val farmaci: LiveData<List<String>> get() = _farmaci

    init {
        loadFarmaci()  // Carica i farmaci all'inizializzazione
    }

    private fun loadFarmaci() {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT $COLUMN_NOMEFARMACO FROM $TABLE_FARMACI", null)

        val farmaciList = mutableListOf<String>()
        if (cursor.moveToFirst()) {
            do {
                val nome = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMEFARMACO))
                farmaciList.add(nome)
            } while (cursor.moveToNext())
        }

        cursor.close()
        _farmaci.value = farmaciList  // Aggiorna il LiveData
    }

}



