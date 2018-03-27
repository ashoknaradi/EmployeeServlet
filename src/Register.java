
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Map<String, String> al;
		try {
			Integer counter = (Integer) session.getAttribute("counter");
			if (counter == null) {
				counter = new Integer(1);
			} else {
				counter = new Integer(counter.intValue() + 1);
			}
			session.setAttribute("counter", counter);
			Map<Integer, Map<String, String>> empr = (Map<Integer, Map<String, String>>) session.getAttribute("empr");
			if (empr == null) {
				empr = new TreeMap<Integer, Map<String, String>>();
			} else {
				empr = empr;
			}
			session.setAttribute("empr", empr);
			al = new HashMap<String, String>();
			al.put("name", request.getParameter("empName"));
			al.put("age", request.getParameter("empAge"));
			al.put("salary", request.getParameter("empSalary"));
			al.put("address", request.getParameter("empCountry"));
			al.put("phone", request.getParameter("empPhone"));
			empr.put(counter, al);
			if (session.isNew()) {
				out.println("new session");
				out.println("counter: " + counter);
				out.println(empr);
			} else {
				out.println("counter: " + counter);
				// out.println(empr);
			}
			session.setAttribute("empr", empr);
			RequestDispatcher rd = request.getRequestDispatcher("request.html");
			rd.include(request, response);
		} catch (Exception e2) {
			System.out.println(e2);
		}
		out.close();
	}
}