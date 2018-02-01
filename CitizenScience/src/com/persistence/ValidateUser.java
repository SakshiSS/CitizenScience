package com.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ValidateUser {
	
	public boolean checkinfo(String uname,String pwd) throws SQLException{
		ConnectionManager con=new ConnectionManager();
		Connection con1=con.getConnection();
		String s="select Uname,Password from User";
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
		   String uname1=rs.getString("Uname");
		   String pwd1=rs.getString("Password");
		   if((uname1.equals(uname)) &&(pwd1.equals(pwd))){
			   return true;
		   }//if
		}//while
		return false;
	}//checkinfo

}
