package com.example.autochekapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.autochekapplication.adapter.ViewPagerAdapter
import com.example.autochekapplication.databinding.FragmentDetailsBinding
import com.example.autochekapplication.util.ApiCallErrorHandler
import com.example.autochekapplication.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    //receive args passed from the home fragment
    private val passedArgs : DetailsFragmentArgs by navArgs()

    private val viewModel : MainViewModel by viewModels()

    private lateinit var viewPager : ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        viewPager = binding.viewPagerId



        //Implement back navigation
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }
        

        //passed data
        val passedData = passedArgs.passedDetailsArgs

        //set title to title text view
        binding.titleTvId.text = passedData.title

        //get car id
        var carId = passedData.id

        //car media observer
        if (carId != null) {
            viewModel.getCarMedia(carId)
            viewModel.carMediaResponse.observe(viewLifecycleOwner, Observer {response ->
                when(response){
                    is ApiCallErrorHandler.Success ->  {
                        response.data?.carMediaList.let {
                            if (it != null) {
                                Log.d("TAG", it.toString())
                                val adapter = ViewPagerAdapter(it)
                                viewPager.adapter = adapter
                            }
                        }
                    }

                    is ApiCallErrorHandler.Error ->{
                        response.message?.let {
                            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                        }
                    }

                    is ApiCallErrorHandler.Loading -> {
                    }
                }

            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}