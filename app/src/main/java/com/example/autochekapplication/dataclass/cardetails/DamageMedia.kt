package com.example.autochekapplication.dataclass.cardetails

data class DamageMedia(
    val comment: String,
    val inspectionItems: List<InspectionItem>,
    val name: String
)