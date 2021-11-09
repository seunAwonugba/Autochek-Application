package com.example.autochekapplication.dataclass.makelist

data class Pagination(
    val currentPage: Int,
    val pageSize: Int,
    val total: Int
)