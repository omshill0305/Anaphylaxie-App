package com.example.olga.aa_app;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmergencyCallFragment extends Fragment implements LocationListener {

    private static final int LOCATION_REQUEST = 500;
    private Intent intentThatCalled;
    private double latitude;
    private double longitude;
    private LocationManager locationManager;
    private Criteria criteria;
    private String bestProvider;
    private EmergencyCallFragment _this;
    private Geocoder geocoder;
    private List<Address> addresses;


    public EmergencyCallFragment() {
        // Required empty public constructor
        this._this = this;
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
                        } else {
                            googleMap.setMyLocationEnabled(true);
                            locationManager = (LocationManager)  getActivity().getSystemService(Context.LOCATION_SERVICE);
                            criteria = new Criteria();
                            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

                            Location location = locationManager.getLastKnownLocation(bestProvider);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                                geocoder = new Geocoder(getActivity(), Locale.getDefault());

                                try {
                                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "Leider kann deine Adresse nicht gefunden werden.", Toast.LENGTH_SHORT).show();

                                }

                                String address = addresses.get(0).getAddressLine(0);

                                googleMap.clear();

                                googleMap.getUiSettings().setAllGesturesEnabled(true);
                                MarkerOptions marker = new MarkerOptions().position(
                                        new LatLng(latitude, longitude)).title(address);


                                googleMap.addMarker(marker).showInfoWindow();

                                CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(15.0f).build();
                                CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                                googleMap.moveCamera(cameraUpdate);
                            }
                            else{
                                locationManager.requestLocationUpdates(bestProvider, 1000, 0, _this);
                            }

                        }
                    }

                }
            });

        // Perform any camera updates here

        }
        return v;
    }


    @Override
    public void onLocationChanged(Location location) {
        //Hey, a non null location! Sweet!

        //remove location callback:
        locationManager.removeUpdates(this);

        //open the map:
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
