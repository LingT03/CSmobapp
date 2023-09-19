package edu.msudenver.cs3013.project03


import android.content.ContentValues
import android.net.Uri
import android.provider.MediaStore
/**File to help with content needed for photos*/
class MediaContentHelper {
    //get uri content to be able to store photo
    fun getImageContentUri(): Uri =
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) { //for new versions of phone
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {//for old versions of phone
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
    //generate needed content for displaying photos
    fun generateImageContentValues(fileInfo: FileInfo) = ContentValues().apply {
        this.put(MediaStore.Images.Media.DISPLAY_NAME, fileInfo.name)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
            this.put(MediaStore.Images.Media.RELATIVE_PATH, fileInfo.relativePath)
        }
        this.put(MediaStore.Images.Media.MIME_TYPE, fileInfo.mimeType)
    }

}
