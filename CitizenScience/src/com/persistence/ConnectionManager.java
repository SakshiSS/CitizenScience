package com.persistence;



import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionManager{
	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Connection con=null;
		try {
			con = (Connection)DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/DataCollection11","root","Mdsp1891#");
			System.out.println("connection"+con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	
}