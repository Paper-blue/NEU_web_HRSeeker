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
 * Servlet implementation class DoUserUpdate
 */
@WebServlet("/manage/admin_douserupdate")
public class DoUserUpdate extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//接受参数
		String username=request.getParameter("userName");
		String name=request.getParameter("Name");
		String password=request.getParameter("Password");
		//String repassword=request.getParameter("RePassword");
		String sex=request.getParameter("sex");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		String userStatus=request.getParameter("userStatus");
		int status=1;//普通用户
		if(userStatus != null) {
			status = Integer.parseInt(userStatus);
		}
		HRseeker_user u=new HRseeker_user(username, name, password, sex, null, email, mobile, address, status);//1代表普通用户
		//加入到数据库中去
		int count = HRseeker_USERDao.update(u);
		System.out.print(u);
		//成功或者失败重定向
		if(count>0) {
			response.sendRedirect("admin_douserselect?cp="+request.getParameter("cpage"));
		}else {
			PrintWriter out= response.getWriter();//用输出流的方式
			out.write("<script>");
			out.write("Alter('用户修改失败！')");
			out.write("location.href='manage/admin_touserupdate?id="+username+"'");
			out.write("</script>");
			
		}
	}

}
