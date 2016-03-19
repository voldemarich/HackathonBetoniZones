package com.betonilogistics.app.locationtools;

import android.location.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by voldemarich on 18.3.2016.
 */
public class Util {


    private static double sign (Coordinate p1, Coordinate p2, Coordinate p3)
    {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    private static boolean isPointInTriangle (Coordinate pt, Coordinate v1, Coordinate v2, Coordinate v3)
    {
        boolean b1, b2, b3;

        b1 = sign(pt, v1, v2) <= 0.0;
        b2 = sign(pt, v2, v3) <= 0.0;
        b3 = sign(pt, v3, v1) <= 0.0;

        return ((b1 == b2) && (b2 == b3));
    }


     static boolean isCoordInPredefArea(LinkedList<Coordinate> listcoords, Coordinate pnt){
        if(listcoords.size()<3) return false;
        Iterator<Coordinate> iter = listcoords.iterator();
        boolean result = false;
        Coordinate p1 = iter.next();
        Coordinate p2 = iter.next();
        do {
            Coordinate p3 = iter.next();
            result |= isPointInTriangle(pnt, p1, p2, p3);
            p2 = p3;
        }
        while (iter.hasNext());
        return result;
    }

    public static String[] getZonesByLocation(ArrayList<Zone> alz, Location location){
        Coordinate c = new Coordinate(location.getLatitude(),location.getLongitude());
        LinkedList<String> ls = new LinkedList<String>();
        for (Zone a : alz){
            if(a.cointains(c)){
                ls.add(a.getName());
            }
        }
        return (String[])ls.toArray();
    }

}
