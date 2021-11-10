package com.example.autochekapplication.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.autochekapplication.dataclass.cars.Result

@Dao
interface CarsDao {

    //insert and update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(carsResult : Result)

    //Function to retrieve data from database, its used to update views

    @Query("SELECT * FROM cars_table")
    fun getAllDataInDB() : LiveData<List<Result>>

    //Function to delete data from database

    @Delete
    suspend fun deleteCars(carsResult: Result)
}