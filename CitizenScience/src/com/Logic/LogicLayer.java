package com.Logic;

import java.sql.SQLException;
import java.util.List;

import com.model.AirPollution;
import com.model.Rectangle;
import com.persistence.DataLoc;
import com.persistence.InsertAirPollutionData;
import com.persistence.ValidateUser;

public class LogicLayer {
	
	public List<String> getplaces() throws SQLException{
		DataLoc d=new DataLoc();
		return d.getPlaces();
	}//getPlaces
	
	public List<String> getLatLng(String loc) throws SQLException{
		DataLoc d=new DataLoc();
		return d.getData(loc);
	}//getLatLng
	public boolean checkuserinfo(String uname,String pwd) throws SQLException{
		ValidateUser v=new ValidateUser();
		return v.checkinfo(uname,pwd);
		
		
	}//checkinfo
	public int getrid(Rectangle r) throws SQLException{
      InsertAirPollutionData i=new InsertAirPollutionData();
      return i.getrid(r);
	}
	
	public boolean checkrepitition(int rid,AirPollution a) throws SQLException{
		  InsertAirPollutionData i=new InsertAirPollutionData();
		  return i.checkrepitition(rid, a);
	}//checkrepitition
	
	public void insertAirpollutionData(int rid,AirPollution ap) throws SQLException{
		InsertAirPollutionData i=new InsertAirPollutionData();
		i.insertData(rid, ap);
	}//insert
	
	public int getStopLimit(int rid) throws SQLException{
		InsertAirPollutionData i=new InsertAirPollutionData();
			return i.getStopLimit(rid);
		
		
	}//getStopLimit
	
   public boolean checkNumberofData(int rid,int StopLimit) throws SQLException{
	InsertAirPollutionData i=new InsertAirPollutionData();
	int numberofdata=0;
	numberofdata=i.getAirPollutionData(rid);
	if(numberofdata<StopLimit){
		return true;
	}//if
	return false;
	}//numberofdata

}
