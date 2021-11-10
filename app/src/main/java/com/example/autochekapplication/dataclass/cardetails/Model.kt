package com.example.autochekapplication.dataclass.cardetails

data class Model(
    val id: Int,
    val imageUrl: String,
    val make: Make,
    val modelFeatures: List<Any>,
    val name: String,
    val popular: Boolean,
    val series: String,
    val wheelType: String
)