package com.Presentation;

import com.Logic.SciLogicLayer;
import com.model.Scientist;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by marne on 11/17/2016.
 */
//@WebServlet(name = "SciLoginServlet")
public class SciLoginServlet extends HttpServlet {


    private static final long serialVersionUID = 1L;

    public SciLoginServlet(){
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String register = request.getParameter("btnRegister");
        String login = request.getParameter("btnLogin");

        String uname = request.getParameter("txtUname");
        String pwd = request.getParameter("txtPwd");

        Scientist scientist = new Scientist();
        SciLogicLayer logic = new SciLogicLayer();
        Boolean authorized = false;
        if(login != null){
            System.out.println("login button clicked "+login);
            scientist.setName(uname);
            scientist.setPassword(pwd);
            try {
                authorized = logic.authorizeScientist(scientist.getName(), scientist.getPassword());
            }
            catch(SQLException se){
                System.out.println("Sql exception caught");
                se.printStackTrace();
            }

        }

        if(authorized){

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("suname",uname);

            System.out.println("before redireting to Scientist");

            response.sendRedirect("ScientistMapPage.html");

        }
        else{

            RequestDispatcher rd=request.getRequestDispatcher("/SLoginPage.html");
            request.setAttribute("error","Incorrect Username or password");

            out.println("<script>");
            out.println("alert('Incorrect login');");
            out.println("</script>");

//            out.println("<script>");
//            out.println("document.getElementById('error').innerText=Incorrect UserName or Password Error");
//            out.println("</script>");

            rd.include(request, response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
