
<%@page import="java.sql.PreparedStatement"%>
<%@page import="database.services.Patientinfo"%>
<%@page import="database.services.DbConnection"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<html>
<head>
<title>MDDSS</title>

<link href="css/style.css" rel="stylesheet"/>
<script src="library/jquery-1.11.3.min.js" language="javascript">
    
</script>
<script>
        $(document).ready(function(){
           var piles_count=0;
           var piles_array=[];
           var check;
           var datastring ="0"; 
                  $("#yes1,#yes2,#yes3,#yes4,#yes5").click(function(){
                      piles_count++;
                     
                     
                     
                      var id =$(this).attr("id");
                       piles_array=id;
                       
                     
                        id=id.toString();
                        
                     id=id.substring(3,id.length);
                      datastring =datastring+" "+id;
                       
                      
                      
                      if(piles_count===2)
                      {
                          datastring='data='+datastring;
                          
                          $.ajax({
                            type: "POST",
                            url: "fetchsymptom",
                            data: datastring,
                            cache: false,
                            success: function(data)
                            {
                            
                            }
			});
                        //end of the ajax.
                      }
                      //end of if statement.
                  });
                  $("#no1,#no2,#no3,#no4,#no5").click(function(){
                     if($(this)===$("#no1"))
                     {
                          check="yes1";
                     }
                     if($(this)===$("#no2"))
                     {
                          check="yes2";
                     }
                     if($(this)===$("#no3"))
                     {
                          check="yes3";
                     }
                     if($(this)===$("#no4"))
                     {
                          check="yes4";
                     }
                    for(i=0;i<5;i++)
                     {
                         if(piles_array[i]===check)
                         {
                             piles_count--;
                             piles_array[i]=0;
                         }
                         
                     }
                    // alert(piles_count);
                     
                  });
                  
                  
                 
           
            
        });
        
</script>
</head>
<body>
    <% 
       String username =(String)session.getAttribute("username");
       
    
      String name=null;
      int Age=0;
      String Gender=null;
      if((username!=null))
       {
       Patientinfo record = new Patientinfo();
       record.recieveData(username);
        name = record.getName();
      
       Age = record.getAge();
      
       Gender = record.getGender();
           
        }
      else
      {
          RequestDispatcher rs =request.getRequestDispatcher("index.jsp");
          rs.forward(request, response);
      }
       
    %>
<div class="container">
	
	
		<div id="header"><div id="banner"><img src="img/logo_mddss.jpg" /></div>
		
		
		
			<div class="navigation">
					<div class="anchor_control">
					<a href="#" >Home</a>
					<a href="#">Blogs</a>
					<a href="#">Activity</a>
					<a href="#">Documentation</a>
					<a href="#">Contact Us</a>
					</div>
			</div><!-- end of navigation--->
		</div>
		<div id="triangle"></div>
		
		
		
		<div class="sideMenu">
		<div id="welcome">Patient Information
                 <table>
                  <tr>  
                      <th>Username:</th><td><%=username%></td>
                  </tr>
                  <tr>  
                      <th>Name:</th><td><%=name%></td>
                  </tr>
                  <tr>  
                      <th>Age:</th><td><%=Age%></td>
                  </tr>
                   <tr>  
                      <th>Gender:</th><td><%=Gender%></td>
                  </tr>
                 <tr>
                     <th></th>
                     <td>
                         <a href="logout.jsp">Exit</a>
                     </td>
                   </tr>
                 
                </table>
                </div>
		
			
									
				
		</div><!-- End of sideMenu -->
		<div class="right_sidebar"> 
		
		<form action="proceed" method="post" >
                <h2>Select Symptoms</h2><br/></br>
                               <%
                    int disease_id=0;
       int symptoms_id;
       String symptoms;
       int count=1;
       
       int piles_count=0,pneumonia_count=0,asthma_count=0,counter=1;
      
       try {
           DbConnection db = new DbConnection();
         while(count<4)
         {
           PreparedStatement pst=db.con.prepareStatement("select id,symptom from symptoms where disease_id=?");
           pst.setInt(1,disease_id);
           db.rs = pst.executeQuery();
           while(db.rs.next())
           {
               
             symptoms_id=db.rs.getInt(1);
             symptoms=db.rs.getString("symptom");
           //  System.out.println("Symptoms_id is "+symptoms_id+" symptoms is "+symptoms+" disease id is"+disease_id);
             %>
             <div style="border-color: #DDDDDD;margin-bottom:5px;padding-top:5px;padding-left: 5px;">
                 <%=counter+".  "%><%=symptoms%>&nbsp;&nbsp; 
                  <div style="float:right;"class="radiocheck">
                      <input type="radio" name="symptom<%=symptoms_id%>" value="<%= disease_id+" "+symptoms_id%>" id="yes<%=symptoms_id%>"><label for="yes<%=symptoms_id%>">Yes</label>
                      <input type="radio" name="symptom<%=symptoms_id%>" value="null" id="no<%=symptoms_id%>"><label for="no<%=symptoms_id%>">No</label>
                   &nbsp;&nbsp;</div> </br></br>
                    
             </div>

             
                <%
                counter ++;
             if(disease_id==0)
             {
                 piles_count++;
                 if(piles_count==2)
                 {
                     disease_id=1;
                     break;
                 }
             }
             if(disease_id==1)
             {
                 pneumonia_count++;
                 if(pneumonia_count==3)
                 {
                   disease_id=2;
                   break;
                 }
             }
             if(disease_id==2)
             {
                 asthma_count++;
                 if(asthma_count==3)
                 {
                     break;
                 }
             }
           }
           /*
           * end of the inner while loop
            */
           count++;
         }
         /* end of the  outer while loop*/
       } catch (Exception ex) {
          ex.getMessage();
       }
                    
                %>
                 <br/>
                 <input type="submit" name="btn" id="submit_btn" value="Proceed" style="padding:5px;"> 
                

            </form>
	
			
		</div><!-- End of right sidebar --->
		
		
		
		<div  class="bottom_result_bar">
		<h3> Result :</h3>
                <%
             String result =(String)request.getAttribute("message");
            if(result!=null)
            {
                 out.println(result);
            }
        %>
		</div> <!-- End of bottom result div -->
	
		
	
	</div> <!-- end of container div -->


<div id="footer"><h3 align="center">The Project is under development</h3></div>



</body>


</html>
