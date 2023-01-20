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
 * Servlet implementation class DoUserDel
 */
@WebServlet("/manage/admin_douserdel")
public class DoUserDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=utf-8");
				//接受参数
				String id=request.getParameter("id");
				
				//加入到数据库中去
				int count = HRseeker_USERDao.del(id);
				//成功或者失败重定向
				if(count>0) {
					response.sendRedirect("admin_douserselect?cp="+request.getParameter("cpage"));
				}else {
					PrintWriter out= response.getWriter();//用输出流的方式
					out.write("<script>");
					out.write("Alter('用户添加失败！')");
					out.write("location.href='manage/admin_douserselect?cp="+request.getParameter("cpage")+"'");
					out.write("</script>");
					
				}
			}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//接受参数
		String ids[]=request.getParameterValues("id[]");
		
		//加入到数据库中去
		//成功或者失败重定向
			for(int i=0; i<ids.length; i++) {		
				HRseeker_USERDao.del(ids[i]);
			}
					response.sendRedirect("/HRseeker_wzn/manage/admin_douserselect");
					}
		
	}


