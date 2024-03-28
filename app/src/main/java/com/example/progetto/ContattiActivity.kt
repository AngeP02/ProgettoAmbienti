package com.example.progetto

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.progetto.MyDatabaseHelper
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_COGNOME
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_COGNOMECONTATTO1
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_COGNOMECONTATTO2
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_COGNOMECONTATTO3
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_DATANASCITA
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_EMAIL
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_NOME
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_NOMECONTATTO1
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_NOMECONTATTO2
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_NOMECONTATTO3
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_PASSWORD
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_TELEFONOCONTATTO1
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_TELEFONOCONTATTO2
import com.example.progetto.MyDatabaseHelper.Companion.COLUMN_TELEFONOCONTATTO3


class ContattiActivity: AppCompatActivity() {
    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.numeripreferiti)
        dbHelper = MyDatabaseHelper(this)
        val indietroButton: Button = findViewById(R.id.indietro1)
        // Aggiungi un listener al bottone
        indietroButton.setOnClickListener {
            // Avvia un'attività per la schermata di registrazione
            val intent = Intent(this, RegistrazioneActivity::class.java)
            startActivity(intent)
        }
        val avantiButton: Button = findViewById(R.id.salva)
        // Aggiungi un listener al bottone
        avantiButton.setOnClickListener {
            // Avvia un'attività per la schermata di registrazione
            val intent = Intent(this, HomePageAutonoma::class.java)
            salvaContatto()
            startActivity(intent)
        }

    }

    private fun salvaContatto() {
        val nome = intent.getStringExtra("nome")
        val cognome = intent.getStringExtra("cognome")
        val dataNascita = intent.getStringExtra("dataNascita")
        // GESTIRE NULL POINTER EXCEPTION
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")

        val nome1EditText: EditText = findViewById(R.id.Nome1)
        val cognome1EditText: EditText = findViewById(R.id.Cognome1)
        val numTelefono1EditText: EditText = findViewById(R.id.NumTelefono1)
        val nome1 = nome1EditText.text.toString()
        val cognome1 = cognome1EditText.text.toString()
        val telefono1 = numTelefono1EditText.text.toString()

        val nome2EditText: EditText = findViewById(R.id.Nome2)
        val cognome2EditText: EditText = findViewById(R.id.Cognome2)
        val numTelefono2EditText: EditText = findViewById(R.id.NumTelefono2)
        val nome2 = nome1EditText.text.toString()
        val cognome2 = cognome1EditText.text.toString()
        val telefono2 = numTelefono1EditText.text.toString()

        val nome3EditText: EditText = findViewById(R.id.Nome3)
        val cognome3EditText: EditText = findViewById(R.id.Cognome3)
        val numTelefono3EditText: EditText = findViewById(R.id.NumTelefono3)
        val nome3 = nome1EditText.text.toString()
        val cognome3 = cognome1EditText.text.toString()
        val telefono3 = numTelefono1EditText.text.toString()

        // Controlla se i campi non sono vuoti prima di salvare
        if (nome1.isNotEmpty() && cognome1.isNotEmpty() && telefono1.isNotEmpty()) {
            // Ottieni un'istanza scrivibile del database
            val db = dbHelper.writableDatabase

            // Crea un ContentValues per inserire i dati nel database
            val values = ContentValues().apply {
                put(COLUMN_EMAIL, email)
                put(COLUMN_NOME, nome)
                put(COLUMN_COGNOME, cognome)
                put(COLUMN_DATANASCITA, dataNascita)
                put(COLUMN_PASSWORD, password)
                put(COLUMN_NOMECONTATTO1, nome1)
                put(COLUMN_COGNOMECONTATTO1, cognome1)
                put(COLUMN_TELEFONOCONTATTO1, telefono1)
                put(COLUMN_NOMECONTATTO2, nome2)
                put(COLUMN_COGNOMECONTATTO2, cognome2)
                put(COLUMN_TELEFONOCONTATTO2, telefono2)
                put(COLUMN_NOMECONTATTO3, nome3)
                put(COLUMN_COGNOMECONTATTO3, cognome3)
                put(COLUMN_TELEFONOCONTATTO3, telefono3)
            }

            // Esegui l'operazione di inserimento
            db.insert(MyDatabaseHelper.TABLE_CONTATTI, null, values)

            // Chiudi il database
            db.close()

            // Puoi anche aggiungere un messaggio di conferma o avviare un'altra attività dopo il salvataggio dei dati
        } else {
            // Mostra un messaggio di errore se i campi sono vuoti
            Toast.makeText(this, "Inserisci il nome e il cognome del contatto", Toast.LENGTH_SHORT).show()
        }
    }
}