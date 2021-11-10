package com.example.autochekapplication.repository

import com.example.autochekapplication.api.ApiServiceInterface
import com.example.autochekapplication.dataclass.cars.CarsDataClass
import com.example.autochekapplication.dataclass.makelist.MakeDataClass
import com.example.autochekapplication.db.CarsDao
import com.example.autochekapplication.util.ApiCallErrorHandler
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiInterface : ApiServiceInterface,
    private val carDao: CarsDao
) : TestRepository {
    override suspend fun getMakeList(popular: String): ApiCallErrorHandler<MakeDataClass> {
        return try {
            val receivedApiResponse = apiInterface.getMakeList(popular)
            val receivedApiResult = receivedApiResponse.body()

            if (receivedApiResponse.isSuccessful && receivedApiResult != null) {
                ApiCallErrorHandler.Success(receivedApiResult)
            } else {
                ApiCallErrorHandler.Error(receivedApiResponse.message())
            }
        } catch (e: Exception){
            ApiCallErrorHandler.Error(e.message ?: " An Error Occurred fetching data from the API ")
        }
    }

    override suspend fun getCars(): ApiCallErrorHandler<CarsDataClass> {
        return try {
            val receivedApiResponse = apiInterface.getCars()
            val receivedApiResult = receivedApiResponse.body()

            if (receivedApiResponse.isSuccessful && receivedApiResult != null) {
                ApiCallErrorHandler.Success(receivedApiResult)
            } else {
                ApiCallErrorHandler.Error(receivedApiResponse.message())
            }
        } catch (e: Exception){
            ApiCallErrorHandler.Error(e.message ?: " An Error Occurred fetching data from the API ")
        }
    }
}