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

public class ManagerServlet extends HttpServlet {
	private static ReimbursementServiceImpl reim_service = new  ReimbursementServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		
		PrintWriter outputWriter = response.getWriter();
		String responseData = "";		
		int id = 0;
		Gson gs = new Gson();
		switch(action) {
			case "approve":	
				System.out.println("approving...");
				System.out.println(request.getParameter("id"));
				id = Integer.parseInt(request.getParameter("id"));
				System.out.println(id);
				boolean ret = reim_service.approveReimbursement(id);
				if(ret) {
					System.out.println("approved!");
				}
				//gs = new Gson();
				responseData = "{\"ret\":\"1\"}";
				//System.out.println(responseData);
				outputWriter.print(responseData);
				break;
			case "deny":
				System.out.println("denying...");
				id = Integer.parseInt(request.getParameter("id"));
				System.out.println("id:" + id);
				reim_service.denyReimbursement(id);
				break;
			case "load":
				List<Reimbursement> rlist = new ArrayList<Reimbursement>();
				rlist = reim_service.getAllReimbursments();
				
				if (rlist != null) {
					response.setStatus(200);
					gs = new Gson();
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
			default: break;
		}
		outputWriter.close();

	}

}