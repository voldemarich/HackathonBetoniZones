package com.betonilogistics.app.locationtools;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by voldemarich on 18.3.2016.
 */
public class LocationConstants {

    public static LinkedList<Coordinate> getPredefCoordinates(){
        LinkedList<Coordinate> lnc = new LinkedList<Coordinate>();
        lnc.add(new Coordinate(61.666887, 27.290911));
        lnc.add(new Coordinate(61.667585, 27.291099));
        lnc.add(new Coordinate(61.668089, 27.291854));
        lnc.add(new Coordinate(61.667833, 27.292112));
        lnc.add(new Coordinate(61.666826, 27.291588));
        return lnc;
    }

//    public static ArrayList<Zone> getTerritoryZones(){
//        ArrayList<Zone> zones = new ArrayList<Zone>();
//        LinkedList<Coordinate> a = new LinkedList<Coordinate>();
//
//    }

}
