package com.betonilogistics.app.locationtools;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.widget.Toast;
import com.betonilogistics.app.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LinkedList<PolygonOptions> listpoly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        LatLng latlng = new LatLng(61.6674358, 27.2913909);
        mMap.addMarker(new MarkerOptions().position(latlng).title("Betoni"));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latlng).zoom(18.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        try {
            listpoly = new LinkedList<PolygonOptions>();
            LocationsXmlParser lxp = new LocationsXmlParser(getAssets().open("zones.xml"));
            ArrayList<Zone> alz = lxp.getStorageZones();
            for (Zone z : alz){
                PolygonOptions p = new PolygonOptions();
                for(Coordinate c : z.getFrame()){
                    p.add(new LatLng(c.x, c.y));
                }
                p.strokeColor(Color.RED);
                p.fillColor(0x1000FF00);
                listpoly.add(p);
            }

            for(PolygonOptions po : listpoly) {
                googleMap.addPolygon(po);
            }
        } catch (IOException e) {
            Toast.makeText(this, "Fail zones file", Toast.LENGTH_LONG).show();
        }

    }
}
