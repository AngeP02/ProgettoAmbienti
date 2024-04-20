package com.example.progetto.Contatti

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
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
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


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
        // Ottenere i nomi dai metodi del ViewModel
        val nomeContatto1 = viewModel.getNome1()
        val cognomeContatto1 = viewModel.getCognome1()
        val numeroTelefonoContatto1 = viewModel.getNumTelefono1()

        // Trovare i TextView nel layout e impostare i loro valori
        val textViewNome1 = view.findViewById<TextView>(R.id.Nome1)
        textViewNome1.text = nomeContatto1

        val textViewCognome1 = view.findViewById<TextView>(R.id.Cognome1)
        textViewCognome1.text = cognomeContatto1

        val textViewNumTelefono1 = view.findViewById<TextView>(R.id.NumTelefono1)
        textViewNumTelefono1.text = numeroTelefonoContatto1

        val imageViewPhone1 = view.findViewById<ImageView>(R.id.imageView)
        imageViewPhone1.setOnClickListener {
            // Prendi il numero di telefono dal TextView
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono1).text.toString()

            // Avvia un'intent implicito per aprire l'app del telefono
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
        val imageViewMessage1 = view.findViewById<ImageView>(R.id.imageView4)
        imageViewMessage1.setOnClickListener {
            // Prendi il numero di telefono dal TextView
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono1).text.toString()

            // Avvia un'intent implicito per aprire l'app dei messaggi
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("smsto:")  // Specifica l'azione per avviare l'app dei messaggi

            // Aggiungi il numero di telefono come dati nell'intent
            intent.putExtra("address", phoneNumber)

            // Avvia l'intent
            startActivity(intent)
        }
        val imageViewCam1 = view.findViewById<ImageView>(R.id.imageView2)
        imageViewCam1.setOnClickListener {
            dispatchTakePictureIntent()
        }

        // DA FARE L'IMMAGINE DI MODIFICA CON IL POPUP DIALOG
        // E PER GLI ALTRI FARE IL CONTROLLO SE SONO NULL

    }
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            sendMMS(imageBitmap)
        }
    }

    private fun sendMMS(bitmap: Bitmap) {
        val phoneNumber = view?.findViewById<TextView>(R.id.NumTelefono1)?.text.toString()
        val uri = saveImageToExternalStorage(bitmap)

        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra("sms_body", "Messaggio con immagine")
        intent.putExtra("address", phoneNumber)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.type = "image/*"
        startActivity(intent)
    }

    private fun saveImageToExternalStorage(bitmap: Bitmap): Uri? {
        val wrapper = ContextWrapper(requireContext().applicationContext)
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)
        file = File(file, "unique_filename.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return Uri.parse(file.absolutePath)
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up any resources or references here
    }
}