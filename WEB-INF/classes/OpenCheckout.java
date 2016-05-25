

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import sdsu.*;
import helpers.*;
import java.util.logging.*;

public class OpenCheckout extends HttpServlet {
    	private ServletContext context=null;
	private RequestDispatcher dispatcher = null;
        private String toDo = "";  
          
    public void doGet(HttpServletRequest request,
              HttpServletResponse response)
                        throws IOException, ServletException  {


		 String checkoutProduct = request.getParameter("checkoutProducts"); 
		 String shippingName = request.getParameter("ShippingFirstName");
		  String shippingLastName = request.getParameter("ShippingLastName");
		   String shippingAddress1 = request.getParameter("ShippingAddress1");
		   
		   String ShippingAddress2 = request.getParameter("ShippingAddress2");
		   
		   String ShippingCity = request.getParameter("ShippingCity");
		   
		   String ShippingState = request.getParameter("ShippingState");
		   
		   String ShippingZip = request.getParameter("ShippingZip");
		 


		if(checkoutProduct != null){
    		String query="select vendor.name, category.name, product.vendorModel, product.retail, product.image, product.sku, on_hand.on_hand_quantity FROM product left join on_hand on product.sku=on_hand.sku inner join vendor ON vendor.vendorID=product.venID inner join  category ON category.categoryID = product.catID WHERE product.sku in "+checkoutProduct;
            String answer = Proj3_DBHelper.query(query);

    		request.setAttribute("checkoutProducts", answer);
    		}
    		
    		List<String> shippingDetails = new ArrayList<String> ();
    		shippingDetails.add(shippingName+" "+shippingLastName );
    		if(ShippingAddress2 != null) {
    		shippingDetails.add(shippingAddress1+", "+ShippingAddress2);
    		
    		} else {
    		shippingDetails.add(shippingAddress1);
    		}
    		shippingDetails.add(ShippingCity);
    		shippingDetails.add(ShippingState);
    		shippingDetails.add(ShippingZip);
    		
		request.setAttribute("shippingDetails", shippingDetails);
        toDo = "/WEB-INF/Ass3_JSP/checkout_product.jsp";     
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



