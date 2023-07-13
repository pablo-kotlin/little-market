package com.project.littlemarket.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.littlemarket.databinding.FragmentListArticlesBinding
import com.project.littlemarket.ui.adapter.ArticleAdapter
import com.project.littlemarket.ui.viewmodel.ArticleViewModel


class ListArticlesFragment : Fragment() {

    private lateinit var binding: FragmentListArticlesBinding
    private lateinit var adapter: ArticleAdapter
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instantiate your adapter
        adapter = ArticleAdapter()

        // Setup RecyclerView
        binding.articlesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.articlesRecyclerView.adapter = adapter

        // Get the ViewModel
        viewModel = ViewModelProvider(this)[ArticleViewModel::class.java]

        // Observe the data from the ViewModel
        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            // Update the data in the adapter when the observed data changes
            adapter.submitList(articles)
        }
    }

}


