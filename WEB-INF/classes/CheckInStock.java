import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sdsu.*;
public class CheckInStock extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response)
    throws IOException, ServletException
    {
        HttpSession session = request.getSession(false);
        if(session == null) {    
            ServletContext context = getServletContext();       
            RequestDispatcher dispatcher 
            = request.getRequestDispatcher("/WEB-INF/jsp/login_err.jsp");   
            dispatcher.forward(request, response);              
        }    
        String sku = (String) request.getParameter("sku");
        String sqlResult = DBHelper.singleAnswerQuery("select on_hand_quantity from on_hand where sku ='" + sku + "'");
        PrintWriter result = response.getWriter();
        if(sqlResult.isEmpty()) result.print("0");
        else{
            result.print(sqlResult);
        }
    }
    
    public void doPost(HttpServletRequest request,HttpServletResponse response)
    throws IOException, ServletException
    {
    	doGet(request, response);
    }  
}



