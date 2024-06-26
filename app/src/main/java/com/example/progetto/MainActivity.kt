package com.example.progetto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Controlla se l'utente è già registrato
        val isUserRegistered = checkIfUserIsRegistered()

        if (isUserRegistered) {
            // L'utente è già registrato, mostra la homepage
            setContentView(R.layout.homepage)
        } else {
            // L'utente non è ancora registrato, mostra il layout di scelta utente
            setContentView(R.layout.layoutsceltautente)
            // Trova il bottone "autonomo" nel layout
            val autonomoButton: ImageButton = findViewById(R.id.imageButton6)
            // Aggiungi un listener al bottone
            autonomoButton.setOnClickListener {
                // Avvia un'attività per la schermata di registrazione
                    val intent = Intent(this, RegistrazioneActivity::class.java)
                    startActivity(intent)
                }
        }

    }

    private fun checkIfUserIsRegistered(): Boolean {
        // Ottieni un'istanza del database
        val dbHelper = MyDatabaseHelper(this)
        val db = dbHelper.readableDatabase

        // Esegui la query per verificare se esiste un record corrispondente all'utente
        //val query = "SELECT COUNT(*) FROM user WHERE email = ?"
        // Esegui la query per verificare se esiste almeno un record nella tabella degli utenti

        val query = "SELECT COUNT(*) FROM ${MyDatabaseHelper.TABLE_USER}"
        val cursor = db.rawQuery(query, null)



        // Ottieni il numero di righe nel cursore (dovrebbe essere 0 se l'utente non è registrato)
        cursor.moveToFirst()
        val count = cursor.getInt(0)

        // Chiudi il cursore e il database
        cursor.close()
        db.close()

        // Restituisci true se l'utente è registrato (count > 0), false altrimenti
        return count > 0
    }




}