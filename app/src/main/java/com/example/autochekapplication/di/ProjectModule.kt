package com.example.autochekapplication.di

import android.content.Context
import androidx.room.Room
import com.example.autochekapplication.api.ApiServiceInterface
import com.example.autochekapplication.constants.Constants.BASE_URL
import com.example.autochekapplication.constants.Constants.DATABASE_NAME
import com.example.autochekapplication.db.CarsDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProjectModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): ApiServiceInterface{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level= HttpLoggingInterceptor.Level.BODY
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiServiceInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideCarsDB(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CarsDB::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideCarsDao(carsDB: CarsDB) = carsDB.carsDao()
    

}