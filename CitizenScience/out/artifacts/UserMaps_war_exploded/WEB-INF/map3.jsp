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
     zoom: 11,
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

<form action="" onsubmit="draw()">
 <input type="text" name="incr" id="incr" value="0">
 <input name="act" type="submit"  value="submit">
</form>
<script>

<%
String lat1="";
String lng1="";
String lat2="";
String lng2="";
int m=0;
//ArrayList<String> itemsArray = (ArrayList<String>) request.getAttribute("servletName");
%>

function draw(){
	alert("in draw");
var i=0;

for(i=0;i<<%=itemsArray.size()%>;i++){
alert(<%=itemsArray.size()%>);

//var x=document.getElementById("incr").value;
<% 	
//out.println("m"+m);
lat=itemsArray.get(m).split(" ");
lat1=lat[2];
lng1=lat[3];
lat2=lat[4];
lng2=lat[5];
m=m+1;
%>
alert("m"+<%=m%>);
alert("lat1"+<%=lat1%>);
alert("lat2"+<%=lat2%>);
alert("lng2"+<%=lng2%>);
document.getElementById("incr").value=<%=m+1%>;
}//for
}//draw
</script>


<!--  <script>
var north=parseFloat(<%=lat1%>); 
alert("north"+north);
var south = parseFloat(<%=lat2%>);
alert("south"+south);
var east = parseFloat(<%=lng1%>);
alert("east"+east);
var west = parseFloat(<%=lng2%>);
alert("west"+west)

rectangle[j] = new google.maps.Rectangle({
 strokeColor: '#FF0000',
 strokeOpacity: 1.0,
 strokeWeight: 2,
 zoom:11,
 //fillColor: '#FF0000',
 //fillOpacity: 0.35,
 map: map,
 bounds:{
	  north:0,
      south:0,
      east:0,
      west:0
	 
 }
});
var NE = new google.maps.LatLng(north, east);
var SW = new google.maps.LatLng(south, west);
var newRect = new google.maps.LatLngBounds(SW, NE);
rectangle[j].setBounds(newRect);
alert("rectangle bounds"+rectangle[j].getBounds());
j++;
</script>-->
 <body>
 
 
<form action="RectangleCor" method="get" onsubmit="return myFunction()">
  <input type="submit" value="SubmitYourLocation">
  You Fall In:<br>
    
   <input type="hidden" name="northlat" id="northlat" value="northlat">
    
   <input type="hidden" name="eastlng" id="eastlng" value="eastlng">
    
   <input type="hidden" name="southlat" id="southlat" value="southlat">
    
   <input type="hidden" name="westlng" id="westlng" value="westlng">
   
   
   
   
</form>


<script>

/*google.maps.event.addListener(marker, 'click', function() {
	window.location.href = marker.url;
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
		​//alert("north"+nlat);
		//document.getElementById("northlat").value= nlat;
		window.location ="RectangleCor";
	}
	else{
		alert("Your Location Does not fall in given areas");
		window.location ="FailedCor";
	}


	//var output = document.getElementById('output');
	 //output.innerHTML = k;
	//return y;
	
	
	});*/
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

/*function myFunction(){
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
		var nlat=rectangle[k].getBounds().getNorthEast().lat()
		alert("north"+nlat)
		document.getElementById("northlat").value= nlat;
	}
	else{
		alert("Your Location Does not fall in given areas");
	}


	//var output = document.getElementById('output');
	 //output.innerHTML = k;
	return y;
	
}*/
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