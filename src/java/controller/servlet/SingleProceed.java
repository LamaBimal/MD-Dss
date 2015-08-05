/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import database.services.CheckLogin;
import java.io.IOException;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bimal
 */
@WebServlet(name = "SingleProceed", urlPatterns = {"/SingleProceed"})
public class SingleProceed extends HttpServlet {

    public SingleProceed()
    {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        String username= request.getParameter("username");
        CheckLogin cl = new CheckLogin();
        int count =cl.check(username);
        out.println("The value of count "+count);
        if(count==1)
        {
        request.setAttribute("username", username);
        HttpSession s =request.getSession();
        s.setAttribute("username",username);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("nextpage.jsp");
        dispatcher.forward(request, response);
        
        }
        else
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
             dispatcher.forward(request, response);
        
        }
        
        
    }

    
}
