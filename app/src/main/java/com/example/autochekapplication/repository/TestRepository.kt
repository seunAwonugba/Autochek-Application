package com.example.autochekapplication.repository

import com.example.autochekapplication.dataclass.cardetails.CarDetailsDataClass
import com.example.autochekapplication.dataclass.carmedia.CarMediaDataClass
import com.example.autochekapplication.dataclass.cars.CarsDataClass
import com.example.autochekapplication.dataclass.makelist.MakeDataClass
import com.example.autochekapplication.util.ApiCallErrorHandler

interface TestRepository {
    suspend fun getMakeList(popular : String) : ApiCallErrorHandler<MakeDataClass>

    suspend fun getCars() : ApiCallErrorHandler<CarsDataClass>

    suspend fun getCarMedia(carId : String) : ApiCallErrorHandler<CarMediaDataClass>

    suspend fun getCarDetails(carId : String) : ApiCallErrorHandler<CarDetailsDataClass>
}