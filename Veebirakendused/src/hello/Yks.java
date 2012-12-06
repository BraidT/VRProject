package hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Yks
 */
public class Yks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Yks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, 
    		HttpServletResponse response) throws ServletException, IOException {
	    	String nimi = request.getParameter("nimi");
    		
    		HttpSession session = request.getSession();
        	Integer reloads= (Integer)session.getAttribute("reloads");
        	if(reloads == null) {
        	 reloads = 1;
        	}
        	else {
        	 reloads++;
        	}
        	session.setAttribute("reloads", reloads);
        	PrintWriter out = response.getWriter();
        	out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                                        "Transitional//EN\">\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>Hello WWW</TITLE></HEAD>\n" +
                "<BODY>\n" +
                "Tere " + nimi + "," +
                "\nSee on sinu " + reloads + " visiit!\n" +
                "<form method='POST' action='NaitaKella'>" +
        			"  <input name='uusnimi' />" +
        			"  <input type='submit' />" +
        			"</form>");
    			
    }
    		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uusnimi = request.getParameter("uusnimi");
		response.getWriter().println("Tere, " + uusnimi);
		
		// TODO Auto-generated method stub
	}

}
