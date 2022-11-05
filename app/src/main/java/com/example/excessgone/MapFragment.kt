package com.example.excessgone
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.excessgone.databinding.FragmentMapBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MapFragment : Fragment() {
    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapBinding
    private var latitude:Double=0.toDouble()
    private var longitude:Double=0.toDouble()
    private lateinit var mLastLocation: Location
    private var mMarker: Marker?=null

    // location
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    companion object {
        private const val MY_PERMISSION_CODE : Int = 1000;
    }
    //
    //lateinit var mService:IGoogleAPIService
    //internal lateinit var  currentPlace:MyPlaces
    //

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_map, null, false)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        mapFragment!!.getMapAsync { googleMap ->
            mMap = googleMap
            mMap.clear() //clear old markers


            val googlePlex = CameraPosition.builder()
                .target(LatLng(37.4219999, -122.0862462))
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)
        }
        //

        // Init Service
        //mService = Common.googleApiService

        //

        //Request runtime permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(checkLocationPermission()) {
                buildLocationRequest()
                buildLocationCallBack()

                fusedLocationProviderClient = activity?.let {
                    LocationServices.getFusedLocationProviderClient(
                        it
                    )
                }!!
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.myLooper()
                )
            }
        }
        else{

            buildLocationRequest()
            buildLocationCallBack()

            fusedLocationProviderClient = activity?.let {
                LocationServices.getFusedLocationProviderClient(
                    it
                )
            }!!
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.myLooper()
            )
        }


        //
        //nearByPlace("restaurants")


        //

        return rootView

    }


  /* private fun nearByPlace(typePlace: String) {
        // clear all marker on Map

     //   mMap.clear()

        // build URL Request base on location
        val url = getUrl(latitude, longitude, typePlace)
        mService.getNearbyPlaces(url)
            .enqueue(object : Callback<MyPlaces> {
                override fun onResponse(call: Call<MyPlaces>?, response: Response<MyPlaces>) {
                    currentPlace = response.body()!!

                    if (response!!.isSuccessful) {


                        for (i in 0 until response!!.body()!!.results!!.size) {
                            val markerOptions = MarkerOptions()
                            val googlePlace = response.body()!!.results!![i]
                            val lat = googlePlace.geometry!!.location!!.lat
                            val lng = googlePlace.geometry!!.location!!.lng
                            val placeName = googlePlace.name
                            val latLng = LatLng(lat, lng)

                            markerOptions.position(latLng)
                            markerOptions.title(placeName)
                            if (typePlace.equals("restaurants"))
                                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.maps))
                            else
                                markerOptions.icon(
                                    BitmapDescriptorFactory.defaultMarker(
                                        BitmapDescriptorFactory.HUE_BLUE
                                    )
                                )

                            markerOptions.snippet(i.toString()) // assign index

                            // add marker to map
                            mMap!!.addMarker(markerOptions)


                            // Move camera
                            mMap!!.moveCamera(
                                CameraUpdateFactory.newLatLng(latLng))

                            mMap!!.animateCamera(CameraUpdateFactory.zoomTo(17f))


                        }
                    }
                }

                override fun onFailure(call: Call<MyPlaces>?, t: Throwable?) {
                        Toast.makeText(activity, "" + t!!.message, Toast.LENGTH_SHORT).show()
                }
            })
    }*/

   /* private fun getUrl(latitude: Double, longitude: Double, typePlace: String): String {

        val googlePlaceUrl = StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json")
        googlePlaceUrl.append("?keyword=cruise&location=$latitude,$longitude")
        googlePlaceUrl.append("&radius=10000") // 10 km
        googlePlaceUrl.append("&type=$typePlace")
        googlePlaceUrl.append("&key=AIzaSyD6GxLmTFb6A-O0MSVAQqJxZWyY5pMeFdU")

        Log.d("URL DEBUG XXX", googlePlaceUrl.toString())
        return googlePlaceUrl.toString()
    }*/


    private fun buildLocationCallBack() {
        locationCallback = object : LocationCallback() {

            override fun onLocationResult(p0: LocationResult) {
                mLastLocation = p0!!.locations.get(p0!!.locations.size-1) // get last location
                if(mMarker != null)
                {
                    mMarker!!.remove()
                }

                latitude = mLastLocation.latitude
                longitude = mLastLocation.longitude

                val latLng = LatLng(latitude,longitude)
                val markerOptions = MarkerOptions()
                    .position(latLng)
                    .title("Your position")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                mMarker = mMap!!.addMarker(markerOptions)

                // move camera
                mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                mMap!!.animateCamera(CameraUpdateFactory.zoomTo(11f))

            }
        }
    }
    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10f
    }
    private fun checkLocationPermission() : Boolean{
        if(activity?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.ACCESS_FINE_LOCATION) } != PackageManager.PERMISSION_GRANTED)
        {

            if (activity?.let { ActivityCompat.shouldShowRequestPermissionRationale(it, android.Manifest.permission.ACCESS_FINE_LOCATION) } == true)
                ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSION_CODE)
            else
                activity?.let { ActivityCompat.requestPermissions(it, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), MY_PERMISSION_CODE) }
            return false
        }
        else
            return true
    }
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode)
        {
            MY_PERMISSION_CODE->{
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if(activity?.let { ContextCompat.checkSelfPermission(it, android.Manifest.permission.ACCESS_FINE_LOCATION) } == PackageManager.PERMISSION_GRANTED)
                        if(checkLocationPermission()){

                            buildLocationRequest();
                            buildLocationCallBack();

                            fusedLocationProviderClient = activity?.let {
                                LocationServices.getFusedLocationProviderClient(
                                    it
                                )
                            }!!
                            fusedLocationProviderClient.requestLocationUpdates(
                                locationRequest,
                                locationCallback,
                                Looper.myLooper()
                            )
                            mMap!!.isMyLocationEnabled=true
                        }
                }
                else
                {
                    Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
/*
    override fun onStop(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onStop()
    }*/

     @SuppressLint("MissingPermission")
      fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // init google play services

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity?.let {
                    ContextCompat.checkSelfPermission(
                        it, android.Manifest.permission.ACCESS_FINE_LOCATION
                    )
                } == PackageManager.PERMISSION_GRANTED) {
                mMap!!.isMyLocationEnabled = true
            }
        }else
        {
            mMap!!.isMyLocationEnabled = true
        }

        // Enable zoom control
        mMap!!.uiSettings.isZoomControlsEnabled=true
    }

}