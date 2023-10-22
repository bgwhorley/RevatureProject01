package dev.whorley.serf;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

import dev.whorley.daos.EmployeeServiceImpl;
import dev.whorley.models.Employee;

/**
 * Servlet implementation class MasterServlet
 */
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("username"));
		String URI = request.getRequestURI();
		System.out.println(URI);
		System.out.println(request.getPathInfo());
		HttpSession sess = request.getSession();
		switch(URI) {
			case "/Project1/Login.do":
				/*if(checkCredentials(request)) {
					Gson gs = new Gson();
					Employee e = employee_service.getEmployee(request.getParameter("username"));
					String json = gs.toJson(employee_service.getEmployee(request.getParameter("username")));
					response.getWriter().append(json);
				}*/
				break;
			default:
				break;
		}
		System.out.println(request.getParameter("username"));
		//RequestHelper.process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
}
