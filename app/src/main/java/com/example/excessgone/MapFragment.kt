// Attached: MapFragment.kt
// =============================================================================
//
// Programmer: Nour Siwar
// Class: Android Development
// Instructor: CodePath
//
// =============================================================================
// Program: MapFragment class
// =============================================================================
// Description:
//
// Note: This class is still WIP for retrieving nearby places.
//
// This class is another fragment of main activity, and it is responsible for
// setting up the map, retrieving current user's location, and retrieving
// near by homeless shelters.
//
// =============================================================================
package com.example.excessgone
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.excessgone.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater)

        // Inflate the layout for this fragment
        val rootView = binding.root

        //use SupportMapFragment for using in fragment instead of:
        // activity  MapFragment = activity and SupportMapFragment = fragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.frg) as SupportMapFragment?


        // instead of using OnMapReadyCallBack in main activity, this is how it is used in Fragment.
        mapFragment!!.getMapAsync { mMap ->
            // setting the type of map to be similar to Google Map's.
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            //clear old markers
            mMap.clear()


            // positioning camera to current location
            val googlePlex = CameraPosition.builder()
                .target(LatLng(33.73451, -117.79149))
                .zoom(10f)
                .bearing(0f)
                .tilt(45f)
                .build()

            // takes 3000 ms, aka 3 seconds to animate to current position
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 3000, null)

            // add the green marker to current location
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(33.73451, -117.79149))
                    .title("Your location")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))

            )

            // adds the plus and minus (zooming in and out) zoom controls.
            mMap.uiSettings.isZoomControlsEnabled = true
        }

        return rootView

    }
}