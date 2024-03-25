package com.example.progetto

import android.app.AlertDialog
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.sql.Date
import java.util.Locale

class RegistrazioneActivity : AppCompatActivity() {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private var selectedDate: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrazioneautonoma)
        val dataButton: ImageButton = findViewById(R.id.imageButton3)
        dataButton.setOnClickListener {
            // Mostra il widget CalendarView
            showCalendarDialog(dataButton)
        }
        val indietroButton: Button = findViewById(R.id.indietro)
        // Aggiungi un listener al bottone
        indietroButton.setOnClickListener {
            // Avvia un'attività per la schermata di registrazione
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showCalendarDialog(dataButton: ImageButton) {
        val calendarView = CalendarView(this)
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setView(calendarView)
            .setPositiveButton("OK") { dialog, _ ->
                selectedDate = calendarView.date
                updateImageButton(dataButton)
                dialog.dismiss()
            }
            .setNegativeButton("Annulla") { dialog, _ ->
                dialog.dismiss()
            }
            .setTitle("Seleziona una data")

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun updateImageButton(dataButton: ImageButton) {
        val dateString = dateFormat.format(Date(selectedDate))
        dataButton.contentDescription = dateString // Opzionale: Aggiorna la descrizione con la data
        // Aggiorna il testo o l'icona dell'ImageButton con la data selezionata
        dataButton.setImageResource(R.drawable.ic_calendar) // Se l'ImageButton è un ImageButton con un'icona personalizzata
    }
}