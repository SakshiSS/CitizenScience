package com.Presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RectangleCor
 */
public class RectangleCor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RectangleCor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	   String north=request.getParameter("northlat");
	   String east=request.getParameter("eastlng");
	   String south=request.getParameter("southlat");
	   String west=request.getParameter("westlng");
	   String userlat=request.getParameter("userlat");
	   String userlng=request.getParameter("userlng");
	   
	   System.out.println("north"+north);
	   request.setAttribute("north",north);
	   request.setAttribute("east",east);
	   request.setAttribute("south",south);
	   request.setAttribute("west",west);
	   request.setAttribute("userlat",userlat);
	   request.setAttribute("userlng",userlng);
	   
	   
	   
	   
	   request.getRequestDispatcher("Form.jsp").forward(request,response);
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
