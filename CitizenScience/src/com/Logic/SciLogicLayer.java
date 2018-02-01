package com.Logic;

import com.model.*;
import com.model.Rectangle;

import com.persistence.Persistable;
import com.persistence.ScientistLogin;
import com.persistence.UpdateMapRectangle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by marne on 11/16/2016.
 */
public class SciLogicLayer {


    public boolean authorizeScientist(String uname, String pwd) throws SQLException{
        ScientistLogin scientistLogin = new ScientistLogin();
        System.out.println("Inside logic layer login");
        Boolean resultInLogic = scientistLogin.authorizeScientist(uname,pwd);
        System.out.println("THe result in logic layer "+resultInLogic);
        return resultInLogic;
    }


    //UpdateMapRectangle persistenceMapRect = new UpdateMapRectangle();
    public void updateMapDetails(Map map) throws Exception{
        UpdateMapRectangle updateMapRectangle = new UpdateMapRectangle();

        updateMapRectangle.storeMap(map);


    }

    public void updateRectDetails(RectangleSci rect) throws Exception{
        UpdateMapRectangle updateMapRectangle = new UpdateMapRectangle();

        updateMapRectangle.storeRectangle(rect) ;
    }

    public void deleteRectangle(RectangleSci rect) throws Exception{
        UpdateMapRectangle updateMapRectangle = new UpdateMapRectangle();
        updateMapRectangle.deleteRectangle(rect);
    }

    public void storeMapRectangle(int rectId, int mapId, int stopLimit) throws Exception{

        UpdateMapRectangle updateMapRectangle = new UpdateMapRectangle();
        updateMapRectangle.storeMapRectangle(rectId,mapId,stopLimit);
    }

    public void storeScientistRectangle(String sname, int rectId) throws Exception{
        UpdateMapRectangle updateMapRectangle = new UpdateMapRectangle();
        updateMapRectangle.storeScientistRectangle(sname,rectId);
    }





}
