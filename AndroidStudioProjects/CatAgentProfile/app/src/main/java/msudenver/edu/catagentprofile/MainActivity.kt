package msudenver.edu.catagentprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import msudenver.edu.catagentprofile.api.ImageLoader
import msudenver.edu.catagentprofile.api.TheCatApiService
import msudenver.edu.catagentprofile.model.GlideImageLoader
import msudenver.edu.catagentprofile.model.ImageResultData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(
                MoshiConverterFactory.create()
            ).build()
    }

    private val profileImageView by lazy {
        findViewById<ImageView>(R.id.main_profile_image)
    }

    private val theCatApiService by lazy {
        retrofit.create(TheCatApiService::class.java)
    }
    private val serverResponseView by lazy {
        findViewById<TextView>(R.id.main_server_response)
    }

    private val imageLoader: ImageLoader by lazy {
        GlideImageLoader(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCatImageResponse()
    }

    private fun getCatImageResponse() {
        val call = theCatApiService.searchImages(1, "full")
        call.enqueue(object : Callback<List<ImageResultData>> {
            override fun onFailure(call: Call<List<ImageResultData>>, t: Throwable) {
                Log.e("MainActivity", "Failed to get search results", t)
            }

            override fun onResponse(
                call: Call<List<ImageResultData>>,
                response: Response<List<ImageResultData>>
            ) {
                if (response.isSuccessful) {
                    val imageResults = response.body()
                    val firstImageUrl = imageResults?.firstOrNull()?.imageUrl.orEmpty()
                    if (!firstImageUrl.isBlank()) {
                        imageLoader.loadImage(firstImageUrl, profileImageView)
                    } else {
                        Log.d("MainActivity", "Missing image URL")

                    }
                }
            }
        })
    }
}
