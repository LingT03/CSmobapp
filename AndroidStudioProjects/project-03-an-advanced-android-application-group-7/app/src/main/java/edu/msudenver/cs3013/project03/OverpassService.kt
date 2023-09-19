package edu.msudenver.cs3013.project03

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
/** Overpass class to get the service for Map. From project boilerplate code
 * */
interface OverpassService {
    //reaches out to API
    @GET("api/interpreter")
    suspend fun getParks(
        @Query("data") data: String
    ): OverpassResponse
}

//gets retrofit service
val retrofit = Retrofit.Builder()
    .baseUrl("https://overpass-api.de/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val service = retrofit.create(OverpassService::class.java)