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
		//�����ַ���
				request.setCharacterEncoding("UTF-8");
				response.setContentType("text/html;charset=utf-8");
				//���ܲ���
				String id=request.getParameter("id");
				
				//���뵽���ݿ���ȥ
				int count = HRseeker_USERDao.del(id);
				//�ɹ�����ʧ���ض���
				if(count>0) {
					response.sendRedirect("admin_douserselect?cp="+request.getParameter("cpage"));
				}else {
					PrintWriter out= response.getWriter();//��������ķ�ʽ
					out.write("<script>");
					out.write("Alter('�û����ʧ�ܣ�')");
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
		//���ܲ���
		String ids[]=request.getParameterValues("id[]");
		
		//���뵽���ݿ���ȥ
		//�ɹ�����ʧ���ض���
			for(int i=0; i<ids.length; i++) {		
				HRseeker_USERDao.del(ids[i]);
			}
					response.sendRedirect("/HRseeker_wzn/manage/admin_douserselect");
					}
		
	}


