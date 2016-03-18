package com.betonilogistics.app.locationtools;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by voldemarich on 18.3.2016.
 */
public class FriendlyLocationListener implements LocationListener{

    private Location myposition;
    private Context c;

    public FriendlyLocationListener(Context c){
        this.c = c;
    }

    @Override
    public void onLocationChanged(Location location) {
        myposition = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Toast.makeText(c, "Kek status changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(c, "Gps up", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(c, "Gps down", Toast.LENGTH_SHORT).show();
    }


    public Location getMyposition() throws NullPointerException{
        return myposition;
    }
}
