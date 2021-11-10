package com.example.autochekapplication

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.autochekapplication.adapter.CarsAdapter
import com.example.autochekapplication.adapter.MakeAdapter
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
    private lateinit var carsAdapter: CarsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setUpRecyclerViewForMakeList()
        setUpRecyclerViewForCarsList()

        //observer for make list data
        viewModel.makeResponse.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is ApiCallErrorHandler.Success ->  {
                    hideMakeListProgressBar()
                    response.data?.makeList.let {
                        if (it != null) {
                            makeAdapter.make = it
                        }
                    }
                }

                is ApiCallErrorHandler.Error ->{
                    hideMakeListProgressBar()
                    response.message?.let {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                }

                is ApiCallErrorHandler.Loading -> {
                    displayMakeListProgressBar()
                }
            }
        })

        viewModel.getCars()
        //observer for cars data
        viewModel.carResponse.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is ApiCallErrorHandler.Success ->  {
                    hideCarsProgressBar()
                    response.data?.result.let {
                        if (it != null) {
                            carsAdapter.cars = it
                        }
                    }
                }

                is ApiCallErrorHandler.Error ->{
                    hideCarsProgressBar()
                    response.message?.let {
                        Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                    }
                }

                is ApiCallErrorHandler.Loading -> {
                    displayCarsProgressBar()
                }
            }
        })

        //implement navigation to details page
        carsAdapter.setListItemClickListener {
            val navDirection = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
            findNavController().navigate(navDirection)
        }
    }

    private fun displayMakeListProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideMakeListProgressBar(){
        binding.progressBar.visibility = View.GONE
    }

    private fun displayCarsProgressBar(){
        binding.progressBar2.visibility = View.VISIBLE
    }

    private fun hideCarsProgressBar(){
        binding.progressBar2.visibility = View.GONE
    }

    private fun setUpRecyclerViewForMakeList(){
        makeAdapter = MakeAdapter()
        binding.makeListRV.apply {
            adapter = makeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setUpRecyclerViewForCarsList(){
        carsAdapter = CarsAdapter()
        binding.carsRV.apply {
            adapter = carsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}