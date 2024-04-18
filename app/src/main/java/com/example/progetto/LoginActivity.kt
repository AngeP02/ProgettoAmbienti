package com.example.progetto

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.security.MessageDigest

class LoginActivity: AppCompatActivity() {

    private lateinit var dbHelper: MyDatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login) // Imposta il layout dell'attività

        dbHelper = MyDatabaseHelper(applicationContext)

        val avantiButton: Button = findViewById(R.id.avantiLogin)
        // Aggiungi un listener al bottone
        avantiButton.setOnClickListener {
            // Avvia un'attività per la schermata di registrazione
            val intent = Intent(this, HomePageAutonoma::class.java)
            salvaUtente(intent)
            //startActivity(intent)
        }
    }

    private fun salvaUtente(intent: Intent) {
        val emailEditText: EditText = findViewById(R.id.Email_utente)
        val passwordEditText: EditText = findViewById(R.id.Password_utente)
        val email1 = emailEditText.text.toString()
        val password1 = passwordEditText.text.toString()
        if (email1.isNotEmpty() && password1.isNotEmpty()) {




            // Ottieni un'istanza scrivibile del database
            val db = dbHelper.writableDatabase
            val db1 = dbHelper.readableDatabase
            // Definisci una proiezione che contiene le colonne da leggere
            val projection = arrayOf(
                MyDatabaseHelper.COLUMN_EMAILL,
                MyDatabaseHelper.COLUMN_PASSWORDL
            )

            // Filtra le righe in cui l'email corrisponde a quella inserita dall'utente
            val selection = "${MyDatabaseHelper.COLUMN_EMAILL} = ?"
            val selectionArgs = arrayOf(email1)

            // Esegue la query sulla tabella degli utenti
            val cursor = db1.query(
                MyDatabaseHelper.TABLE_CONTATTI,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
            )

            // Verifica se ci sono risultati
            if (cursor.moveToFirst()) {
                // Email trovata nel database, ora verifica la password
                val savedPassword = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_PASSWORDL))
                val hashedEnteredPassword = hashPassword(password1)
                //val hashedPasswordBase64 = Base64.encodeToString(hashedEnteredPassword.toByteArray(), Base64.DEFAULT)
                if (hashedEnteredPassword == savedPassword) {
                    val values = ContentValues().apply {
                        put(MyDatabaseHelper.COLUMN_EMAILL, email1)
                        put(MyDatabaseHelper.COLUMN_PASSWORDL, password1)
                        startActivity(intent)
                    }
                    db.insert(MyDatabaseHelper.TABLE_USER, null, values)
                } else {
                    Toast.makeText(this, "Username o password errati", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Username o password errati", Toast.LENGTH_SHORT).show()
            }
            // Crea un ContentValues per inserire i dati nel database

            // Chiudi il database
            cursor.close()
            db.close()
        }
    }
    fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }
}