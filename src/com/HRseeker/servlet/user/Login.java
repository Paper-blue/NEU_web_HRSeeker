package com.HRseeker.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.HRseeker.entity.HRseeker_user;
import com.HRseeker.service.HRseeker_USERDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html charset=utf-8");
		response.setContentType("text/html;charset=gb2312");
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		int count = HRseeker_USERDao.selectByNM(userName, passWord);
		
		if(count > 0) {
			HttpSession session = request.getSession();
			HRseeker_user user = HRseeker_USERDao.selectAdmin(userName, passWord);//
			
			
			
			session.setAttribute("name", user);
            session.setAttribute("isLogin", "1");			
			response.sendRedirect("index.jsp");
			
		}else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('ÓÃ»§µÇÂ¼Ê§°Ü');");
			out.write("location.href='login.jsp'");
			out.write("</script>");
		}
	}

}
