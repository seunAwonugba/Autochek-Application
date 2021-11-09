package com.example.autochekapplication.repository

import com.example.autochekapplication.dataclass.MakeDataClass
import com.example.autochekapplication.util.ApiCallErrorHandler

interface TestRepository {
    suspend fun getMakeList(popular : String) : ApiCallErrorHandler<MakeDataClass>
}