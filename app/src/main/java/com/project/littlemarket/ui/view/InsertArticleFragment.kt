package com.project.littlemarket.ui.view

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.project.littlemarket.data.model.Article
import com.project.littlemarket.databinding.FragmentInsertArticleBinding
import com.project.littlemarket.ui.viewmodel.ArticleViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class InsertArticleFragment : Fragment() {

    private lateinit var binding: FragmentInsertArticleBinding
    private lateinit var viewModel: ArticleViewModel

    private val REQUEST_PERMISSION = 1
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the ViewModel
        viewModel = ViewModelProvider(this)[ArticleViewModel::class.java]

        // Set onClickListener to handle user interactions
        binding.btnSaveArticle.setOnClickListener {
            val name = binding.productNameInputLayout.editText?.text.toString()
            val price = binding.priceInputLayout.editText?.text.toString().toDouble()
            val discount = binding.discountInputLayout.editText?.text.toString().toDouble()

            // Create a new Article and insert it into the database
            viewModel.insertArticle(Article(0, name, price, discount))
        }

        val uploadButton = binding.btnUploadPhoto
        uploadButton.setOnClickListener {
            if (checkPermission()) {
                pickImageFromGallery()
            } else {
                requestPermission()
            }
        }
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_PERMISSION
        )
    }

    private val pickImageLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data?.data?.let { uri ->
                    selectedImageUri = uri
                    uploadPhoto()
                }
            }
        }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }

    private fun uploadPhoto() {
        // lanzar una corutina en IO Dispatcher
        lifecycleScope.launch(Dispatchers.IO) {
            selectedImageUri?.let { uri ->
                val filePath = getRealPathFromUri(uri)
                val file = File(filePath!!)

                // Convertir la imagen a un array de bytes
                val imageBytes: ByteArray = file.readBytes()

                // Imprimir el array de bytes en el log
                Log.d("Upload", "Image bytes: ${imageBytes.contentToString()}")

                // Volvemos al hilo principal para actualizar la UI
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        requireContext(),
                        "Photo converted successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Ocultar la ProgressBar una vez que la carga se haya completado
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun getRealPathFromUri(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(uri, projection, null, null, null)
        return cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            it.moveToFirst()
            it.getString(columnIndex)
        }
    }

}