package edu.msudenver.cs3013.project03

import android.widget.ImageView
/* Interface used to load image from the url from the API. */
interface ImageLoader {
    fun loadImage(imageUrl: String, imageView: ImageView) //loads image
}