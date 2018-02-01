<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" import="java.util.*" %>
<%
String[] lat=new String[6];
String initlat="";
String initlng="";
ArrayList<String> itemsArray = (ArrayList<String>) request.getAttribute("servletName");
lat=itemsArray.get(0).split(" ");
initlat=lat[0];
initlng=lat[1];%>

<form action="RectangleCor" method="get" onsubmit="return myFunction()">
  <input type="submit" value="SubmitYourLocation">
  You Fall In:<br>
    North Latitude:<br>
   <input type="text" name="northlat" id="northlat" value="northlat">
    North Latitude:<br>
   <input type="text" name="eastlng" id="eastlng" value="eastlng">
    North Latitude:<br>
   <input type="text" name="southlat" id="southlat" value="southlat">
    North Latitude:<br>
   <input type="text" name="westlng" id="westlng" value="westlng">
   
   
   
   
</form>

       <div id="map_canvas" style="width:400px; height:400px"></div>
          <p>latitude: <span id="lat"></span></p>
          <p>longitude: <span id="lng"></span></p>

<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDVgNI2snXEm8Cu5y-dxk0Y1ep9Iv3SOC4&sensor=false&libraries=drawing,geometry"></script>
 <style>
    .google-map-container{
        width: 600px;
        height:600px;
    }

</style>
</table>

<script type="text/javascript">

var s=<%=initlat%>; 
alert(s); 
var s1=<%=initlng%>; 
alert(s1); 
var map;
var home;

home = new google.maps.LatLng(s,s1);   
var opts = {
     zoom: 8,
     center: home,
     mapTypeId: google.maps.MapTypeId.ROADMAP
};

map = new google.maps.Map(document.getElementById('map_canvas'), opts);

var marker = new google.maps.Marker({
    position: new google.maps.LatLng(s,s1),
    map: map,
    title: 'Hello World!',
    draggable:true
  });

var rectangle=[];
var j=0;
</script>

<%
String lat1="";
String lng1="";
String lat2="";
String lng2="";
//out.println(itemsArray.size());
//ArrayList<String> itemsArray = (ArrayList<String>) request.getAttribute("servletName");
for(int i=0;i<itemsArray.size();i++){
lat=itemsArray.get(i).split(" ");
lat1=lat[2];
lng1=lat[3];
lat2=lat[4];
lng2=lat[5];
%>
<script>
alert("north"+<%=lat%>)
var north=parseFloat(<%=lat1%>); 
alert("north"+north);
var south = parseFloat(<%=lat2%>);
var east = parseFloat(<%=lng1%>);
var west = parseFloat(<%=lng2%>);


rectangle[j] = new google.maps.Rectangle({
 strokeColor: '#FF0000',
 strokeOpacity: 0.8,
 strokeWeight: 2,
 fillColor: '#FF0000',
 fillOpacity: 0.35,
 map: map,
 bounds:{
	  north:0,
      south:0,
      east:0,
      west:0
	 
 }
});
var NE = new google.maps.LatLng(north,east);
var SW = new google.maps.LatLng(south,west);
var newRect = new google.maps.LatLngBounds(SW, NE);
rectangle[j].setBounds(newRect);
j++;
<%
}%>

</script>
 <body>
 
 

<script>

function myFunction(){
	var k=0;
	for(k=0;k<j;k++){
		alert("k"+k+"-->"+j);
		alert("rectangle"+rectangle[k].getBounds());
		var id =document.getElementById("loc1");
		var loclat=marker.getPosition().lat();
		alert(loclat);
		var id1 =document.getElementById("loc2");
		var loclng=marker.getPosition().lng();
		alert(loclng);
		
		alert("other script");
		

			
		var temp = new google.maps.LatLng(loclat,loclng);
		var y=rectangle[k].getBounds().contains(temp);
		if(y){
			alert("break");
			break;
		}
		
	}//for
	alert("k"+k);
	alert(y);
	if(y){
		//alert();
		var nlat=rectangle[k].getBounds().getNorthEast().lat();
		var elang=rectangle[k].getBounds().getNorthEast().lng();
		var slat=rectangle[k].getBounds().getSouthWest().lat();
		var wlng=rectangle[k].getBounds().getSouthWest().lng();

		alert("north"+nlat);
		alert("east"+elang);
		alert("south"+slat);
		alert("west"+wlng);
		document.getElementById("northlat").value= nlat;
		document.getElementById("eastlng").value= elang;
		document.getElementById("southlat").value=slat;
		document.getElementById("westlng").value=wlng;
	}
	else{
		alert("Your Location Does not fall in given areas");
		window.location ="FailedCor";
	}


	//var output = document.getElementById('output');
	 //output.innerHTML = k;
	return y;
	
}
</script>
<!--  <form action="" onsubmit="validateFormOnSubmit(this)" method="post">
  Enter Latitude:<br>
  <input type="text" name="lat" value="">
  <br>
  Last Longitude:<br>
  <input type="text" name="lng" value="">
  <br><br>
  <input type="submit" value="Submit">
</form>-->

<!--  <script>
function validateFormOnSubmit(this){
	alert("In Validate");
	var temp = new google.maps.LatLng(33.680,-116.240);
	//var x=google.maps.geometry.poly.containsLocation(temp,rectangle)
	var x=rectangle.getBounds().getCenter();
	var y=rectangle.getBounds().contains(temp); 
	alert(y);
		//var center = bounds.getCenter();  // still returns (55.04334815163844, -1.9917653831249726)
	    //alert(temp);
		//var x = rectangle.
	//alert("x"+x);
	
}

</script>
-->
</body>


</html>