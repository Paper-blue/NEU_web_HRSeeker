package com.HRseeker.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HRseeker.service.HRseeker_USERDao;
import java.io.PrintWriter;

/**
 * Servlet implementation class UsernameCheck
 */
@WebServlet("/usernamecheck")
public class UsernameCheck extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("name");
		
		int count = HRseeker_USERDao.selectByName(name);
	
		PrintWriter out = response.getWriter();
		if(count > 0) {
		out.print("false");
	}else {
		out.print("true");
	}
	
	out.close();
	
	}

	
}
