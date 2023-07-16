package com.project.littlemarket.ui.view

//noinspection SuspiciousImport
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.project.littlemarket.data.model.Article
import com.project.littlemarket.databinding.FragmentModifyArticleBinding
import com.project.littlemarket.ui.viewmodel.ArticleViewModel

class ModifyArticleFragment : Fragment() {

    private lateinit var binding: FragmentModifyArticleBinding
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModifyArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get the ViewModel
        viewModel = ViewModelProvider(this)[ArticleViewModel::class.java]

        // Load the image into the ImageView
        Glide.with(this)
            .load("http://pabloruiztest02.getenjoyment.net/rest/cargarFotos/imagenes/64b461e8c7fe9.jpg")
            .into(binding.articleImage)

        // Observe the articles from the ViewModel
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            // Create a map with the article names as keys and the articles as values
            val articleMap = articles.associateBy { it.name }

            // Create an ArrayAdapter with the article names
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                articleMap.keys.toList()
            )

            // Set the ArrayAdapter to the AutoCompleteTextView
            binding.articleNameAutocomplete.setAdapter(adapter)

            // Initialize a variable to store the selected article
            var selectedArticle: Article? = null

            // Set an item click listener on the AutoCompleteTextView
            binding.articleNameAutocomplete.onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    // Get the selected article name
                    val selectedArticleName = parent.getItemAtPosition(position) as String

                    // Get the selected article using the map
                    selectedArticle = articleMap[selectedArticleName]

                    // Update the TextInputEditText fields with the article data
                    if (selectedArticle != null) {
                        binding.articleName.setText(selectedArticle!!.name)
                        binding.articlePrice.setText(selectedArticle!!.price.toString())
                        binding.articleDiscount.setText(selectedArticle!!.discount.toString())
                    }
                }

            // Set the click listener for the delete button
            binding.btnDeleteArticle.setOnClickListener {
                // Call the ViewModel function to delete the article
                if (selectedArticle != null) {
                    viewModel.deleteArticle(selectedArticle!!.id)
                } else {
                    // Show an error message if no article is selected
                    Toast.makeText(
                        context,
                        "Por favor, selecciona un artículo para eliminar",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            binding.btnModifyArticle.setOnClickListener {
                // Check if an article is selected
                if (selectedArticle != null) {
                    // Extract the text from the input fields
                    val articleName = binding.articleName.text.toString()
                    val articlePrice = binding.articlePrice.text.toString().toDoubleOrNull()
                    val articleDiscount = binding.articleDiscount.text.toString().toDoubleOrNull()

                    // Call the ViewModel function to modify the article
                    if (articleName.isNotBlank() && articlePrice != null && articleDiscount != null) {
                        viewModel.modifyArticle(
                            selectedArticle!!.id,
                            articleName,
                            articlePrice,
                            articleDiscount
                        )
                    } else {
                        // Show an error message if the input fields are not correctly filled
                        Toast.makeText(
                            context,
                            "Por favor, completa todos los campos correctamente",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    // Show an error message if no article is selected
                    Toast.makeText(
                        context,
                        "Por favor, selecciona un artículo para modificar",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }
}