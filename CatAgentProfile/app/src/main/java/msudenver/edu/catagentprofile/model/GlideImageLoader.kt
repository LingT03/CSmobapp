package msudenver.edu.catagentprofile.model

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import msudenver.edu.catagentprofile.api.ImageLoader


class GlideImageLoader(private val context: Context) :
    ImageLoader {
    override fun loadImage(imageUrl: String, imageView:
        ImageView) {
        Glide.with(context)
            .load(imageUrl).centerCrop().into(imageView)
    }
}