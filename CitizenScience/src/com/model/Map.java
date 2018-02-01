package com.model;

/**
 * Created by marne on 11/16/2016.
 */
public class Map {

    private int mapId;
    private double mapCenterLat;
    private double mapCenterLng;

    public Map() {

    }

    public Map(int mapId, double mapCenterLat, double mapCenterLng){
        this.mapId = mapId;
        this.mapCenterLat = mapCenterLat;
        this.mapCenterLng = mapCenterLng;

    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }



    public double getMapCenterLat() {
        return mapCenterLat;
    }

    public void setMapCenterLat(double mapCenterLat) {
        this.mapCenterLat = mapCenterLat;
    }

    public double getMapCenterLng() {
        return mapCenterLng;
    }

    public void setMapCenterLng(double mapCenterLng) {
        this.mapCenterLng = mapCenterLng;
    }



    public boolean isPersistent() {
        // TODO Auto-generated method stub
        if(this.mapId > 0)
            return true;
        else
            return false;
    }


}
