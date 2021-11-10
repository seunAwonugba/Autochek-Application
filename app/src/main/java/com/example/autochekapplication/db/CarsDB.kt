package com.example.autochekapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.autochekapplication.dataclass.cars.Result
import com.example.autochekapplication.db.typeconverter.TypeConverter

@Database(entities = [Result::class], version = 1)
@TypeConverters(TypeConverter::class)

abstract class CarsDB : RoomDatabase() {

    abstract fun carsDao(): CarsDao
}