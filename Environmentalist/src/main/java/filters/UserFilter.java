package filters;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Environmentalist;

@WebFilter("/index.jsp")

public class UserFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		
		HttpSession oldSession = req.getSession(false);
		Environmentalist myEnviron;
		
		if(oldSession != null) {
			myEnviron = (Environmentalist) oldSession.getAttribute("sessionUser");
		}else {
			myEnviron = null;
		}
		
		if(myEnviron == null) {
			
			//res.sendRedirect(req.getContextPath());	
			chain.doFilter(request, response);
			
		}else { //if(myEnviron.getUsername().equals(req.getParameter("username"))){
			out.println("<p style= 'color:red'>Вече сте се вписали!</p>");
			
			//oldSession.setAttribute("sessionUser", myEnviron);			
			//res.sendRedirect("singleEnvironData.jsp");
			
			RequestDispatcher rd = req.getRequestDispatcher("/singleEnvironData.jsp");
			rd.include(req, res);
		}
		
	}


}
