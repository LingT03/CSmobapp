package edu.msudenver.cs3013.project03

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.msudenver.cs3013.project03.PreferenceApplication
import edu.msudenver.cs3013.project03.PreferenceViewModel
import edu.msudenver.cs3013.project03.R
import edu.msudenver.cs3013.project03.databinding.ActivityMapsBinding
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Fragment that displays a map and the user's current location.
 * It also displays locations of nearby grocery stores.
 */
class MapsFragment : Fragment(), OnMapReadyCallback {

    private var marker: Marker? = null
    private lateinit var mMap: GoogleMap
    private var radiusDecimal: Float = 0.5F
    private lateinit var binding: ActivityMapsBinding
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityMapsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //sets up button to increase search radius
        val changeRadiusButton: Button = view.findViewById(R.id.change_radius_button)
        changeRadiusButton.setOnClickListener {
            changeRadius()
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    getLastLocation()
                } else {
                    // if should show rationale, show it
                    if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                        showPermissionRationale {
                            requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
                        }
                    }
                }
            }
    }


    @SuppressLint("MissingPermission")
    private fun getLocation() {
        //get location of the device
        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val userLocation = LatLng(location.latitude, location.longitude)
                    updateMapLocation(userLocation)
                    addMarkerAtLocation(userLocation, "You")
                }
            }
        findPlace(radiusDecimal)
    }

    private fun updateMapLocation(location: LatLng) {
        //updates the map view to see the location of device
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 7f))
    }
    private fun addMarkerAtLocation(
        //adds a marker at the device's location
        location: LatLng, title: String,
        markerIcon: BitmapDescriptor? = null) = mMap.addMarker(
        MarkerOptions().title(title).position(location)
            .apply { markerIcon?.let { icon(markerIcon) } } //if markericon is not null, set icon
    )//if it is null, then use the default. apply


    private fun getBitmapDescriptorFromVector(@DrawableRes
                                              vectorDrawableResourceId: Int): BitmapDescriptor? {
        //changes the marker icon for grocery stores
        val bitmap = context?.let {
            ContextCompat.getDrawable(
                it,
                vectorDrawableResourceId)?.let { vectorDrawable ->
                vectorDrawable.setBounds(0, 0,
                    vectorDrawable.intrinsicWidth,
                    vectorDrawable.intrinsicHeight)
                val drawableWithTint = DrawableCompat
                    .wrap(vectorDrawable)
                DrawableCompat.setTint(drawableWithTint,
                    Color.RED)
                val bitmap = Bitmap.createBitmap(
                    vectorDrawable.intrinsicWidth,
                    vectorDrawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
                )
                val canvas = Canvas(bitmap)
                drawableWithTint.draw(canvas)
                bitmap
            }
        }
        //only calls the let if bitmap is not null
        return bitmap?.let {//if bitmap != null, return object, else return null.
            BitmapDescriptorFactory.fromBitmap(it)
                .also { bitmap?.recycle() }
        }
    }

    private fun hasLocationPermission() =
        //checks for user permissions
        context?.let {
            ContextCompat.checkSelfPermission(
                it, ACCESS_FINE_LOCATION
            )
        } == PackageManager.PERMISSION_GRANTED
    private fun showPermissionRationale(
        //gives reasoning to user for why the app needs location permissions
        positiveAction: () -> Unit
    ) {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle("Location permission")
                .setMessage("We need your permission to find your current position")
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    positiveAction()
                }
                .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                    dialog.dismiss()
                }
                .create().show()
        }
    }

    private fun changeRadius() {
        //expands radius of the search parameters for nearby grocery stores.
        if (hasLocationPermission()) {
            if (context?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        ACCESS_FINE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED && context?.let {
                    ActivityCompat.checkSelfPermission(
                        it,
                        ACCESS_COARSE_LOCATION
                    )
                } != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        radiusDecimal += 0.5F
                        findPlace(radiusDecimal)
                    }
                }
        } else {
            // Request location permission
            requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }
    }
    private fun findPlace(radiusDecimal: Float) {
        //used to find nearest grocery stores
        val fusedLocationProviderClient = context?.let {
            LocationServices.getFusedLocationProviderClient(
                it
            )
        }
        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    ACCESS_FINE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED && context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    ACCESS_COARSE_LOCATION
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient?.getLastLocation()?.addOnSuccessListener { location ->
            if (location != null) {
                val currentLocation = LatLng(location.latitude, location.longitude)

                val data = """
    [out:json];
    (
        node["shop"="supermarket"]
            (${currentLocation.latitude - radiusDecimal},${currentLocation.longitude - radiusDecimal},${currentLocation.latitude + radiusDecimal},${currentLocation.longitude + radiusDecimal});
    );
    out;
    """.trimIndent()
                CoroutineScope(Dispatchers.IO).launch {
                    val response = service.getParks(data)

                    withContext(Dispatchers.Main) {
                        // Clear all existing markers before adding new ones
                        mMap.clear()

                        // Add the user's location marker with default icon
                        addMarkerAtLocation(currentLocation, "Current Location")

                        for (element in response.elements) {
                            val markerOptions = MarkerOptions()
                                .position(LatLng(element.lat, element.lon))
                                .title(element.tags.name)

                            // Use the target_icon for restaurant markers
                            val markerIcon = getBitmapDescriptorFromVector(R.drawable.target_icon)
                            addMarkerAtLocation(
                                LatLng(element.lat, element.lon),
                                "Store",
                                markerIcon
                            )
                        }
                    }
                }
            }
        }
    }
    private fun addOrMoveSelectedPositionMarker(latLng: LatLng) {
        //moves marker position
        if (marker == null) {
            marker = addMarkerAtLocation(latLng,
                "Deploy here", getBitmapDescriptorFromVector(
                    R.drawable.target_icon)
            )
        } else { marker?.apply { position = latLng } } //.apply changes already intilializes variable. an initilizer.
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap.apply {
            setOnMapClickListener { latLng ->
                addOrMoveSelectedPositionMarker(latLng)
            }
        }
        mMap.uiSettings.isZoomControlsEnabled = true
        when {
            hasLocationPermission() -> getLocation()
            shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION) -> {
                showPermissionRationale {
                    requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
                }
            }
            else -> requestPermissionLauncher.launch(ACCESS_FINE_LOCATION)
        }

    }


    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        //fused location last location with addOnFailureListener and addOnCanceledListener listeners added
        fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    val currentLocation = LatLng(it.latitude, it.longitude)
                    updateMapLocation(currentLocation)
                    addMarkerAtLocation(currentLocation, "Current Location")
                    //zoom in
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))
                }
            }
    }

}