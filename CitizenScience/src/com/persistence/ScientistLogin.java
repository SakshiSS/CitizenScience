package com.persistence;


import java.sql.*;

/**
 * Created by marne on 11/17/2016.
 */
public class ScientistLogin {

    public boolean authorizeScientist(String uname,String pwd) throws SQLException {


        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            //log.error( "DbUtils.connect:  unable to find JDBC Driver", ex );
            System.out.println("exception with connection");
            ex.printStackTrace();

        }




        //ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DataCollection11","root","Mdsp1891#");
        if(connection != null){
            System.out.println("Connected successfully");
        }

        String sql ="Select * from Scientist";

        System.out.println("Came to persistence layer");

        PreparedStatement preparedStatement = null;

        preparedStatement = (PreparedStatement) connection.prepareStatement(sql);

        ResultSet rs = preparedStatement.executeQuery();

        while(rs.next()){

            String unameDB = rs.getString("SUName");
            String pwdDB = rs.getString("Password");

            if(uname.equals(unameDB) && pwd.equals(pwdDB)){
                return true;
            }//




        }//while
        return false;

    }

}
