package com.example.autochekapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autochekapplication.adapter.MakeAdapter
import com.example.autochekapplication.databinding.FragmentDetailsBinding
import com.example.autochekapplication.databinding.FragmentHomeBinding
import com.example.autochekapplication.util.ApiCallErrorHandler
import com.example.autochekapplication.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

/**
 * To make android classes use Hilt, we have to annotate it with @AndroidEntryPoint
 */

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by viewModels()

    private lateinit var makeAdapter: MakeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        setUpRecyclerView()

        viewModel.makeResponse.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is ApiCallErrorHandler.Success ->  {
                    hideProgressBar()
                    response.data?.makeList.let {
                        if (it != null) {
                            makeAdapter.make = it
                        }
                    }
                }

                is ApiCallErrorHandler.Error ->{
                    hideProgressBar()
                    response.message?.let {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                }

                is ApiCallErrorHandler.Loading -> {
                    displayProgressBar()
                }
            }
        })


    }

    private fun displayProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.GONE
    }

    private fun setUpRecyclerView(){
        makeAdapter = MakeAdapter()
        binding.makeListRV.apply {
            adapter = makeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}