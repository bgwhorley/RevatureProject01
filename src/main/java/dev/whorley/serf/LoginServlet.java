package dev.whorley.serf;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.whorley.daos.EmployeeServiceImpl;
import dev.whorley.models.Employee;

public class LoginServlet extends HttpServlet {
	private static EmployeeServiceImpl employee_service = new EmployeeServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter outputWriter = response.getWriter();
		String responseData = "";
		
		Employee matchedUser = checkCredentials(request);
		System.out.println(matchedUser);
		
		if (matchedUser != null) {
			response.setStatus(200);
			responseData = "{\"current_user\":\""+ username +"\","
					+ "\"is_manager\": ";
			if (matchedUser.getManager() == 1)
				responseData += "1";
			else
				responseData += "0";
			responseData += "}";
			
			System.out.println(responseData);
		} else {
			response.setStatus(401);
			responseData = "{\"errorMessage\":\"Sorry, username or password error!\"}";
		}
		
		outputWriter.print(responseData);
	}

//	protected void _doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
////        response.setContentType("application/json");  
//		PrintWriter out = response.getWriter();
//
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//
//		// if(password.equals("admin123")){
//		// append employee (user) json to the response
//		if (checkCredentials(request)) {
//			Gson gs = new Gson();
//			Employee e = employee_service.getEmployee(request.getParameter("username"));
//			String json = gs.toJson(employee_service.getEmployee(request.getParameter("username")));
//			System.out.println("appending json in post(): " + json);
//			out.append(json);
//			// setResponseDataKeyForUserNameWithGetEmployeeDotGetUserName();
//			/* out.print("Welcome, "+username); */
//			HttpSession session = request.getSession();
//			session.setAttribute("username", username);
//		} else {
//			response.setContentType("application/json");
//			String responseData = "{\"errorMessage\":\"Sorry, username or password error!\"}";
//			out.print(responseData);
//
//		}
//		// close the printwriter
//		out.close();
//	}

	public Employee checkCredentials(HttpServletRequest request) {
		System.out.println("checking credentials");
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		Employee matchedUser = employee_service.logIn(request.getParameter("username"), request.getParameter("password")); 
		return matchedUser;
	}

}