package com.example.progetto.Farmaci

import android.app.AlertDialog
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
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

    //private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    //private val binding get() = _binding!!

   /* override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }*/



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Trova il TimePicker e impostalo per usare il formato 24 ore
        val timePicker = view.findViewById<TimePicker>(R.id.timePicker)
        timePicker.setIs24HourView(true)  // Imposta il formato 24 ore
        val imageViewAggiungi = view.findViewById<ImageView>(R.id.imageView13)
        imageViewAggiungi.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val inflater = LayoutInflater.from(requireContext())
            val dialogView = inflater.inflate(R.layout.popupinseriscifarmaco, null)

            builder.setView(dialogView)
                .setPositiveButton("Salva") { dialog, which ->
                    // Ottieni i riferimenti agli elementi nel popup
                    val editTextNomeFarmaco = dialogView.findViewById<EditText>(R.id.editTextNome)
                    val timePicker = dialogView.findViewById<TimePicker>(R.id.timePicker)

                    // Ottieni i valori inseriti dall'utente
                    val nomeFarmaco = editTextNomeFarmaco.text.toString()
                    val ora = getTimeFromTimePicker(timePicker)

                    // Aggiungi il nuovo elemento al fragment "Farmaci"
                    addFarmaco(nomeFarmaco, ora)
                }
                .setNegativeButton("Annulla") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun getTimeFromTimePicker(timePicker: TimePicker): String {
        val hour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.hour
        } else {
            timePicker.currentHour
        }
        val minute = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.minute
        } else {
            timePicker.currentMinute
        }

        return String.format("%02d:%02d", hour, minute)
    }

    private fun addFarmaco(nome: String, ora: String) {
        val constraintLayout = LayoutInflater.from(requireContext()).inflate(R.layout.itemfarmaco, null) as ConstraintLayout

        // Imposta il nome del farmaco
        constraintLayout.findViewById<TextView>(R.id.Nome1).text = nome
        // Imposta l'ora
        constraintLayout.findViewById<TextView>(R.id.Orario).text = ora

        // Ottieni il parent layout e aggiungi il nuovo constraint layout
        val parentLayout = requireView().findViewById<ConstraintLayout>(androidx.constraintlayout.widget.R.id.constraint)
        parentLayout.addView(constraintLayout)
    }


    override fun onDestroyView() {
        super.onDestroyView()
       // _binding = null
    }
/*
    val timePicker = findViewById<TimePicker>(R.id.timePicker)
    timePicker.setIs24HourView(true)
*/
}