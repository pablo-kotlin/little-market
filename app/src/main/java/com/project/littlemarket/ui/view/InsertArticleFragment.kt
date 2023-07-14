package com.project.littlemarket.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.littlemarket.data.model.Article
import com.project.littlemarket.databinding.FragmentInsertArticleBinding
import com.project.littlemarket.ui.viewmodel.ArticleViewModel

class InsertArticleFragment : Fragment() {

    private lateinit var binding: FragmentInsertArticleBinding
    private lateinit var viewModel: ArticleViewModel

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
            viewModel.insertArticle(Article(name, price, discount))
        }
    }


}