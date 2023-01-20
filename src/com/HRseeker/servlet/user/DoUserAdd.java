package com.HRseeker.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HRseeker.entity.HRseeker_user;
import com.HRseeker.service.HRseeker_USERDao;

/**
 * Servlet implementation class DoUserAdd
 */
@WebServlet("/manage/admin_douseradd")
public class DoUserAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoUserAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//接受参数
		String username=request.getParameter("userName");
		String name=request.getParameter("Name");
		String password=request.getParameter("Password");
		String repassword=request.getParameter("RePassword");
		String sex=request.getParameter("sex");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		HRseeker_user u=new HRseeker_user(username, name, password, sex, null, email, mobile, address, 1);//1代表普通用户
		//加入到数据库中去
		int count = HRseeker_USERDao.insert(u);
		System.out.print(u);
		//成功或者失败重定向
		if(count>0) {
			response.sendRedirect("admin_douserselect");
		}else {
			PrintWriter out= response.getWriter();//用输出流的方式
			out.write("<script>");
			out.write("Alter('用户添加失败！')");
			out.write("location.href='manage/admin_useradd.jsp'");
			out.write("</script>");
			
		}
		
	}

}
