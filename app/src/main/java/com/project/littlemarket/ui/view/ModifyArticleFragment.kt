package com.project.littlemarket.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.project.littlemarket.databinding.FragmentModifyArticleBinding

class ModifyArticleFragment : Fragment() {

    private lateinit var binding: FragmentModifyArticleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentModifyArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

}