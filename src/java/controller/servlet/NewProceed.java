/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

import database.services.Patientinfo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bimal
 */
public class NewProceed extends HttpServlet {

    public NewProceed()
    {
        super();
    }
       @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name=request.getParameter("name");
        System.out.println("Inputed name is "+name);
        String age_data = request.getParameter("age");
        int age = Integer.parseInt(age_data);
        String gender =request.getParameter("gender");
        String username=request.getParameter("username");
        
        Patientinfo object = new Patientinfo();
        object.setName(name);
        object.setGender(gender);
        object.setUsername(username);
        object.setAge(age);
        object.saveRecord();
        request.setAttribute("username", username);
        HttpSession session= request.getSession();
        session.setAttribute("username",username);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/nextpage.jsp");
        dispatcher.forward(request,response);
        
    }

  }
