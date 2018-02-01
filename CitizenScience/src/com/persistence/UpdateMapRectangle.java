package com.persistence;

import com.model.Map;
import com.model.RectangleSci;


import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by marne on 11/16/2016.
 */
public class UpdateMapRectangle {


    public void storeMap(Map map) throws Exception {

        String insertMap = "insert into MapDetails (Lat, Lng) values (?,?)";
        String getMap = "Select * from MapDetails where Lat=? and Lng = ?";

        System.out.println("Inside store map");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataCollection11", "root", "Mdsp1891#");
        if (connection != null) {
            System.out.println("Connected successfully");
        }

        PreparedStatement preparedStatement = null;
        int mapid;
        int executeStatus;


        preparedStatement = (PreparedStatement) connection.prepareStatement(getMap);
        preparedStatement.setDouble(1, map.getMapCenterLat());
        preparedStatement.setDouble(2, map.getMapCenterLng());
        ResultSet rs1 = preparedStatement.executeQuery();

        if(rs1.next()){
            System.out.println("Map already exists");
            map.setMapId(rs1.getInt(1));
            return;
        }
        else {

            if (!map.isPersistent()) {

                preparedStatement = (PreparedStatement) connection.prepareStatement(insertMap);

                preparedStatement.setDouble(1, map.getMapCenterLat());
                preparedStatement.setDouble(2, map.getMapCenterLng());


                executeStatus = preparedStatement.executeUpdate();

                if (executeStatus >= 1) {
                    String sql = "select last_insert_id()";

                    if (preparedStatement.execute(sql)) {


                        ResultSet rs = preparedStatement.getResultSet();

                        while (rs.next()) {
                            mapid = rs.getInt(1);
                            if (mapid > 0)
                                map.setMapId(mapid);
                        }
                    }
                }

            } else {
                System.out.println("Error while entering the store map");
            }
        }


    }


    public void storeRectangle(RectangleSci rect) throws Exception {

        String insertRectangle = "insert into RectDetails(NELat, NELng,SWLat,SWLng,Place) values (?,?,?,?,?)";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataCollection11", "root", "Mdsp1891#");
        if (connection != null) {
            System.out.println("Connected successfully");
        }

        int rectId;
        int executeStatus;
        PreparedStatement preparedStatement = null;
        System.out.println("Inside store rectangle");
        if (!rect.isPersistent()) {
            preparedStatement = (PreparedStatement) connection.prepareStatement(insertRectangle);

            preparedStatement.setDouble(1, rect.getRectNELat());
            preparedStatement.setDouble(2, rect.getRectNELng());
            preparedStatement.setDouble(3, rect.getRectSWLat());
            preparedStatement.setDouble(4, rect.getRectSWLng());
            preparedStatement.setString(5, rect.getPlace());



            executeStatus = preparedStatement.executeUpdate();



            if (executeStatus >= 1) {
                String sql = "select last_insert_id()";

                if (preparedStatement.execute(sql)) {


                    ResultSet rs = preparedStatement.getResultSet();

                    while (rs.next()) {
                        rectId = rs.getInt(1);
                        if (rectId > 0)
                            rect.setRectId(rectId);
                    }
                } else {
                    System.out.println("Error in store rectangle");
                }
            }

        }


    }

    public void storeMapRectangle(int rectId, int mapId, int stopLimit) throws Exception{

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataCollection11", "root", "Mdsp1891#");
        if(connection != null)
            System.out.println("Connected successfully");

        String storeMapRectangle = "insert into MapRectangles values(?,?,?)";

        PreparedStatement preparedStatement = null;
        preparedStatement = (PreparedStatement) connection.prepareStatement(storeMapRectangle);
        preparedStatement.setInt(1,rectId);
        preparedStatement.setInt(2,mapId);
        preparedStatement.setInt(3,stopLimit);

        int execStatus = preparedStatement.executeUpdate();

        if (execStatus == 1) {
            return;
        }

        else{
            System.out.println("SOme error while processing store Map Rectangle");
        }




    }

    public void storeScientistRectangle(String suname, int rectId) throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataCollection11", "root", "Mdsp1891#");
        if (connection != null) {
            System.out.println("Connected successfully");
        }

        String storeSciRect = "insert into ScientistRectangle values ( ?,?);";

        PreparedStatement preparedStatement = null;
        int execStatus;


        preparedStatement = (PreparedStatement) connection.prepareStatement(storeSciRect);
        preparedStatement.setString(1, suname);
        preparedStatement.setInt(2, rectId);

        execStatus = preparedStatement.executeUpdate();

        if (execStatus == 1) {
            return;
        }

        else{
            System.out.println("SOme error while processing store Scientist Rectangle");
        }

    }


    public void deleteRectangle(RectangleSci rect) throws Exception {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataCollection11", "root", "Mdsp1891#");
        if (connection != null) {
            System.out.println("Connected successfully");
        }
        String deleteFromRectangle = "delete from RectDetails where NELat = ?, NELng=?, SWLat=?, SWLng = ?";

        PreparedStatement preparedStatement = null;
        int execStatus;


        preparedStatement = (PreparedStatement) connection.prepareStatement(deleteFromRectangle);
        preparedStatement.setDouble(1, rect.getRectNELat());
        preparedStatement.setDouble(2, rect.getRectNELng());
        preparedStatement.setDouble(3, rect.getRectSWLat());
        preparedStatement.setDouble(4, rect.getRectSWLng());

        execStatus = preparedStatement.executeUpdate();

        if (execStatus == 1) {
            return;
        }
        else
            System.out.println("Error while deleting data");


    }

}
