package com.example.autochekapplication.dataclass.cars

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)