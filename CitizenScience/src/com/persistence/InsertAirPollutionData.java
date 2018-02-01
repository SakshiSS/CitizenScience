package com.persistence;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.AirPollution;
import com.model.Rectangle;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class InsertAirPollutionData {
	
	public int getStopLimit(int rid) throws SQLException{
		ConnectionManager con=new ConnectionManager();
		Connection con1=con.getConnection();
		String s="select StopLimit from MapRectangles where RectId="+rid;

		PreparedStatement ps =null;
		try {
			ps = (PreparedStatement)con1.prepareStatement(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ps.setInt(1,rid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
		  return rs.getInt("stopLimit");
		}//while
		return 0;
		
  }//stoplimit
	
	public int getAirPollutionData(int rid) throws SQLException{
		ConnectionManager con=new ConnectionManager();
		Connection con1=con.getConnection();
		String s="select * from airpollution where RectId="+rid;

		PreparedStatement ps =null;
		try {
			ps = (PreparedStatement)con1.prepareStatement(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ps.setInt(1,rid);
		ResultSet rs = ps.executeQuery();
		int numberofdata=0;
		while(rs.next()){
			numberofdata++;
		}//while
        return numberofdata;
		
		
	}//getdata
	
	public void insertData(int rid,AirPollution ap) throws SQLException{
		ConnectionManager con=new ConnectionManager();
		Connection con1=con.getConnection();
		String s="insert into airpollution(UserName,RectId,Userlat,Userlng,CarbonMonoxide,HydrogenMonoxide,Temperature,Humidity) values(?,?,?,?,?,?,?,?)";
       
		PreparedStatement ps =null;
		try {
			ps = (PreparedStatement)con1.prepareStatement(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps.setString(1,ap.getUname());
		ps.setInt(2,rid);
		ps.setDouble(3,ap.getUserlat());
		ps.setDouble(4,ap.getUserlng());
		ps.setFloat(5,ap.getCo());
		ps.setFloat(6,ap.getHo());
		ps.setInt(7,ap.getTemperature());
		ps.setFloat(8,ap.getHumidity());
		ps.executeUpdate();
		
		
		
	}//inserData
	
	public boolean checkrepitition(int rid,AirPollution ap) throws SQLException{
		ConnectionManager con=new ConnectionManager();
		Connection con1=con.getConnection();
		String s="select CarbonMonoxide,HydrogenMonoxide,Temperature,Humidity from airpollution where RectId="+rid;

		PreparedStatement ps =null;
		try {
			ps = (PreparedStatement)con1.prepareStatement(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//ps.setInt(1,rid);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
		System.out.println("In rco"+rs.getFloat("co"));
	      float co=rs.getFloat("CarbonMonoxide");
	      float ho=rs.getFloat("HydrogenMonoxide");
	      int temp=rs.getInt("Temperature");
	      float humidity=rs.getFloat("Humidity");
	      //System.out.println("data"+co+"--"+ho+"--"+temp+"--"+humidity);
	      if(co==ap.getCo() && ho==ap.getHo() && temp==ap.getTemperature() && humidity==ap.getHumidity()){
	    	  return false;
	      }//if
	      
	    	  
		}//if
        return true;
		
		
		
		
		
	}//getrid
	public int getrid(Rectangle r) throws SQLException{
	
		ConnectionManager con=new ConnectionManager();
		Connection con1=con.getConnection();
		
		String s="select RectId from RectDetails where NELat=? and NELng=? and SWLat=? and SWLng=?";
		
		PreparedStatement ps =null;
		try {
			ps = (PreparedStatement)con1.prepareStatement(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ps.setDouble(1,r.getNlat());
		ps.setDouble(2,r.getElng());
		ps.setDouble(3,r.getSlat());
		ps.setDouble(4,r.getWlng());
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
		System.out.println("In rid"+rs.getInt("RectId"));
		return rs.getInt("RectId");
		}
		return -1;
		
	}//getrid

}
