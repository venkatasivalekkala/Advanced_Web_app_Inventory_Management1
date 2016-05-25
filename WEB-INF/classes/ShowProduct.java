
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import sdsu.*;
import helpers.*;
import java.util.logging.*;

public class ShowProduct extends HttpServlet {
    	private ServletContext context=null;
	private RequestDispatcher dispatcher = null;
        private String toDo = "";  
          
    public void doGet(HttpServletRequest request,
              HttpServletResponse response)
                        throws IOException, ServletException  {

		 String sku = request.getParameter("sku");

		if(sku != null){
    		String query="select vendor.name, category.name, product.vendorModel, product.description, product.features , product.retail, product.image, on_hand.on_hand_quantity, product.sku FROM product left join on_hand on product.sku=on_hand.sku inner join vendor ON vendor.vendorID=product.venID inner join  category ON category.categoryID = product.catID WHERE product.sku='"+sku.trim()+"'";

    		ArrayList<String> product = Proj3_DBHelper.queryProductDetails(query);
    		request.setAttribute("Product", product);
    		}

        toDo = "/WEB-INF/Ass3_JSP/show_product.jsp";     
        dispatcher = request.getRequestDispatcher(toDo); 
        dispatcher.forward(request, response);  
        
                        
    }    
}



