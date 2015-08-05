<%-- 
    Document   : check_user
    Created on : Jul 26, 2015, 2:51:52 PM
    Author     : bimal
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="database.services.DbConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int count=0;
         String name=request.getParameter("username");
         DbConnection db = new DbConnection();
         PreparedStatement pst = db.con.prepareStatement("select username from patient_info where username=?");
         pst.setString(1,name);
         db.rs =pst.executeQuery();
         while(db.rs.next())
         {
             count++;
         }
         if(count==0)
         {
             out.println("valid username");
         }
         else
         {
             out.println("user name already exists");
         }
        %>
    </body>
</html>
