package com.model;

/**
 * Created by marne on 11/18/2016.
 */
public class MapRectangles {

    private int mapId;
    private int rectId;
    private int stopLimit;

    public MapRectangles(){

    }

    public MapRectangles(int rectId, int mapId, int stopLimit){
        this.rectId = rectId;
        this.mapId = mapId;
        this.stopLimit = stopLimit;
    }

    public int getStopLimit() {
        return stopLimit;
    }

    public void setStopLimit(int stopLimit) {
        this.stopLimit = stopLimit;
    }

    public int getRectId() {
        return rectId;
    }

    public void setRectId(int rectId) {
        this.rectId = rectId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }


}
