package com.example.olga.aa_app;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmergencyCallFragment extends Fragment {

    private static final int LOCATION_REQUEST = 500;

    public EmergencyCallFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_emergency_call, container, false);

        SupportMapFragment mSupportMapFragment;

        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map_view, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    if (googleMap != null) {

                        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            getActivity().requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
                            return;
                        }
                        googleMap.setMyLocationEnabled(true);
                        LocationManager myLocation = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


                        double latitude = myLocation.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLatitude();
                        double longitude = myLocation.getLastKnownLocation(LocationManager.NETWORK_PROVIDER).getLongitude();

                        googleMap.clear();

                        googleMap.getUiSettings().setAllGesturesEnabled(true);
                        MarkerOptions marker = new MarkerOptions().position(
                                new LatLng(latitude, longitude)).title("Hello Maps");

                        googleMap.addMarker(marker); // MAKE THIS WHATEVER YOU WANT

                        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);

                    }

                }
            });

        // Perform any camera updates here

        }
        return v;
    }



}
