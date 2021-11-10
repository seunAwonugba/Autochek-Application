package com.example.autochekapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


}