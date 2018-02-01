package com.Presentation;

import java.io.IOException;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Logic.LogicLayer;
import com.model.AirPollution;
import com.model.Rectangle;

/**
 * Servlet implementation class AirPollutionData
 */
public class AirPollutionData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AirPollutionData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String northlat=request.getParameter("northlat");
		String eastlng=request.getParameter("eastlng");
		String southlat=request.getParameter("southlat");
		String westlng=request.getParameter("westlng");
		String userlat=request.getParameter("userlat");
		String userlng=request.getParameter("userlng");

        HttpSession session=request.getSession();
        String uname=(String)session.getAttribute("uname");
		//DecimalFormat df = new DecimalFormat("#.###");
		//df.setRoundingMode(RoundingMode.HALF_UP);
		//String elng=df.format(Double.parseDouble(eastlng));
		System.out.println("north"+northlat);
		System.out.println("South"+southlat);
		//System.out.println("nlat"+elng);
		//String wlng=df.format(Double.parseDouble(westlng));
		//System.out.println("wlng"+wlng);
		Rectangle r=new Rectangle();
		r.setNlat(Double.parseDouble(northlat));
		r.setElng(Double.parseDouble(eastlng));
		r.setSlat(Double.parseDouble(southlat));
		r.setWlng(Double.parseDouble(westlng));
		LogicLayer l=new LogicLayer();
		int rid=0;
		try {
			rid=l.getrid(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String co=request.getParameter("co");
		String ho=request.getParameter("ho");
		String temperature=request.getParameter("temp");
		String humidity=request.getParameter("hum");
		AirPollution ap=new AirPollution();
		ap.setCo(Float.parseFloat(co));
		ap.setHo(Float.parseFloat(ho));
		ap.setHumidity(Float.parseFloat(humidity));
		ap.setTemperature(Integer.parseInt(temperature));
		ap.setUname(uname);
		ap.setUserlat(Float.parseFloat(userlat));
		ap.setUserlng(Float.parseFloat(userlng));
		boolean rep=false;
		int StopLimit=0;

		try {
			rep=l.checkrepitition(rid, ap);
			System.out.println("rep"+rep);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rep==false){
			System.out.println("rep is false");
			response.sendRedirect("RepeatedData.html");
			return;
		}//if
		else{
			try {
				StopLimit=l.getStopLimit(rid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//else
		boolean limit=false;
		try {
			limit=l.checkNumberofData(rid, StopLimit);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(limit){
			try {
				l.insertAirpollutionData(rid,ap);
				response.sendRedirect("Inserted.html");
				return;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}//if
		else{
			
				response.sendRedirect("StopLimit.html");
				return;
		}//else
		
        
	}//doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
