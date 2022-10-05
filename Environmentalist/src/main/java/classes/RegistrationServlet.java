package classes;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xml.sax.SAXException;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EnvironmentalistCollection collection = null;
	
	
	public void init(ServletConfig config) throws ServletException {
			collection = EnvironmentalistCollection.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession oldSession = request.getSession(false);
		Environmentalist myEnviron;
		
		if(oldSession != null) {
			myEnviron = (Environmentalist) oldSession.getAttribute("sessionUser");
		}else {
			myEnviron = null;
		}
		
		if(myEnviron == null) {
			response.sendRedirect(request.getContextPath());
		}else if(myEnviron.getUsername().equals(request.getParameter("username"))){
			
			//oldSession.setAttribute("sessionUser", myEnviron);			
			//response.sendRedirect("singleEnvironData.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/singleEnvironData.jsp");
			rd.forward(request, response);
		}
		/*RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.include(request,response);*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		
		if(name== null || name.isEmpty() || username==null||username.isEmpty()||password==null||
				password.isEmpty()||phoneNumber==null||
				phoneNumber.isEmpty()) {
			out.println("<p style = 'color:red'>Моля въведете всички полета!</p>");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
		}else {
			Environmentalist e = new Environmentalist(name, username, password, phoneNumber);
			if(collection.addEnvironmentalist(e)) {
				
				collection.update();
				Environmentalist regEnvironm = collection.getEnvironmentalist(e.getUsername());
				//out.print("<p style='color:green'> Браво, "+ regEnvironm.getUsername()+" вече се регистрирахте!</p>");
				
				HttpSession oldSession = request.getSession(false);
				
				if(oldSession != null) {
					oldSession.invalidate();
				}
				
				HttpSession newSession = request.getSession(true);
				newSession.setAttribute("sessionUser", regEnvironm);
				
				response.sendRedirect("singleEnvironData.jsp");
				
			}else {
				Environmentalist regEnvironm = collection.getEnvironmentalist(e.getUsername());
				out.println("<p style= 'color:red'>Добре дошли!</p>");
				
				HttpSession oldSession = request.getSession(false);
				
				if(oldSession != null) {
					oldSession.invalidate();
				}
				
				HttpSession newSession = request.getSession(true);
				newSession.setAttribute("sessionUser", regEnvironm);
				
				RequestDispatcher rd = request.getRequestDispatcher("/singleEnvironData.jsp");
				rd.include(request, response);
			}
		}
		
	}

}
