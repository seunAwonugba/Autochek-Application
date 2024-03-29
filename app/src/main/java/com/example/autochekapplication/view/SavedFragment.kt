package com.example.autochekapplication.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.autochekapplication.R
import com.example.autochekapplication.databinding.FragmentSavedBinding
import com.example.autochekapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * To make android classes use Hilt, we have to annotate it with @AndroidEntryPoint
 */

@AndroidEntryPoint
class SavedFragment : Fragment(R.layout.fragment_saved) {
    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSavedBinding.bind(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}