package com.model;

/**
 * Created by marne on 11/16/2016.
 */
public class RectangleSci {

    private Integer rectId;
    private double rectNELat;
    private double rectNELng;
    private double rectSWLat;
    private double rectSWLng;
    private String place;



    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }




    public RectangleSci(int rectId, double rectNELat, double rectNELng, double rectSWLat, double rectSWLng, String place){
        this.rectId = rectId;
        this.rectNELat = rectNELat;
        this.rectNELng = rectNELng;
        this.rectSWLat = rectSWLat;
        this.rectNELng = rectSWLng;

        this.place = place;
    }

    public Integer getRectId() {
        return rectId;
    }

    public void setRectId(Integer rectId) {
        this.rectId = rectId;
    }

    public double getRectNELat() {
        return rectNELat;
    }

    public void setRectNELat(double rectNELat) {
        this.rectNELat = rectNELat;
    }

    public double getRectNELng() {
        return rectNELng;
    }

    public void setRectNELng(double rectNELng) {
        this.rectNELng = rectNELng;
    }

    public double getRectSWLat() {
        return rectSWLat;
    }

    public void setRectSWLat(double rectSWLat) {
        this.rectSWLat = rectSWLat;
    }

    public double getRectSWLng() {
        return rectSWLng;
    }

    public void setRectSWLng(double rectSWLng) {
        this.rectSWLng = rectSWLng;
    }

    public boolean isPersistent() {
        // TODO Auto-generated method stub
        if(this.rectId > 0)
            return true;
        else
            return false;
    }






}
