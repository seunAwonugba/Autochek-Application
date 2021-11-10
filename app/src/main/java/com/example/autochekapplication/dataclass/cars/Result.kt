package com.example.autochekapplication.dataclass.cars

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cars_table"
)
data class Result(
    @PrimaryKey(autoGenerate = true)
    val roomId : Int? =null,
    val bodyTypeId: String?,
    val city: String?,
    val depositReceived: Boolean?,
    val gradeScore: Double?,
    val hasCleanTitle: Boolean?,
    val hasFinancing: Boolean?,
    val hasThreeDImage: Boolean?,
    val hasWarranty: Boolean?,
    val id: String?,
    val imageUrl: String?,
    val installment: Int?,
    val loanValue: Int?,
    val marketplaceOldPrice: Int?,
    val marketplacePrice: Int?,
    val mileage: Int?,
    val mileageUnit: String?,
    val sellingCondition: String?,
    val sold: Boolean?,
    val state: String?,
    val stats: Stats?,
    val title: String?,
    val websiteUrl: String?,
    val year: Int?
)