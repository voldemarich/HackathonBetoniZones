package com.betonilogistics.app.locationtools;

import android.location.Location;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by voldemarich on 18.3.2016.
 */
public class Util {


    private double sign (Coordinate p1, Coordinate p2, Coordinate p3)
    {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    private boolean isPointInTriangle (Coordinate pt, Coordinate v1, Coordinate v2, Coordinate v3)
    {
        boolean b1, b2, b3;

        b1 = sign(pt, v1, v2) < 0.0;
        b2 = sign(pt, v2, v3) < 0.0;
        b3 = sign(pt, v3, v1) < 0.0;

        return ((b1 == b2) && (b2 == b3));
    }


    public static boolean isCoordInPredefArea(LinkedList<Coordinate> listcoords, Location point){
        double latp, lonp;
        latp = point.getLatitude();
        lonp = point.getLongitude();
        Iterator<>
        while (listcoords.iterator().hasNext()){
            listc
        }
    }

}
