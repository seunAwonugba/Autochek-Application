package com.example.autochekapplication.dataclass

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)