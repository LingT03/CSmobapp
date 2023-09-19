package edu.msudenver.cs3013.project03.model

import com.squareup.moshi.Json

/*This class uses Moshi to interpret JSON data from API.
* This class expects an object. */
data class MealData(
    @field:Json(name = "meals") val meals: List<MealImageDataResult>
)
