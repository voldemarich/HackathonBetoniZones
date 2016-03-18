package com.betonilogistics.app.locationtools;

import android.location.Location;

/**
 * Created by voldemarich on 18.3.2016.
 */
public class Util {



    public static boolean isCoordInPredefArea(Location startcoord, Location fincoord, Location point){
        double latp, lonp, lats, lons, latf, lonf;
        lats = startcoord.getLatitude();
        lons = startcoord.getLongitude();
        latf = fincoord.getLatitude();
        lonf = fincoord.getLongitude();
        latp = point.getLatitude();
        lonp = point.getLongitude();
        if(lats - latp <= 0 && latf - latp >= 0 && lons - lonp <= 0 && lonf - lonp >= 0) return true;
        return false;
    }

}
