

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import helpers.*;

public class GetVendorCategory extends HttpServlet 
{
	String query;
	String answer;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException  
        {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");

		response.setContentType("text/html"); 

			if(id != null && id.trim().equals("cat")) {
         	query = "select * from category ORDER by name";
         	answer = Proj3_DBHelper.query(query);
         	} else if(id != null && id.trim().equals("vendor")){
         	query = "select * from vendor ORDER by name";
         	answer = Proj3_DBHelper.query(query);
         	}
     
	out.println(answer);
    }
    
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	doGet(request, response);
    }  
}



