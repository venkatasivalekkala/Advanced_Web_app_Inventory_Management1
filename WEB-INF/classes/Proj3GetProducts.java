
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import helpers.*;
 import java.util.logging.*;

public class Proj3GetProducts extends HttpServlet 
{
	String query;
	String answer;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException  
        {
        	PrintWriter out = response.getWriter();
        

		response.setContentType("text/html"); 
        String vendor = request.getParameter("vendor");
        String category = request.getParameter("category");
        String tab = request.getParameter("tab");
        String sort = request.getParameter("sort");
        String inProducts =  request.getParameter("inProducts");
        String search =  request.getParameter("search");

    	String searchQString =" (vendor.name LIKE '%"+search+"%' OR category.name LIKE '%"+search+"%' OR product.vendorModel LIKE '%"+search+"%')";
    	String filterString = " ORDER BY product.retail "+sort;
    	String catQString = " catID in"+category;
    	String venQString= " venID in"+vendor;
    	String rawQuery = "select vendor.name, category.name, product.vendorModel, product.retail, product.image, on_hand.on_hand_quantity, product.sku FROM product left join on_hand on product.sku=on_hand.sku inner join vendor ON vendor.vendorID=product.venID inner join  category ON category.categoryID = product.catID";
    	boolean isWhereUsed = false;
    	
    	if(inProducts != null){
    	query="select vendor.name, category.name, product.vendorModel, product.retail, product.image, product.sku, on_hand.on_hand_quantity FROM product left join on_hand on product.sku=on_hand.sku inner join vendor ON vendor.vendorID=product.venID inner join  category ON category.categoryID = product.catID WHERE product.sku in "+inProducts;
         answer = Proj3_DBHelper.query(query);
         answer+="|"+inProducts;
    	}else {
    	if(category != null) {
    	 rawQuery+=" WHERE"+catQString;
    	isWhereUsed = true;
    	} 
    	
    	if(vendor != null) {
    	if(isWhereUsed) {
    	rawQuery+=" AND"+venQString;
    	} else {
    	rawQuery+=" WHERE"+venQString;
    	isWhereUsed = true;
    	}
    	}
    	
    	if(search != null) {
    	if(isWhereUsed) {
    	rawQuery+=" AND"+searchQString;
    	} else {
    	rawQuery+=" WHERE"+searchQString;
    	isWhereUsed = true;
    	}
    	
    	}
    	
    	if(sort != null) {
    	rawQuery+=filterString;
    	}
    	

    	answer = Proj3_DBHelper.query(rawQuery);
    	
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



