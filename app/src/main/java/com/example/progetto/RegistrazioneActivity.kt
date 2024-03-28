package com.example.progetto

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.sql.Date
import java.util.Calendar
import java.util.Locale

class RegistrazioneActivity : AppCompatActivity() {

    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private var selectedDate: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrazioneautonoma)
        val dataButton: ImageButton = findViewById(R.id.imageButton3)

        val intent = Intent(this, ContattiActivity::class.java)
        dataButton.setOnClickListener {
            // Mostra il widget CalendarView
           // showCalendarDialog(dataButton)
            showDatePickerDialog(dataButton)
        }
        val indietroButton: Button = findViewById(R.id.indietro)
        // Aggiungi un listener al bottone
        indietroButton.setOnClickListener {
            // Avvia un'attività per la schermata di registrazione
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val avantiButton: Button = findViewById(R.id.avanti)
        // Aggiungi un listener al bottone
        avantiButton.setOnClickListener {
            // Avvia un'attività per la schermata di registrazione
            // GESTIRE NULL POINTER EXCEPTION
            val nomeEditText: EditText = findViewById(R.id.Nome)
            val cognomeEditText: EditText = findViewById(R.id.Cognome)
            val dataNascitaEditText: EditText = findViewById(R.id.dataNascita)
            val emailEditText: EditText = findViewById(R.id.email)
            val passwordEditText: EditText = findViewById(R.id.password)

            val nome = nomeEditText.text.toString()
            val cognome = cognomeEditText.text.toString()
            val dataNascita = dataNascitaEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (nome.isNotBlank() && cognome.isNotBlank() && dataNascita.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
                val intent = Intent(this, ContattiActivity::class.java)
                intent.putExtra("nome", nome)
                intent.putExtra("cognome", cognome)
                intent.putExtra("dataNascita", dataNascita)
                intent.putExtra("email", email)
                intent.putExtra("password", password.hashCode())
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_SHORT).show()

                // Colora i campi vuoti di rosso
                if (nome.isEmpty()) {
                    nomeEditText.setBackgroundColor(Color.RED)
                }
                if (cognome.isEmpty()) {
                    cognomeEditText.setBackgroundColor(Color.RED)
                }
                if (dataNascita.isEmpty()) {
                    dataNascitaEditText.setBackgroundColor(Color.RED)
                }
                if (email.isEmpty()) {
                    emailEditText.setBackgroundColor(Color.RED)
                }
                if (password.isEmpty()) {
                    passwordEditText.setBackgroundColor(Color.RED)
                }
            }
        }
    }
    private fun showDatePickerDialog(dataButton: ImageButton) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val dataEditText: EditText = findViewById(R.id.dataNascita)
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                dataEditText.setText(selectedDate)
            },
            year,
            month,
            dayOfMonth
        )

        // Imposta il limite inferiore dell'anno selezionabile a 1900
        val minCalendar = Calendar.getInstance()
        minCalendar.set(1900, 0, 1) // Imposta il 1 gennaio 1900
        datePickerDialog.datePicker.minDate = minCalendar.timeInMillis
        // Mostra il dialogo
        datePickerDialog.show()
    }
    private fun showCalendarDialog(dataButton: ImageButton) {
        val calendarView = CalendarView(this)
        val dataEditText: EditText = findViewById(R.id.dataNascita)
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setView(calendarView)
            .setPositiveButton("OK") { dialog, _ ->
                //selectedDate = calendarView.date
                /*val selectedDateInMillis = calendarView.date
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selectedDateInMillis
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val selectedDate = dateFormat.format(calendar.time)
                dataEditText.setText(selectedDate)*/
                /*val selectedDateInMillis = calendarView.date
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val selectedDate = dateFormat.format(selectedDateInMillis)
                dataEditText.setText(selectedDate)
                dialog.dismiss()*/
               /* val selectedDateInMillis = calendarView.date
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selectedDateInMillis

                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH) + 1 // In Calendar.MONTH, January è 0, quindi aggiungiamo 1 per ottenere il mese corretto
                val year = calendar.get(Calendar.YEAR)

                val selectedDate = "$day/$month/$year"
                dataEditText.setText(selectedDate)*/

                dialog.dismiss()
            }
            .setNegativeButton("Annulla") { dialog, _ ->
                dialog.dismiss()
            }
            .setTitle("Seleziona una data")

        val alertDialog = alertDialogBuilder.create()
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year" // Mese è 0-based, quindi aggiungiamo 1
            dataEditText.setText(selectedDate)
            alertDialog.dismiss() // Chiude l'AlertDialog quando viene selezionata una data
        }
        alertDialog.show()
    }

    private fun updateImageButton(dataButton: ImageButton) {
        val dateString = dateFormat.format(Date(selectedDate))
        dataButton.contentDescription = dateString // Opzionale: Aggiorna la descrizione con la data
        // Aggiorna il testo o l'icona dell'ImageButton con la data selezionata
        dataButton.setImageResource(R.drawable.ic_calendar) // Se l'ImageButton è un ImageButton con un'icona personalizzata
    }
}