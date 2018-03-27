import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		boolean isNotPresent = false;
		boolean flag = false;
		try {
			int id = (Integer) session.getAttribute("counter");
			Map<Integer, Map<String, String>> empr = (Map<Integer, Map<String, String>>) session.getAttribute("empr");
			String data = request.getParameter("empPhone");
			for (id = id; id > 0; id--) {
				if (data.equals(empr.get(id).get("phone"))) {
					out.println("Yes employee specific data Found & your record is: \n" + empr.get(id));
					flag = true;
					break;
				} else {
					isNotPresent = true;
				}
			}
			if (isNotPresent) {
				if (!flag) {
					response.sendRedirect("error.html");
				}
			}
		} catch (Exception e2) {
			System.out.println(e2);
		}
		out.close();
	}
}