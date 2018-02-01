package com.Presentation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Logic.LogicLayer;
import com.model.User;
import com.persistence.DataLoc;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	String regist=request.getParameter("register");
	   	
		String login=request.getParameter("login");
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		boolean exist=false;
		if(login!=null){
			System.out.println("Login");
			User u=new User();
			u.setUname(uname);
			u.setPwd(pwd);
			LogicLayer l=new LogicLayer();
			try {
				exist=l.checkuserinfo(uname, pwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(exist){
			
				HttpSession session=request.getSession();  
		        session.setAttribute("uname",uname);  
				LogicLayer l1=new LogicLayer();
				List<String> places=new ArrayList<String>();
				
				try {
					places=l1.getplaces();
					Set<String> setplaces=new HashSet<String>();
					setplaces.addAll(places);
					places.clear();
					places.addAll(setplaces);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				request.setAttribute("places",places);
				request.getRequestDispatcher("Locations.jsp").forward(request,response);
			}//if
			else{
				response.sendRedirect("FailedLogin.html");
			}//else
			
			
		}//if
		if(regist!=null){
			System.out.println("Register");
		}
	   	
		
		
	}//doGet

	
}
