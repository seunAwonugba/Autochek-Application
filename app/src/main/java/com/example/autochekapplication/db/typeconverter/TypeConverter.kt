package com.example.autochekapplication.db.typeconverter

import androidx.room.TypeConverter
import com.example.autochekapplication.dataclass.cars.Stats
import org.json.JSONObject

class TypeConverter {

    //store Stats data class to DB with the help of Type converters
    @TypeConverter
    fun convertStatsToJSONObject(stats : Stats) : String{
        return JSONObject().apply {
            put("appViewCount", stats.appViewCount)
            put("appViewerCount", stats.appViewerCount)
            put("interestCount", stats.interestCount)
            put("processedLoanCount", stats.processedLoanCount)
            put("testDriveCount", stats.testDriveCount)
            put("webViewCount", stats.webViewCount)
            put("webViewerCount", stats.webViewerCount)
        }.toString()
    }

    @TypeConverter
    fun convertJSONBackToStats(string: String) : Stats{
        val json = JSONObject(string)
        return Stats(
            json.optInt("appViewCount"),
            json.optInt("appViewerCount"),
            json.optInt("interestCount"),
            json.optInt("processedLoanCount"),
            json.optInt("testDriveCount"),
            json.optInt("webViewCount"),
            json.optInt("webViewerCount"),
        )
    }
}