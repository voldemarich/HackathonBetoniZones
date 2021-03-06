package com.betonilogistics.app.locationtools;

import android.util.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by voldemarich on 19.3.2016.
 */
public class LocationsXmlParser {

    Document rootXml;


    public LocationsXmlParser(InputStream fileInputStream){
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            rootXml = db.parse(fileInputStream);


        }catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }catch(SAXException se) {
            se.printStackTrace();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private LinkedList<Coordinate> readCoordListFromElem(Element e) throws IOException, NumberFormatException{
        //NodeList points = e.getElementsByTagName("loc");
        LinkedList<Coordinate> llc = new LinkedList<Coordinate>();
//        if (points != null && points.getLength() > 0) {
//            for (int i = 0; i < points.getLength(); i++) {
//                Element el = (Element) points.item(i);
////                llc.add(new Coordinate(Double.parseDouble(el.getAttribute("lat")), Double.parseDouble(el.getAttribute("lon"))));
//                String locs = el.getTextContent();
//            }
        String locs = e.getTextContent();
        String[] coords = locs.split(";");
        for (String s : coords){
            String[] onerange = s.split(",");
            if(onerange.length > 1){
                llc.add(new Coordinate(Double.parseDouble(onerange[1]), Double.parseDouble(onerange[0])));
            }
        }
            return llc;
        //}
        //else throw new IOException();
    }

    public Zone getRootZone() throws IOException, NumberFormatException {
        LinkedList<Coordinate> llc;
        Element rootelem = rootXml.getDocumentElement();
        NodeList nl = rootelem.getElementsByTagName("rootzone");
        if (nl != null && nl.getLength() == 1) {
            Element rootZoneNode = (Element) nl.item(0);
            llc = readCoordListFromElem(rootZoneNode);
        }
        else throw new IOException();
        return new Zone("rootzone", llc);
    }

    public ArrayList<Zone> getStorageZones() throws IOException, NumberFormatException {
        Element rootelem = rootXml.getDocumentElement();
        NodeList nl = rootelem.getElementsByTagName("zone");
        ArrayList<Zone> alz = new ArrayList<Zone>();
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                Element zoneNode = (Element) nl.item(i);
                alz.add(new Zone(zoneNode.getAttribute("name"), readCoordListFromElem(zoneNode)));
                }
            return alz;
        }
        else throw new IOException();
    }

}
