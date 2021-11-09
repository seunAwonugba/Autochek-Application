package com.example.autochekapplication.api

import com.example.autochekapplication.constants.Constants.MAKE_END_POINT
import com.example.autochekapplication.dataclass.MakeDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET(MAKE_END_POINT)
    suspend fun getMakeList(
        @Query("popular")
        popular : String = "true"
    ) : Response<MakeDataClass>
}