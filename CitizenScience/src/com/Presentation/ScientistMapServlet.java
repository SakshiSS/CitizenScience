package com.Presentation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.swing.text.Style;

import com.Logic.SciLogicLayer;
import com.model.Map;
import com.model.MapRectangles;
import com.model.RectangleSci;
import org.json.JSONObject;


/**
 * Created by marne on 11/16/2016.
 */

public class ScientistMapServlet extends javax.servlet.http.HttpServlet {

    private static final long serialVersionUID = 1L;


    public ScientistMapServlet() {
        super();
    }


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        String jsonData = request.getParameter("data");


        if (jsonData != null) {
            System.out.println("jsong data from angulalr http is " + jsonData);
        }


        //String ssid =  (String) httpSession.getAttribute("ssid");
        //Session session = SessionManager.getSessionById(ssid);
        System.out.println("Inside servlet doPost maprectangle");

        PrintWriter out = response.getWriter();

        Map map = new Map(0, 0, 0);
        RectangleSci rect = new RectangleSci(0, 0, 0, 0, 0,null);
        SciLogicLayer logic = null;



        String scientistId = httpSession.getAttribute("suname").toString();
        System.out.println("scientist id is "+scientistId);
        int mapId = 0;
        int rectId = 0;


        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }


        JSONObject jsonObject = new JSONObject(sb.toString());
        logic = new SciLogicLayer();
        map.setMapCenterLat(jsonObject.getDouble("vCenterLat"));
        map.setMapCenterLng(jsonObject.getDouble("vCenterLng"));
        try {
            if (!map.isPersistent()) {
                logic.updateMapDetails(map);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        //rect.setStopLimit(jsonObject.getInt("stopLimit"));
        rect.setRectNELat(jsonObject.getDouble("rectNLat"));
        rect.setRectNELng(jsonObject.getDouble("rectNLng"));
        rect.setRectSWLat(jsonObject.getDouble("rectSLat"));
        rect.setRectSWLng(jsonObject.getDouble("rectSLng"));
        rect.setPlace(jsonObject.getString("place"));

        try {
            if(!rect.isPersistent()){
                logic.updateRectDetails(rect);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Json Object is: " + jsonObject.toString());

        System.out.println("Inside servlet maprectangle");
        //logic  = session.getLogicLayer();


    System.out.println("Map status in servlet "+map.isPersistent()+ " rect status "+rect.isPersistent());

        MapRectangles mr = new MapRectangles(0,0,0);
        mr.setMapId(map.getMapId());
        mr.setRectId(rect.getRectId());
        mr.setStopLimit(jsonObject.getInt("stopLimit"));

        try {

            logic.storeMapRectangle(mr.getRectId(), mr.getMapId(), mr.getStopLimit());
        }catch(Exception e){
            System.out.println("error while inserting into map rectangles");
            e.printStackTrace();
        }

        try{

            logic.storeScientistRectangle(scientistId,rect.getRectId());
            System.out.println("ScientistRectangle  details updated successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(map.isPersistent() && rect.isPersistent()) {

            System.out.println("map and rectangle id's update successfully");
            RequestDispatcher rd = request.getRequestDispatcher("/ScientistMapPage.html");
            //request.setAttribute("error","Incorrect Username or password");

            out.println("<html><body><script>alert('Update successfully')</script></body></html>");

//            out.println("<script>");
//            out.println("alert('Updated successfully');");
//            out.println("</script>");


            rd.include(request, response);
        }
        else{
            RequestDispatcher rd = request.getRequestDispatcher("/ScientistMapPage.html");
            //request.setAttribute("error","Incorrect Username or password");

            out.println("<script>");
            out.println("alert('Something went wrong');");
            out.println("</script>");


            rd.include(request, response);

        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

    }
}
