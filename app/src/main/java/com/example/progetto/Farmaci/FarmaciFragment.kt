package com.example.progetto.Farmaci

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Switch
import android.widget.TextView
import android.widget.TimePicker
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.progetto.Contatti.ContattiViewModel
import com.example.progetto.R
//import com.example.progetto.databinding.FragmentNotificationsBinding

class FarmaciFragment: Fragment() {


    private lateinit var viewModel: FarmaciViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = FarmaciViewModel(requireContext().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.farmacifragment, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageViewAggiungi = view.findViewById<ImageView>(R.id.imageView13)
        imageViewAggiungi.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.popupinseriscifarmaco, null)
            val timePicker = dialogView.findViewById<TimePicker>(R.id.timePicker)
            if (timePicker != null) {
                timePicker.setIs24HourView(true)  // Imposta il formato 24 ore
            }

            builder.setView(dialogView)
                .setPositiveButton("Salva") { dialog, which ->
                    val editTextNomeFarmaco = dialogView.findViewById<EditText>(R.id.editTextNome)
                    val nomeFarmaco = editTextNomeFarmaco.text.toString()
                    val ora = if (timePicker != null) {
                        getTimeFromTimePicker(timePicker)
                    } else {
                        "00:00" // valore predefinito se il timePicker non è trovato
                    }

                    // Aggiungi il nuovo elemento al fragment "Farmaci"
                    addFarmaco(nomeFarmaco, ora)
                }
                .setNegativeButton("Annulla") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }

        // Trova il layout principale dove vengono aggiunti i farmaci
        val parentLayout = view.findViewById<ConstraintLayout>(R.id.scrollView1)  // Assicurati che questo sia un ConstraintLayout

        // Assicurati che il parentLayout sia stato trovato
        if (parentLayout == null) {
            Log.e("FarmaciFragment", "Parent layout non trovato")
            return
        }


        for (i in 0 until parentLayout.childCount) {
            val child = parentLayout.getChildAt(i) // Ogni elemento nel parent layout

            // Trova il pulsante "Elimina" all'interno del `ConstraintLayout`
            val deleteButton = child.findViewById<ImageView>(R.id.elimina)

            // Assicurati che il pulsante "Elimina" esista
            if (deleteButton != null) {
                deleteButton.setOnClickListener {
                    // Passa il `ConstraintLayout` da rimuovere
                    removeFarmaco(child as ConstraintLayout)
                }
            }
        }
    }


    private fun updateLayoutConstraints(parentLayout: ConstraintLayout) {
        for (i in 0 until parentLayout.childCount) {
            val child = parentLayout.getChildAt(i)

            if (child is ConstraintLayout) { // Verifica che sia un ConstraintLayout
                val layoutParams = child.layoutParams as ConstraintLayout.LayoutParams

                if (i == 0) {
                    layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    layoutParams.topToBottom = ConstraintLayout.LayoutParams.UNSET
                } else {
                    val previousChild = parentLayout.getChildAt(i - 1) as ConstraintLayout
                    layoutParams.topToBottom = previousChild.id
                    layoutParams.topToTop = ConstraintLayout.LayoutParams.UNSET
                }

                // Applica i nuovi layoutParams
                child.layoutParams = layoutParams
            }
        }
    }




    private fun getTimeFromTimePicker(timePicker: TimePicker): String {
        val hour = timePicker.hour
        val minute = timePicker.minute

        return String.format("%02d:%02d", hour, minute)
    }
    private fun addFarmaco(nome: String, ora: String) {
        // Inflazione del layout del riquadro da aggiungere
        val newFarmacoView = LayoutInflater.from(requireContext())
            .inflate(R.layout.farmaco_nome, null) as ConstraintLayout

        // Imposta il nome del farmaco
        val nomeTextView = newFarmacoView.findViewById<TextView>(R.id.farmacoNome)
        nomeTextView.text = nome

        // Imposta l'orario
        val oraTextView = newFarmacoView.findViewById<TextView>(R.id.farmacoOrario)
        oraTextView.text = ora

        // Ottieni il parent layout in cui aggiungere il nuovo riquadro
        val parentLayout = requireView().findViewById<ConstraintLayout>(R.id.scrollView1)
// Trova il pulsante "Elimina" e aggiungi il listener
        val deleteButton = newFarmacoView.findViewById<ImageView>(R.id.elimina)
        deleteButton.setOnClickListener {
            parentLayout.removeView(newFarmacoView)  // Rimuovi il nuovo ConstraintLayout
        }
        if (parentLayout != null) {
            val previousViewId = if (parentLayout.childCount > 0) {
                parentLayout.getChildAt(parentLayout.childCount - 1).id
            } else {
                0
            }

            // Imposta il nuovo ConstraintLayout con vincoli rispetto all'elemento precedente
            if (previousViewId > 0) {
                newFarmacoView.id = View.generateViewId()  // Genera un ID univoco
                newFarmacoView.layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topToBottom = previousViewId  // Collegati all'elemento precedente
                }
            }

            parentLayout.addView(newFarmacoView)  // Aggiungi il riquadro al ConstraintLayout interno
        } else {
            Log.e("FarmaciFragment", "Parent layout non trovato")
        }
    }
    private fun removeFarmaco(farmacoView: ConstraintLayout) {
        val parentLayout = requireView().findViewById<ConstraintLayout>(R.id.scrollView1)

        // Rimuovi l'elemento
        parentLayout.removeView(farmacoView)

        // Aggiorna i vincoli
        updateLayoutConstraints(parentLayout)
    }





    override fun onDestroyView() {
        super.onDestroyView()
    }

}