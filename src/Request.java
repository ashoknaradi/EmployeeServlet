import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Request extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			String data = request.getParameter("request");
			if (data.equals("y")) {
				RequestDispatcher rd = request.getRequestDispatcher("register.html");
				rd.include(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("search.html");
				rd.include(request, response);
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
		out.close();
	}
}
