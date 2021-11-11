package com.example.autochekapplication.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.autochekapplication.R
import com.example.autochekapplication.adapter.ViewPagerAdapter
import com.example.autochekapplication.databinding.FragmentDetailsBinding
import com.example.autochekapplication.util.ApiCallErrorHandler
import com.example.autochekapplication.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.String

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

        //implement cars media view pager
        viewPager = binding.viewPagerId
        viewPager.offscreenPageLimit = 2
        viewPager.clipToPadding = false
        viewPager.clipChildren = false
        viewPager.setPadding(60,0,60,0)

        val compositePageTransformer = CompositePageTransformer()

        compositePageTransformer.addTransformer { page, position ->
            page.scaleY = 1 - (0.25f * Math.abs(position))
        }

        viewPager.setPageTransformer(compositePageTransformer)

        //Implement back navigation
        binding.backArrow.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }
        

        //passed data
        val passedData = passedArgs.passedDetailsArgs
        //received price
        var price = passedData.marketplacePrice

        //set title to title text view
        binding.titleTvId.text = passedData.title
        val formattedNumber = String.format("%,d", price)
        binding.priceId.text = "₦${formattedNumber}.00"

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

        //car details observer
        if (carId != null) {
            viewModel.getCarDetails(carId)
            viewModel.carDetailsResponse.observe(viewLifecycleOwner, Observer { response->
                when(response){
                    is ApiCallErrorHandler.Success ->{
                        binding.MileageId.text = response.data?.mileage.toString()
                        binding.engineTypeId.text = response.data?.engineType
                        binding.fuelTypeId.text = response.data?.fuelType
                        binding.wheelTypeId.text = response.data?.model?.wheelType
                        binding.interiorColorId.text = response.data?.interiorColor
                        binding.TransmissionId.text = response.data?.transmission
                        binding.unitId.text = response.data?.mileageUnit
                        binding.seriesId.text = response.data?.model?.series
                        binding.conditionId.text = response.data?.sellingCondition
                        binding.vinId.text = response.data?.vin
                        binding.locationId.text = response.data?.state
                        binding.inspectedId.text = response.data?.inspectorDetails?.workshopName
                    }

                    is ApiCallErrorHandler.Error -> {
                        response.message?.let {
                            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
                        }
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