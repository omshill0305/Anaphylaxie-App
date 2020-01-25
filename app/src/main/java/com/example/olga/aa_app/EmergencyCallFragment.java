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
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
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
    private static final int REQUEST_PHONE_CALL = 1;
    private double latitude;
    private double longitude;
    private LocationManager locationManager;
    private Criteria criteria;
    private String bestProvider;
    private EmergencyCallFragment _this;
    private Geocoder geocoder;
    private List<Address> addresses;
    private SupportMapFragment mSupportMapFragment;
    private FragmentTransaction ft;



    public EmergencyCallFragment() {
        // Required empty public constructor
        this._this = this;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_emergency_call, container, false);
        ImageButton call = v.findViewById(R.id.button_emergancy_call);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = "112";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));
                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    getActivity().requestPermissions(new String[] {Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    return;

                } else {
                startActivity(intent);
                }
            }
        });



        ft = getFragmentManager().beginTransaction();

        // Mapview Fragment innerhalb EmergencyCallFragment setzen
        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map_view, mSupportMapFragment).commit();
        }

        // Map erzeugen
        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(final GoogleMap googleMap) {
                    if (googleMap != null) {

                        //Permission überprüfen
                        if (getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            getActivity().requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
                            ft.detach(_this).attach(_this).commit();
                            return;
                        } else {
                            //Map aktivieren und anzeigen
                            googleMap.setMyLocationEnabled(true);
                            locationManager = (LocationManager)  getActivity().getSystemService(Context.LOCATION_SERVICE);
                            criteria = new Criteria();
                            bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true)).toString();

                            //letzte Location abfragen
                            Location location = locationManager.getLastKnownLocation(bestProvider);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();

                                // Geocoder übersetzt latitude und longitude in die Adresse
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

                                //Marker wird gesetzt
                                MarkerOptions marker = new MarkerOptions().position(
                                        new LatLng(latitude, longitude)).title(address);

                                //Marker wird angezeigt
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


        }
        setHasOptionsMenu(true);
        return v;
    }


    @Override
    public void onLocationChanged(Location location) {

        //Location callback löschen
        locationManager.removeUpdates(this);

        //Map öffnen
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        ft.detach(_this).attach(_this).commit();
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.dataprotection:
                Intent intent = new Intent(getActivity(), DataProtectionActivity.class);
                getActivity().startActivity(intent);
                return true;
        }
        return false;
    }
}
