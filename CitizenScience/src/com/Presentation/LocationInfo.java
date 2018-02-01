package com.Presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Logic.LogicLayer;
import com.persistence.DataLoc;

/**
 * Servlet implementation class LocationInfo
 */

public class LocationInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String loc=request.getParameter("act");
		System.out.println("place in servlet::"+loc);
		
		//DataLoc d=new DataLoc();
		LogicLayer l=new LogicLayer();
		List<String> latlng=new ArrayList<String>();
		try {
			latlng=l.getLatLng(loc);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("servletName",latlng);
		request.getRequestDispatcher("WEB-INF/map5.jsp").forward(request,response);
		
		
		
	
	}//doGet

}
