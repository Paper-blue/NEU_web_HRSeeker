package com.HRseeker.servlet.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.HRseeker.service.HRseeker_USERDao;
import com.HRseeker.entity.HRseeker_user;

/**
 * Servlet implementation class DoUserSelect
 */
@WebServlet("/manage/admin_douserselect")
public class DoUserSelect extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int cpage = 1;//��ǰҳ
		int count = 5;//ÿҳ��ʾ����
		
		//��ȡ�û�ָ����ҳ��
		String cp = request.getParameter("cp");
		
		
		//�����û������Ĺؼ���
		String keyword = request.getParameter("keywords");
		
		if(cp!=null) {
			cpage = Integer.parseInt(cp);
		}
		int arr[] = HRseeker_USERDao.totalPage(count, keyword);
	
		
	
		//��ȡ�����û���¼
	ArrayList<HRseeker_user> list=HRseeker_USERDao.selectAll(cpage,count,keyword);//cpage, count
	
	//�ŵ��������������
	request.setAttribute("userlist", list);
	request.setAttribute("tsum", arr[0]);//����
	request.setAttribute("tpage", arr[1]);//��ҳ��
	request.setAttribute("cpage", cpage);
	
	if(keyword!=null) {
		request.setAttribute("searchParams", "&keywords="+keyword);
	}
	request.getRequestDispatcher("admin_user.jsp").forward(request, response);//����ҳ��

	}
}