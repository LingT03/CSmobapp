package msudenver.edu.catagentprofile.api

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(ImageUrl:String, imageView: ImageView)
}