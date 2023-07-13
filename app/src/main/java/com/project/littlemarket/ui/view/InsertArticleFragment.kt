package com.project.littlemarket.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.littlemarket.databinding.FragmentInsertArticleBinding

class InsertArticleFragment : Fragment() {

    private lateinit var binding: FragmentInsertArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

}