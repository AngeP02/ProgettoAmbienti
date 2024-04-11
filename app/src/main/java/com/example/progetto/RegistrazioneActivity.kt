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
import java.text.ParseException
import java.util.Calendar
import java.util.Locale

class RegistrazioneActivity : AppCompatActivity() {

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

            val nomeEditText: EditText? = findViewById(R.id.Nome)
            val cognomeEditText: EditText? = findViewById(R.id.Cognome)
            val dataNascitaEditText: EditText? = findViewById(R.id.dataNascita)
            val emailEditText: EditText? = findViewById(R.id.email)
            val passwordEditText: EditText? = findViewById(R.id.password)
            val confermaPasswordEditText: EditText? = findViewById(R.id.confirm_password)

            if (nomeEditText != null && cognomeEditText != null && dataNascitaEditText != null && emailEditText != null && passwordEditText != null && confermaPasswordEditText != null) {
                val nome = nomeEditText.text.toString()
                val cognome = cognomeEditText.text.toString()
                val dataNascita = dataNascitaEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                val confermaPassword = confermaPasswordEditText.text.toString()


                    if (nome.isNotBlank() && cognome.isNotBlank() && dataNascita.isNotBlank() && email.isNotBlank() && password.isNotBlank() && confermaPassword.isNotBlank()) {
                        val intent = Intent(this, ContattiActivity::class.java)

                        // Definisci il pattern per il formato della data "dd/MM/yyyy" con il controllo del mese compreso tra 1 e 12
                        val pattern = """(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\d{4}"""
                        // Crea un oggetto Regex con il pattern definito
                        val regex = Regex(pattern)

                        val dataNascitaString = dataNascitaEditText.text.toString()

                        if (dataNascitaString.matches(regex)) {
                            // La stringa è nel formato data "dd/MM/yyyy" con il mese compreso tra 1 e 12
                            // Puoi procedere con altre operazioni
                            if (password == confermaPassword) {
                                intent.putExtra("nome", nome)
                                intent.putExtra("cognome", cognome)
                                intent.putExtra("dataNascita", dataNascita)
                                intent.putExtra("email", email)
                                intent.putExtra("password", password.hashCode())
                                startActivity(intent)
                            }
                            else{
                                Toast.makeText(this, "Le password non coincidono", Toast.LENGTH_LONG).show()
                            }
                        } else {
                            // La stringa non è nel formato corretto

                            // Puoi anche mostrare un messaggio di errore per informare l'utente che la data non è nel formato corretto
                            Toast.makeText(this, "Inserisci una data nel formato gg/mm/aaaa con il mese compreso tra 1 e 12", Toast.LENGTH_LONG).show()
                        }



                    } else {
                        Toast.makeText(this, "Compila tutti i campi", Toast.LENGTH_LONG).show()

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
}