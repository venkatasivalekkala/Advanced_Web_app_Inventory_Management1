import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sdsu.*;



public class DispatchServlet extends HttpServlet {	
    public void doPost(HttpServletRequest request,HttpServletResponse response)
       throws IOException, ServletException {                    
        String toDo;
	String command = request.getParameter("action");	      
        HttpSession session = request.getSession(false);
	if(command.equals("login")) {
                toDo = "/servlet/Login"; 
		}
	else {
        	if(session == null || session.getAttribute("username") == null) {
            	response.sendRedirect("/jadrn013/WEB-INF/jsp/login_err.jsp");
            	return;
            	}
	}                              

        if(command == null)
                toDo = "/WEB-INF/jsp/login_err.jsp";
	else if(command.equals("main"))
		toDo = "/WEB-INF/jsp/warehouse.jsp";
	else if(command.equals("dbTest"))
		toDo = "/servlet/DBTest";
	else if(command.equals("addin"))
		toDo = "/servlet/AddInventory";
	else if(command.equals("removeout"))
		toDo = "/servlet/RemoveInventory";
	else if(command.equals("add"))
		toDo = "/WEB-INF/jsp/inventoryin.jsp";
	else if(command.equals("remove"))
		toDo = "/WEB-INF/jsp/inventoryout.jsp";
        else if(command.equals("login"))
                toDo = "/servlet/Login"; 
        else if(command.equals("logout"))
                toDo = "/servlet/Logout";		               
        else if(command.equals("dbbeantest"))
                toDo = "/servlet/DbBeanTest";		
	else
		toDo = "/WEB-INF/jsp/login_err.jsp";
				
	processRequest(request, response, toDo);
	}
        
    public void doGet(HttpServletRequest request,HttpServletResponse response)
                      throws IOException, ServletException { 
            doPost(request, response);
            }       
	
    public void processRequest(HttpServletRequest request,
                    HttpServletResponse response, String action)
                    throws IOException, ServletException {

        RequestDispatcher dispatcher = 
            request.getServletContext().getRequestDispatcher(action); 	
	dispatcher.forward(request, response);	
        }
        
    
}

