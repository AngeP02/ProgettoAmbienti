package com.example.progetto.Contatti

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.progetto.R
import android.provider.MediaStore
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.progetto.databinding.ContattifragmentBinding


class ContattiFragment : Fragment() {

    private lateinit var viewModel: ContattiViewModel

    private var _binding: ContattifragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var imageUri: Uri? = null
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
        //return inflater.inflate(R.layout.contattifragment, container, false)

        _binding = ContattifragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Controlla se il permesso è già stato concesso
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Richiedi il permesso
            requestPermissions(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1  // Codice di richiesta del permesso
            )}
        // Ottenere i nomi dai metodi del ViewModel
        var nomeContatto1 = viewModel.getNome1()
        var cognomeContatto1 = viewModel.getCognome1()
        var numeroTelefonoContatto1 = viewModel.getNumTelefono1()
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
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono1).text.toString()
            dispatchTakePictureIntent(phoneNumber)
        }
        val imageViewModify1 = view.findViewById<ImageView>(R.id.imageView3)
        imageViewModify1.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val inflater = LayoutInflater.from(requireContext())
            val dialogView = inflater.inflate(R.layout.popupmodificacontatti, null)

            // Trova i campi EditText nel layout del dialogo
            val editTextNome = dialogView.findViewById<EditText>(R.id.editTextNome)
            val editTextCognome = dialogView.findViewById<EditText>(R.id.editTextCognome)
            val editTextTelefono = dialogView.findViewById<EditText>(R.id.editTextTelefono)

            // Imposta i valori esistenti negli EditText
            editTextNome.setText(nomeContatto1)
            editTextCognome.setText(cognomeContatto1)
            editTextTelefono.setText(numeroTelefonoContatto1)

            builder.setView(dialogView)
                .setPositiveButton("Salva") { dialog, which ->
                    // Ottieni i nuovi valori dagli EditText
                    val newNome = editTextNome.text.toString()
                    val newCognome = editTextCognome.text.toString()
                    val newTelefono = editTextTelefono.text.toString()


                    textViewNome1.text = newNome
                    textViewCognome1.text = newCognome
                    textViewNumTelefono1.text = newTelefono

                    viewModel.setNome1(newNome)
                    viewModel.setCognome1(newCognome)
                    viewModel.setNumTelefono1(newTelefono)

                }
                .setNegativeButton("Annulla") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }

        var nomeContatto2 = viewModel.getNome2()
        var cognomeContatto2 = viewModel.getCognome2()
        var numeroTelefonoContatto2 = viewModel.getNumTelefono2()
        val textViewNome2 = view.findViewById<TextView>(R.id.Nome2)
        textViewNome2.text = nomeContatto2
        val textViewCognome2 = view.findViewById<TextView>(R.id.Cognome2)
        textViewCognome2.text = cognomeContatto2
        val textViewNumTelefono2 = view.findViewById<TextView>(R.id.NumTelefono2)
        textViewNumTelefono2.text = numeroTelefonoContatto2

        val imageViewPhone2 = view.findViewById<ImageView>(R.id.imageView5)
        imageViewPhone2.setOnClickListener {
            // Prendi il numero di telefono dal TextView
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono2).text.toString()
            if (phoneNumber != ""){
                // Avvia un'intent implicito per aprire l'app del telefono
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$phoneNumber")
                startActivity(intent)
            }
            else{
                Toast.makeText(requireActivity(), "Nessun numero di telefono presente", Toast.LENGTH_SHORT).show()

            }
        }
        val imageViewMessage2 = view.findViewById<ImageView>(R.id.imageView8)
        imageViewMessage2.setOnClickListener {
            // Prendi il numero di telefono dal TextView
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono2).text.toString()
            if (phoneNumber != "") {
                // Avvia un'intent implicito per aprire l'app dei messaggi
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data =
                    Uri.parse("smsto:")  // Specifica l'azione per avviare l'app dei messaggi

                // Aggiungi il numero di telefono come dati nell'intent
                intent.putExtra("address", phoneNumber)

                // Avvia l'intent
                startActivity(intent)
            }
            else{
                Toast.makeText(requireActivity(), "Nessun numero di telefono presente", Toast.LENGTH_SHORT).show()

            }
        }
        val imageViewCam2 = view.findViewById<ImageView>(R.id.imageView6)
        imageViewCam2.setOnClickListener {
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono2).text.toString()
            if (phoneNumber != "") {
                val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono2).text.toString()
                dispatchTakePictureIntent(phoneNumber)
            }
            else{
                Toast.makeText(requireActivity(), "Nessun numero di telefono presente", Toast.LENGTH_SHORT).show()

            }
        }
        val imageViewModify2 = view.findViewById<ImageView>(R.id.imageView7)
        imageViewModify2.setOnClickListener {

                val builder = AlertDialog.Builder(requireContext())
                val inflater = LayoutInflater.from(requireContext())
                val dialogView = inflater.inflate(R.layout.popupmodificacontatti, null)

                // Trova i campi EditText nel layout del dialogo
                val editTextNome = dialogView.findViewById<EditText>(R.id.editTextNome)
                val editTextCognome = dialogView.findViewById<EditText>(R.id.editTextCognome)
                val editTextTelefono = dialogView.findViewById<EditText>(R.id.editTextTelefono)

                // Imposta i valori esistenti negli EditText
                editTextNome.setText(nomeContatto2)
                editTextCognome.setText(cognomeContatto2)
                editTextTelefono.setText(numeroTelefonoContatto2)

                builder.setView(dialogView)
                    .setPositiveButton("Salva") { dialog, which ->
                        // Ottieni i nuovi valori dagli EditText
                        val newNome = editTextNome.text.toString()
                        val newCognome = editTextCognome.text.toString()
                        val newTelefono = editTextTelefono.text.toString()

                        textViewNome2.text = newNome
                        textViewCognome2.text = newCognome
                        textViewNumTelefono2.text = newTelefono

                        viewModel.setNome2(newNome)
                        viewModel.setCognome2(newCognome)
                        viewModel.setNumTelefono2(newTelefono)

                    }
                    .setNegativeButton("Annulla") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()


        }

        var nomeContatto3 = viewModel.getNome3()
        var cognomeContatto3 = viewModel.getCognome3()
        var numeroTelefonoContatto3 = viewModel.getNumTelefono3()
        val textViewNome3 = view.findViewById<TextView>(R.id.Nome3)
        textViewNome3.text = nomeContatto3
        val textViewCognome3 = view.findViewById<TextView>(R.id.Cognome3)
        textViewCognome3.text = cognomeContatto3
        val textViewNumTelefono3 = view.findViewById<TextView>(R.id.NumTelefono3)
        textViewNumTelefono3.text = numeroTelefonoContatto3
        val imageViewPhone3 = view.findViewById<ImageView>(R.id.imageView9)
        imageViewPhone3.setOnClickListener {
            // Prendi il numero di telefono dal TextView
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono3).text.toString()
            if (phoneNumber != ""){
                // Avvia un'intent implicito per aprire l'app del telefono
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:$phoneNumber")
                startActivity(intent)
            }
            else{
                Toast.makeText(requireActivity(), "Nessun numero di telefono presente", Toast.LENGTH_SHORT).show()

            }
        }
        val imageViewMessage3 = view.findViewById<ImageView>(R.id.imageView12)
        imageViewMessage3.setOnClickListener {
            // Prendi il numero di telefono dal TextView
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono3).text.toString()
            if (phoneNumber != "") {
                // Avvia un'intent implicito per aprire l'app dei messaggi
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data =
                    Uri.parse("smsto:")  // Specifica l'azione per avviare l'app dei messaggi

                // Aggiungi il numero di telefono come dati nell'intent
                intent.putExtra("address", phoneNumber)

                // Avvia l'intent
                startActivity(intent)
            }
            else{
                Toast.makeText(requireActivity(), "Nessun numero di telefono presente", Toast.LENGTH_SHORT).show()

            }
        }
        val imageViewCam3 = view.findViewById<ImageView>(R.id.imageView10)
        imageViewCam3.setOnClickListener {
            val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono3).text.toString()
            if (phoneNumber != "") {
                val phoneNumber = view.findViewById<TextView>(R.id.NumTelefono3).text.toString()
                dispatchTakePictureIntent(phoneNumber)
            }
            else{
                Toast.makeText(requireActivity(), "Nessun numero di telefono presente", Toast.LENGTH_SHORT).show()

            }
        }
        val imageViewModify3 = view.findViewById<ImageView>(R.id.imageView11)
        imageViewModify3.setOnClickListener {

                val builder = AlertDialog.Builder(requireContext())
                val inflater = LayoutInflater.from(requireContext())
                val dialogView = inflater.inflate(R.layout.popupmodificacontatti, null)

                // Trova i campi EditText nel layout del dialogo
                val editTextNome = dialogView.findViewById<EditText>(R.id.editTextNome)
                val editTextCognome = dialogView.findViewById<EditText>(R.id.editTextCognome)
                val editTextTelefono = dialogView.findViewById<EditText>(R.id.editTextTelefono)

                // Imposta i valori esistenti negli EditText
                editTextNome.setText(nomeContatto3)
                editTextCognome.setText(cognomeContatto3)
                editTextTelefono.setText(numeroTelefonoContatto3)

                builder.setView(dialogView)
                    .setPositiveButton("Salva") { dialog, which ->
                        // Ottieni i nuovi valori dagli EditText
                        val newNome = editTextNome.text.toString()
                        val newCognome = editTextCognome.text.toString()
                        val newTelefono = editTextTelefono.text.toString()

                        textViewNome3.text = newNome
                        textViewCognome3.text = newCognome
                        textViewNumTelefono3.text = newTelefono

                        viewModel.setNome3(newNome)
                        viewModel.setCognome3(newCognome)
                        viewModel.setNumTelefono3(newTelefono)

                    }
                    .setNegativeButton("Annulla") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()

        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Il permesso è stato concesso
                // Puoi ora accedere alla memoria
            } else {
                // Il permesso è stato negato
                // Gestisci il caso in cui l'utente rifiuta il permesso
            }
        }
    }

    private fun dispatchTakePictureIntent(phoneNumber: String) {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            val phoneNumber = view?.findViewById<TextView>(R.id.NumTelefono1)?.text.toString()
            if (!phoneNumber.isNullOrEmpty()) {
                val imageUri = saveImageToCache(imageBitmap)
                if (imageUri != null) {
                    openMessageAppWithImage(imageUri, phoneNumber)
                }
            } else {
                // Gestisci il caso in cui non viene fornito alcun numero di telefono
                Toast.makeText(requireActivity(), "Numero di telefono non valido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveImageToCache(bitmap: Bitmap): Uri? {
        val wrapper = ContextWrapper(requireContext().applicationContext)
        val file = File(wrapper.cacheDir, "image.jpg")

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return FileProvider.getUriForFile(requireContext(), "com.example.progetto.fileprovider", file)
    }

    private fun openMessageAppWithImage(imageUri: Uri, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra("address", phoneNumber)
        intent.putExtra(Intent.EXTRA_STREAM, imageUri)

// Usa Intent.createChooser per lasciare che l'utente scelga l'app
        startActivity(Intent.createChooser(intent, "Scegli l'app di messaggi"))

    }
        /*val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra("sms_body", "")
        intent.putExtra(Intent.EXTRA_STREAM, imageUri)
        intent.putExtra("address", phoneNumber) // Numero di telefono come destinatario
        intent.type = "image/*"

        // Impostiamo il pacchetto dell'applicazione dei messaggi per avviare direttamente quell'app
        intent.setPackage("com.google.android.apps.messaging")

        startActivity(intent)

    }
   */

         */


    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up any resources or references here
        _binding = null
    }
}