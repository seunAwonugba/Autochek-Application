package com.example.autochekapplication.dataclass.cardetails

data class InspectorDetails(
    val inspectedMakes: List<InspectedMake>,
    val inspectorFullName: String,
    val totalInspection: Int,
    val workshopName: String
)