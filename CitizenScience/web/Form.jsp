<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" import="java.util.*" %>
<%
String north=(String)request.getAttribute("north");
String east=(String)request.getAttribute("east");
String south=(String)request.getAttribute("south");
String west=(String)request.getAttribute("west");
String userlat=(String)request.getAttribute("userlat");
String userlng=(String)request.getAttribute("userlng");
out.println(north);
%>
<form action="AirPollutionData" method="get" onsubmit="return myFunction()">
   You Fall In:<br>
    North Latitude:<br>
   <input type="text" name="northlat" id="northlat" value=<%=north%>><br>
    East Longitude:<br>
   <input type="text" name="eastlng" id="eastlng" value=<%=east%>><br>
    South Latitude:<br>
   <input type="text" name="southlat" id="southlat" value=<%=south%>>
    West Longitude:<br>
   <input type="text" name="westlng" id="westlng" value=<%=west%>><br>
   Your Latitude:<br>
   <input type="text" name="userlat" id="userlat" value=<%=userlat%>><br>
   Your Longitude:<br>
   <input type="text" name="userlng" id="userlng" value=<%=userlng%>><br>
   
   
   <br>
   Enter Data:<br>
   CO Concentration:<br>
     <input type="text" name="co" id="co" value=""><br>
   HO Concentration:<br>  
     <input type="text" name="ho" id="ho" value=""><br>
   Temperature(F):<br>  
     <input type="text" name="temp" id="temp" value=""><br>
   Humidity:<br>  
     <input type="text" name="hum" id="hum" value=""><br>
   <input type="submit" value="SubmitYourLocation">
    
     
   
     
   <input>
</form>


<body>

</body>
</html>