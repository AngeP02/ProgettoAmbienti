package com.example.progetto.Contatti

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.progetto.R

class ContattiFragment : Fragment() {

    private lateinit var viewModel: ContattiViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ContattiViewModel(requireContext().applicationContext)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contattifragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageViewPhone1 = view.findViewById<ImageView>(R.id.imageView)
        imageViewPhone1.setOnClickListener {
            // Prendi il numero di telefono dal TextView
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono1).text.toString()

            // Avvia un'intent implicito per aprire l'app del telefono
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up any resources or references here
    }
}