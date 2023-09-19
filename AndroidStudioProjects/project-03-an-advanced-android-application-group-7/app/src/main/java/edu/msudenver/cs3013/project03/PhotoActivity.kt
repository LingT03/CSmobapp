package edu.msudenver.cs3013.project03

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.util.concurrent.Executors
/**Photo taking activity, allows the user to take photos through the app that will be displayed in the media app.*/
class PhotoActivity : AppCompatActivity() {
    private val instruction_button: Button by lazy {
        findViewById(R.id.instruction_button)
    }
    companion object {
        private const val REQUEST_EXTERNAL_STORAGE = 3
        private const val REQUEST_IMAGE_CAPTURE = 1
    }
    private lateinit var photoImageView: ImageView
    private lateinit var providerFileManager: ProviderFileManagerClass
    private var photoInfo: FileInfo? = null
    private var isCapturingVideo = false
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        photoImageView = findViewById<ImageView>(R.id.photoImageView)
        val photoButton: Button = findViewById(R.id.photo_button)
        photoButton.setOnClickListener {
            capturePicture()
        }
        providerFileManager =
            ProviderFileManagerClass(
                applicationContext,
                FileHelperClass(applicationContext),
                contentResolver,
                Executors.newSingleThreadExecutor(),
                MediaContentHelper()
            )

        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                showImage(photoInfo!!)
            } else {
                // Handle the case when the picture capture is canceled or fails.
            }
        }
        //set button click listener
        findViewById<Button>(R.id.photo_button).setOnClickListener {
            isCapturingVideo = false
            checkStoragePermission {
                openImageCapture()
            }
        }
        //set back button click listener
        instruction_button.setOnClickListener {
            val pickIntent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(pickIntent)//starts new activity
        }
    }
    private fun capturePicture() {
        // ... Code to capture the picture and obtain FileInfo ...
        val providerFileManager = ProviderFileManagerClass(applicationContext,
            FileHelperClass(applicationContext),
            contentResolver,
            Executors.newSingleThreadExecutor(),
            MediaContentHelper())
        val fileInfo = providerFileManager.generatePhotoUri(System.currentTimeMillis())

        // Call the function to insert the image into the store
        providerFileManager.insertImageToStore(fileInfo)

        // Display the image in the ImageView
        showImage(fileInfo)
    }

    private fun showImage(fileInfo: FileInfo) {
        val file = fileInfo.file
        val uri = fileInfo.uri

        // Load the image into the ImageView using Glide or any other image loading library
        Glide.with(this)
            .load(uri)
            .into(photoImageView)
    }

    //starts getting the actual photo
    private fun openImageCapture() {
        photoInfo = providerFileManager.generatePhotoUri(System.currentTimeMillis())// gets blank photo.
        val uri = photoInfo?.uri
        uri?.let {
            takePictureLauncher.launch(it)
        }
    }

    //checks that the app has permission to access media store
    private fun checkStoragePermission(onPermissionGranted: () -> Unit) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q) {
            when (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )) {
                PackageManager.PERMISSION_GRANTED -> {
                    onPermissionGranted()
                }
                else -> {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_EXTERNAL_STORAGE
                    )
                }
            }
        } else {
            onPermissionGranted()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            // The image was captured successfully, update the photoInfo object
            photoInfo?.let {
                // Here, you might want to update other properties of FileInfo if needed
                // For example, the file name, path, or MIME type of the captured image
                showImage(it)
            }
        }
    }

    //handle the case when the permissions are granted and can continue with process
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                        openImageCapture()

                }
                return
            }

            else -> {
            }
        }
    }
}