package edu.msudenver.cs3013.project03

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import edu.msudenver.cs3013.project03.api.MealAPIService
import edu.msudenver.cs3013.project03.model.MealData
import edu.msudenver.cs3013.project03.model.MealItemUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*Activity that displays a recycler view and calls an API to fill recycler view list. */
class MealBrowserActivity : AppCompatActivity() {
    //initialize recycler view
    private val recyclerView: RecyclerView
            by lazy { findViewById(R.id.recycler_view) }

    //initialize adapter
    private val mealsAdapter by lazy {
        ListItemsAdapter(
            layoutInflater,
            GlideImageLoader(this),
            object : ListItemsAdapter.OnClickListener {
                override fun onItemClick(mealItemUIModel: MealItemUIModel) = showSelectionDialog(mealItemUIModel)
            }
        )
    }

    //create refresh button
    private val button: Button by lazy {
        findViewById(R.id.refresh_meal_button)
    }
    private val instruction_button: Button by lazy {
        findViewById(R.id.instruction_button)
    }

    //create new textview for meal name
    private val mealView: TextView by lazy {
        findViewById(R.id.item_meal_name)
    }

    //create new imageview for item image
    private val mealImageView: ImageView by lazy {
        findViewById(R.id.item_meal_url)
    }

    //initialize API interface
    private val mealAPIServiceInterface by lazy { retrofit.create(MealAPIService::class.java) }

    //Initialize image interface
    private val imageLoader: ImageLoader by lazy {
        GlideImageLoader(this)
    }

    //initialize retrofit
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/random.php/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_browser) //set content
        recyclerView.adapter = mealsAdapter //set adapter to recyclerview adapter
        recyclerView.layoutManager = GridLayoutManager(this, 2) //sets layout to two columns
        val itemTouchHelper = ItemTouchHelper(mealsAdapter.swipeToDeleteCallback) //needed for interaction with viewholders
        itemTouchHelper.attachToRecyclerView(recyclerView)
        getMealImageResponses() //actual API call
        mealsAdapter.setData( //sets data into viewholders
            listOf(
                MealItemUIModel(
                    strMeal = "",
                strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMealThumb = "",
                    strMeal = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
                MealItemUIModel(
                    strMeal = "",
                    strMealThumb = "",
                    strInstructions = ""
                ),
            )
        )

        //resets meal options using button
        button.setOnClickListener {
            getMealImageResponses()
        }

    instruction_button.setOnClickListener {
        val pickIntent = Intent(
            this,
            MainActivity::class.java
        )
        startActivity(pickIntent)//starts new activity
    }

}

    //method for calling API and getting data
    private fun getMealImageResponses() {
        val numberOfRequests = 18 //sets amount of calls to API due to nature of API response
        val callList = List(numberOfRequests) {//creates a list of size number of requests
            mealAPIServiceInterface.searchImages()
        }

        callList.forEachIndexed { index, call ->
            call.clone().enqueue(object : Callback<MealData> { //calls API
                override fun onFailure(call: Call<MealData>, t: Throwable) { //alerts that call failed
                    Log.e("MealBrowseActivity", "Failed to get search results", t)
                }

                override fun onResponse(call: Call<MealData>, response: Response<MealData>) {
                    if (response.isSuccessful) {
                        val mealData = response.body() //gets JSON response
                        val mealImageDataResult = mealData?.meals?.firstOrNull() //gets result if not null
                        if (mealImageDataResult != null) { //checks if null
                            val imageUrl = mealImageDataResult.imageUrl //gets url of meal image
                            if (imageUrl.isNotBlank()) { //checks if blank
                                imageLoader.loadImage(imageUrl, mealImageView)//loads image using Glide
                                Log.d("API CALL", imageUrl) //writes to log
                            } else {
                                Log.d("MealBrowserActivity", "Missing image URL") //writes to log
                            }
                            mealView.text = mealImageDataResult.strMeal //sets meal name

                            // Update the corresponding item in the adapter
                            mealsAdapter.updateItem(index, mealImageDataResult)
                        } else {
                            Log.d("MealBrowserActivity", "No meal data found")//writes of failure to log
                        }
                    } else {
                        Log.e( //writes of failure to log
                            "MealBrowserActivity",
                            "Failed to get search results\n${
                                response.errorBody()?.string().orEmpty()
                            }"
                        )
                    }
                }
            })
        }
    }

    //upon click of item, displays a message
        private fun showSelectionDialog(mealData: MealItemUIModel) {
        val preferenceWrapper = (application as PreferenceApplication).favoritesStore
        val preferenceViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FavoritesViewModel(preferenceWrapper) as T
            }
        }).get(FavoritesViewModel::class.java)

        AlertDialog.Builder(this)
            .setTitle("Food Selected")//message title
            .setMessage("You chose this dish: ${mealData.strMeal}")//message in selection box
            .setPositiveButton("OK") { _, _ -> }
            .setNeutralButton("Save Meal as Featured"){_,_ ->
                preferenceViewModel.saveText(mealData.strMeal +": " + mealData.strInstructions)
                    val newIntent = Intent(
                        this,
                        MealDetailsActivity::class.java
                    )
                    startActivity(newIntent)//starts new activity

            }
            .show()
    }
}