package msudenver.edu.catagentprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import msudenver.edu.catagentprofile.api.TheCatApiService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(
                ScalarsConverterFactory.create()
            ).build()
    }
    private val theCatApiService by lazy {
        retrofit.create(TheCatApiService::class.java)
    }
}