package edu.msudenver.cs3013.project03.api

import edu.msudenver.cs3013.project03.model.MealData
import retrofit2.Call
import retrofit2.http.GET

//interface to connect to API using retrofit
interface MealAPIService {
    @GET("images/search")
    fun searchImages(): //searches for image
            Call<MealData>
}