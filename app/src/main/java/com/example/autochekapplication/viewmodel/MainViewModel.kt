package com.example.autochekapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.autochekapplication.dataclass.cardetails.CarDetailsDataClass
import com.example.autochekapplication.dataclass.carmedia.CarMediaDataClass
import com.example.autochekapplication.dataclass.cars.CarsDataClass
import com.example.autochekapplication.dataclass.makelist.MakeDataClass
import com.example.autochekapplication.repository.TestRepository
import com.example.autochekapplication.util.ApiCallErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var repository : TestRepository
) : ViewModel() {

    private val _makeResponse : MutableLiveData<ApiCallErrorHandler<MakeDataClass>> = MutableLiveData()
    var makeResponse : LiveData<ApiCallErrorHandler<MakeDataClass>> = _makeResponse

    private val _carResponse : MutableLiveData<ApiCallErrorHandler<CarsDataClass>> = MutableLiveData()
    var carResponse : LiveData<ApiCallErrorHandler<CarsDataClass>> = _carResponse

    private val _carMediaResponse : MutableLiveData<ApiCallErrorHandler<CarMediaDataClass>> = MutableLiveData()
    var carMediaResponse : LiveData<ApiCallErrorHandler<CarMediaDataClass>> = _carMediaResponse

    private val _carDetailsResponse : MutableLiveData<ApiCallErrorHandler<CarDetailsDataClass>> = MutableLiveData()
    var carDetailsResponse : LiveData<ApiCallErrorHandler<CarDetailsDataClass>> = _carDetailsResponse

    init {
        getMakeList("true")
    }

    private fun getMakeList(popular : String){
        viewModelScope.launch {
            _makeResponse.postValue(ApiCallErrorHandler.Loading())
            val makeResponseInCoroutine = repository.getMakeList(popular)
            _makeResponse.postValue(makeResponseInCoroutine)
        }
    }

    fun getCars(){
        viewModelScope.launch {
            _carResponse.postValue(ApiCallErrorHandler.Loading())
            val carsResponseInCoroutine = repository.getCars()
            _carResponse.postValue(carsResponseInCoroutine)
        }
    }

    fun getCarMedia(carId : String){
        viewModelScope.launch {
            _carMediaResponse.postValue(ApiCallErrorHandler.Loading())
            val carMediaResponseInCoroutine = repository.getCarMedia(carId)
            _carMediaResponse.postValue(carMediaResponseInCoroutine)
        }
    }

    fun getCarDetails(carId : String){
        viewModelScope.launch {
            _carDetailsResponse.postValue(ApiCallErrorHandler.Loading())
            val carDetailsResponseInCoroutine = repository.getCarDetails(carId)
            _carDetailsResponse.postValue(carDetailsResponseInCoroutine)
        }
    }




}