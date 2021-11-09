package com.example.autochekapplication.repository

import com.example.autochekapplication.dataclass.cars.CarsDataClass
import com.example.autochekapplication.dataclass.makelist.MakeDataClass
import com.example.autochekapplication.util.ApiCallErrorHandler

interface TestRepository {
    suspend fun getMakeList(popular : String) : ApiCallErrorHandler<MakeDataClass>

    suspend fun getCars() : ApiCallErrorHandler<CarsDataClass>
}