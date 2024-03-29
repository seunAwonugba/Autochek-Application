package com.example.autochekapplication.api

import com.example.autochekapplication.constants.Constants.CARS_END_POINT
import com.example.autochekapplication.constants.Constants.CARS_MEDIA_END_POINT
import com.example.autochekapplication.constants.Constants.MAKE_END_POINT
import com.example.autochekapplication.dataclass.cardetails.CarDetailsDataClass
import com.example.autochekapplication.dataclass.carmedia.CarMediaDataClass
import com.example.autochekapplication.dataclass.cars.CarsDataClass
import com.example.autochekapplication.dataclass.makelist.MakeDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET(MAKE_END_POINT)
    suspend fun getMakeList(
        @Query("popular")
        popular : String
    ) : Response<MakeDataClass>

    @GET(CARS_END_POINT)
    suspend fun getCars() : Response<CarsDataClass>

    @GET(CARS_MEDIA_END_POINT)
    suspend fun getCarMedia(
        @Query("carId")
        carId : String
    ) : Response<CarMediaDataClass>

    @GET("car/{carId}")
    suspend fun getCarDetails(
        @Path("carId")string: String
    ) : Response<CarDetailsDataClass>
}