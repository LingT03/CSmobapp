package edu.msudenver.cs3013.project03

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
/* This class uses Glide and ImageLoader interface to load the image based off of the url from the API. */
class GlideImageLoader(private val context: Context) : ImageLoader {
    override fun loadImage(imageUrl: String, imageView: ImageView) { //loads image
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(imageView)
    }
}
