package edu.msudenver.cs3013.project03

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
/**
 * Class that helps get Uri, and pictures from file*/
class FileHelperClass(private val context: Context) {
    fun getUriFromFile(file: File): Uri {
        return FileProvider.getUriForFile(context, "edu.msudenver.cs3013.project03", file)
    }
    fun getPicturesFolder(): String = Environment.DIRECTORY_PICTURES
}