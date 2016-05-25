import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import sdsu.*;



public class Login extends HttpServlet { 

    public void doPost(HttpServletRequest request,
      HttpServletResponse response)
    throws IOException, ServletException  {
        processRequest(request, response);         
    }

    public void doGet(HttpServletRequest request,
      HttpServletResponse response)
    throws IOException, ServletException  { 
        processRequest(request, response);
    } 

    private void processRequest(HttpServletRequest request,
      HttpServletResponse response) 
    throws IOException, ServletException {
        String toDo;
        if(!request.getMethod().equals("POST")) {
            response.sendRedirect("/jadrn013/Error/login_err.jsp"); 
            return;
        }     
        String username = (String) request.getParameter("user");
        String password = (String) request.getParameter("passwd");
        if(username == null || password == null) {
            response.sendRedirect("/jadrn013/Error/login_err.jsp"); 
            return;
        }        
        if(PasswordUtilities.isValidLoginTable(username,password)) {
            toDo = "/jadrn013/servlet/DispatchServlet?action=main";
            HttpSession session = request.getSession(true);
            session.setAttribute("username", username); 
        }
        else
            toDo = "/jadrn013/Error/login_err.jsp";
        response.sendRedirect(toDo);    
    }

}
