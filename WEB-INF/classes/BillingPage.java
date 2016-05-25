

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import sdsu.*;
import helpers.*;
import java.util.logging.*;

public class BillingPage extends HttpServlet {
    	private ServletContext context=null;
	private RequestDispatcher dispatcher = null;
        private String toDo = "";  
    
    public void doGet(HttpServletRequest request,
              HttpServletResponse response)
                        throws IOException, ServletException  {
     List<String> summaryList = new ArrayList<String>();
        				String subtotal = request.getParameter("subtotal");
         				String tax = request.getParameter("tax");
         				String total = request.getParameter("total");
                         summaryList.add(subtotal);
                         summaryList.add(tax);
                         summaryList.add(total);
                         
 	    request.setAttribute("summary", summaryList);
        toDo = "/WEB-INF/Ass3_JSP/billing_form.jsp";     
        dispatcher = request.getRequestDispatcher(toDo);
         
        dispatcher.forward(request, response);  
        
                        
    }  
    
        public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	doGet(request, response);
    }  
}



