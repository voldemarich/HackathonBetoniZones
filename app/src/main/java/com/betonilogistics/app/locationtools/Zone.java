package com.betonilogistics.app.locationtools;

import java.util.LinkedList;

/**
 * Created by voldemarich on 19.3.2016.
 */
public class Zone {
    private String name;
    private LinkedList<Coordinate> frame;

    public LinkedList<Coordinate> getFrame() {

        return frame;
    }

    public Zone(String name, LinkedList<Coordinate> frame){
        this.frame = frame;
        this.name = name;
    }

    public boolean cointains(Coordinate c){
        return Util.isCoordInPredefArea(frame, c);
    }

    public String getName() {
        return name;
    }
}
