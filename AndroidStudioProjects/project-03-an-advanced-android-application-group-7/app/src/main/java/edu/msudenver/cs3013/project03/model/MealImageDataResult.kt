package edu.msudenver.cs3013.project03.model

import com.squareup.moshi.Json
/*This class uses Moshi to interpret JSON data from API.
* This class expects a list and gets the url for an image and a name of the meal. */
data class MealImageDataResult(
    @field:Json(name = "strMealThumb") val imageUrl: String, //gets image url
    @field:Json(name = "strMeal") val strMeal: String, //gets meal name
    @field:Json(name = "strInstructions") val strInstructions: String
    )
fun MealImageDataResult.toMealItemUIModel(): MealItemUIModel {
    return MealItemUIModel(strMeal, imageUrl, strInstructions) //returns data from API
}


