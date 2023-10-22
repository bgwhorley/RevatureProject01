package dev.whorley.serf;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dev.whorley.daos.EmployeeServiceImpl;
import dev.whorley.daos.ReimbursementServiceImpl;
import dev.whorley.models.Employee;
import dev.whorley.models.Reimbursement;

public class EmployeeServlet extends HttpServlet {
	private static ReimbursementServiceImpl reim_service = new ReimbursementServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		
		String username = request.getParameter("username");
		String action = request.getParameter("action");
		
		PrintWriter outputWriter = response.getWriter();
		String responseData = "";
		
		switch(action) {
			case "create":
				int amount = Integer.parseInt(request.getParameter("amount"));
				String description = request.getParameter("description");
				reim_service.createReimbursement(username, amount, description);
				response.setStatus(200);
				responseData = "{\"ret\":\"1\"}";
				outputWriter.append(responseData);
				
				break;
			case "load":
				List<Reimbursement> rlist = new ArrayList<Reimbursement>();
				rlist = reim_service.getReimbursements(username);
				
				if (rlist != null) {
					response.setStatus(200);
					Gson gs = new Gson();
					String json = gs.toJson(rlist);
					System.out.println("appending json in post(): " + json);
					outputWriter.append(json);
					System.out.println(responseData);
				} else {
					//response.setStatus(401);
					//responseData = "{\"errorMessage\":\"Sorry, no reimbursements available for this user!\"}";
				}
			
				outputWriter.print(responseData);
				break;
			default:
				break;
		}
		outputWriter.close();
	}

}