package edu.msudenver.cs3013.project03


import android.net.Uri
import java.io.File
/**
 * CLass to get file info for picture capabilities*/
data class FileInfo(
    val uri: Uri,
    val file: File,
    val name: String,
    val relativePath:String,
    val mimeType:String
)

