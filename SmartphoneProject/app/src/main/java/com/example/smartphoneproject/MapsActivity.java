package com.example.smartphoneproject;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.smartphoneproject.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent in = getIntent();
        Bundle locations = in.getExtras();
        if (locations == null) return;
        String option = locations.getString("Option");

        //iterate over all sent locations and add a marker and LatLng for each
        for (String key : locations.keySet()){
            Object obj = locations.get(key);
            if (obj instanceof MarkerData){
                MarkerData marker = (MarkerData) obj;
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(marker.lat, marker.lng))
                        .title(marker.title));
            }
        }


        if (option.equals("countries")){
            //options for countries map
            //center map somewhere on the border between Germany and Czechia to be able to see all markers
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.45, 12.17), 3));
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else if (option.equals("bars")){
            //options for pubs map
            //center map somewhere East of Denmark for all markers to be visible
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(55.76858018351287, 11.647808473622602), 5));
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

    }
}