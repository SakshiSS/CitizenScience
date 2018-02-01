<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<body>
<%@ page language="java" import="java.util.*" %> 
<% List<String> mylist=(ArrayList<String>) request.getAttribute("places");
%>
<form action="LocationInfoPath">			
	
	 <select name="act">
    <% String plc=""; for(int i=0;i<mylist.size();i++){ plc=mylist.get(i);%>
        <option><%out.print(plc);%></option>
    <%}%>
</select>

<input name="btn" type="submit"  value="submit">			
</form>
<a href="Logout">Logout</a>

</body>
</html>