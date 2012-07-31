package com.redhat.consulting.service;
	import java.io.*;  
	import java.sql.*;  
	import javax.servlet.*;  
	import javax.servlet.http.*; 
	
	
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L; 

	      
	  public void init(ServletConfig config) throws ServletException{  
	    super.init(config);  
	  }  
	  
	public void doGet(HttpServletRequest req, HttpServletResponse res)   
	  throws ServletException, IOException{  
	       
	    String connURL = "jdbc:postgresql://localhost:5432/postgres";  
	    Connection conn = null;  
	    res.setContentType("text/html");  
	    PrintWriter out = res.getWriter();  
	    String name = req.getParameter("name");  

	    try {  
	      // Load the postgres driver, connect, add to database
	      Class.forName("org.postgresql.Driver");    
	      conn = DriverManager.getConnection(connURL, "postgres", "admin");   
	      PreparedStatement st = conn.prepareStatement("INSERT into sample VALUES(?,?)");  
	   
	      st.setString(1,name);  
	              
	      int numRowsChanged = st.executeUpdate();  
	       if(numRowsChanged!=0){    
	                 out.println("Record has been inserted");    
	               }    
	               else{    
	                 out.println("Fail! No data inserted");    
	                }    
	      st.close();  
	    }  
	    catch(ClassNotFoundException e){  
	      out.println("Couldn't load driver: " + e.getMessage());  
	    }  
	    catch(SQLException e){  
	      out.println("SQLException: " + e.getMessage());  
	    }  
	    catch (Exception e){  
	      out.println(e);  
	    }  
	    finally {   
	      try {  
	        if (conn != null) conn.close();  
	      }  
	      catch (SQLException ignored){  
	        out.println(ignored);  
	      }  
	    }  
	      
	}
}
	   