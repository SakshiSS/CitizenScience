package com.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataLoc {
	
	
	
	
	public List<String> getPlaces() throws SQLException{
		ConnectionManager con=new ConnectionManager();
		Connection con1=con.getConnection();
		System.out.println("connection"+con1);
		Statement stmt=con1.createStatement();
		String s="select Place from RectDetails";
		ResultSet rs=stmt.executeQuery(s);
		List<String> places=new ArrayList<String>();
		while(rs.next()){
			String s1=rs.getString(1);
			places.add(s1);
		}//while
		
		for(int i=0;i<places.size();i++){
			System.out.println(places.get(i));
		}//for
		
		return places;
		
		
		
	}//getPlaces
	public List<String> getData(String loc) throws SQLException{
		
		ConnectionManager con=new ConnectionManager();
		Connection con1=con.getConnection();
		System.out.println("connection"+con1);
		Statement stmt=con1.createStatement();
		String s="select Lat, Lng, NELat, NELng, SWLat, SWLng, Place from rectdetails, mapdetails , maprectangles where rectdetails.RectId = maprectangles.RectId and maprectangles.MapId = MapDetails.MapId and Place ='"+loc+"'";
		ResultSet rs=stmt.executeQuery(s);
		List<String> latlng=new ArrayList<String>();
		while(rs.next()){
			String s1=rs.getDouble(1)+" "+rs.getDouble(2)+" "+rs.getDouble(3)+" "+
					rs.getDouble(4)+" "+rs.getDouble(5)+" "+rs.getDouble(6);
			latlng.add(s1);
		}//while
		
		for(int i=0;i<latlng.size();i++){
			System.out.println(latlng.get(i));
		}//for
		
		return latlng;
	}//getData

}
