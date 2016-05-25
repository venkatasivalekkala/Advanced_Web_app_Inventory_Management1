

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import sdsu.*;
import helpers.*;
import java.util.logging.*;
import java.sql.*;
import java.util.Calendar;


public class ConfirmPage extends HttpServlet {
    	private ServletContext context=null;
	private RequestDispatcher dispatcher = null;
        private String toDo = "";  
        
      
          
    public void doGet(HttpServletRequest request,
              HttpServletResponse response)
                        throws IOException, ServletException  {



String ParameterNames = "";
HashMap<String, String> skuQuantityMap = new HashMap<String, String>();
Calendar calendar = Calendar.getInstance();
java.sql.Date today = new java.sql.Date(calendar.getTime().getTime());
String merchandiseOutQuery="Insert into merchandise_out (sku, date, qty) values ";
String onHandQuery="update on_hand set on_hand_quantity = CASE ";
String quantity="0";
String skuIn="(";
boolean isFirstElement=true;
			for(Enumeration e = request.getParameterNames(); e.hasMoreElements(); ){
			
			
				ParameterNames = (String)e.nextElement();
				ParameterNames = ParameterNames.trim();
				quantity = request.getParameter(ParameterNames.trim());
				if(ParameterNames.trim().equals("shippingName") == false && ParameterNames.trim().equals("shipAddress") == false && ParameterNames.trim().equals("shipCity") == false && ParameterNames.trim().equals("shipStateAndZip") == false && ParameterNames.trim().equals("subtotal") == false && ParameterNames.trim().equals("tax") == false && ParameterNames.trim().equals("total") == false ) {
				if(isFirstElement) {
				isFirstElement = false;
				merchandiseOutQuery +="('"+ParameterNames+"','"+today+"','"+request.getParameter(ParameterNames.trim())+"')";
				skuIn+="'"+ParameterNames+"'";
				} else {
				skuIn+=",'"+ParameterNames+"'";

				merchandiseOutQuery +=",('"+ParameterNames+"','"+today+"','"+request.getParameter(ParameterNames.trim())+"')";
				}
				onHandQuery+="when sku='"+ParameterNames+"' then on_hand_quantity-"+quantity+" ";
				
				
				}
				
			}  
			
			skuIn+=")";
				onHandQuery+="end WHERE sku in"+skuIn;

			
			if(merchandiseOutQuery != "") {
			Proj3_DBHelper.queryOnly(merchandiseOutQuery);
			}  
			
			if(onHandQuery != "") {
			Proj3_DBHelper.queryOnly(onHandQuery);
			}   

 String shippingName = request.getParameter("shippingName");
		   String shippingAddress = request.getParameter("shipAddress");
			String ShippingCity = request.getParameter("shipCity");
 
		String ShippingStateAndZip = request.getParameter("shipStateAndZip");
		String subtotal = request.getParameter("subtotal");
		String tax = request.getParameter("tax");
		String total = request.getParameter("total");
		
		List<String> confirmDetails = new ArrayList<String> ();
		confirmDetails.add(shippingName);
		confirmDetails.add(shippingAddress);
		confirmDetails.add(ShippingCity);
		confirmDetails.add(ShippingStateAndZip);
		confirmDetails.add(subtotal);
		confirmDetails.add(tax);
		confirmDetails.add(total);
		
		request.setAttribute("confirm",confirmDetails);
		  Logger.getLogger (ConfirmPage.class.getName()).log(Level.INFO, "confirmDetails="+confirmDetails); 
		toDo = "/WEB-INF/Ass3_JSP/confirm_page.jsp";     
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



