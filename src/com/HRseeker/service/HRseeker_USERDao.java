package com.HRseeker.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.HRseeker.dao.Basedao;
import com.HRseeker.entity.HRseeker_user;


public class HRseeker_USERDao {//�û��༯�д���
	//����
	public static int insert(HRseeker_user u) {
		String sql="insert into hrseeker values(?,?,?,?,?,?,?,?,?)";//����һ������
		//�û�����תΪobject����
		Object[] params= {u.getUSER_ID(),
				u.getUSER_NAME(),
				u.getUSER_PASSWORD(),
				u.getUSER_SEX(),
				u.getUSER_IDENITY_CODE(),
				u.getUSER_EMAIL(),
				u.getUSER_MOBILE(),
				u.getUSER_ADDRESS(),
				u.getUSER_STATUS()};
		return Basedao.exetcuIUD(sql, params);//����Ӱ�������
		
		
	}
	//����
	public static int update(HRseeker_user u) {
		String sql="update  hrseeker set USER_NAME=?,USER_PASSWORD=?,USER_SEX=?,USER_IDENITY_CODE=?,USER_EMAIL=?,USER_MOBILE=?,USER_ADDRESS=?,USER_STATUS=? where USER_ID = ?";//����һ������
		//�û�����תΪobject����
		Object[] params= {
				//u.getUSER_ID()
				u.getUSER_NAME(),
				u.getUSER_PASSWORD(),
				u.getUSER_SEX(),
				u.getUSER_IDENITY_CODE(),
				u.getUSER_EMAIL(),
				u.getUSER_MOBILE(),
				u.getUSER_ADDRESS(),
				u.getUSER_STATUS(),
				u.getUSER_ID()};
		return Basedao.exetcuIUD(sql, params);//����Ӱ�������
		
		
	}
	//ɾ��
	public static int del(String id) {
		String sql="delete from hrseeker where USER_ID=? and USER_STATUS!=2";
		Object[] params= {id};
		return Basedao.exetcuIUD(sql, params);
		
	}
	
	//��ѯ
	public static ArrayList<HRseeker_user> selectAll(int cpage,int count,String keywords){
		ArrayList<HRseeker_user> list=new ArrayList<HRseeker_user>();
		//���������
		ResultSet rs=null;
		//��ȡ���Ӷ���
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;//Ԥ����
		
		try {
			String sql="";
			if(keywords!=null) {
			sql="select * from HRseeker where user_name like ? order by USER_ID desc limit ?, ?";//����'%"+keywords+"%'��ֹsqlע��
			ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+keywords+"%");//Ԥ��������ֱ��ִ�У�������sqlע��
			ps.setInt(2, (cpage-1)*count);
			ps.setInt(3, count);
			}else {
			sql="select * from HRseeker order by USER_ID desc limit ?, ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, (cpage-1)*count);
			ps.setInt(2, count);
			}
			
			
			rs=ps.executeQuery();
			while (rs.next()) {//ѭ������
				HRseeker_user u=new HRseeker_user(
						rs.getString("USER_ID"),
						rs.getString("USER_NAME"), 
						rs.getString("USER_PASSWORD"), 
						rs.getString("USER_SEX"),
						rs.getString("USER_IDENITY_CODE"), 
						rs.getString("USER_EMAIL"), 
						rs.getString("USER_MOBILE"), 
						rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS")
						);//һ��ʼʹ���˵����ţ�����
				list.add(u);
				
				
			}
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);//�ǵùر����ӵ���Դ
		}
		
		return list;
	}
	
	
	//ͨ��id�Ҳ���
	public static HRseeker_user selectByID(String id) {
   	 HRseeker_user u = null;
   	 
   	  ResultSet rs = null;//���������
   	  //��ȡ���Ӷ���
   	  Connection conn = Basedao.getconn();
   	  
   	   PreparedStatement ps = null;
   	   
   	   try {
       String sql = "select * from hrseeker where USER_ID=?";
       ps = conn.prepareStatement(sql);
       ps.setString(1, id);
       rs=ps.executeQuery();
			
			
			while(rs.next()) {
				 u = new HRseeker_user(
						 	rs.getString("USER_ID"),
							rs.getString("USER_NAME"), 
							rs.getString("USER_PASSWORD"), 
							rs.getString("USER_SEX"),
							rs.getString("USER_IDENITY_CODE"), 
							rs.getString("USER_EMAIL"), 
							rs.getString("USER_MOBILE"), 
							rs.getString("USER_ADDRESS"),
							rs.getInt("USER_STATUS")
						);
				
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
   	  return u;
     }
	
	
	public static int[] totalPage(int count,String keywords) {//��ȡ��ҳ��
		int arr[] = {0,1};
		Connection conn=Basedao.getconn();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			String sql="";
			if(keywords!=null) {
				sql="select count(*) from HRseeker where user_name like ?";
				ps=conn.prepareStatement(sql);
				ps.setString(1, "%"+keywords+"%");//Ԥ��������ֱ��ִ�У�������sqlע��
			}
			else {
				sql="select count(*) from HRseeker";
				ps=conn.prepareStatement(sql);

			}
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				arr[0] =rs.getInt(1);
				if(arr[0]%count==0) {
					arr[1]=arr[0]/count;
				}
				arr[1]=arr[0]/count+1;//�����ķ�һҳ
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
		return arr;
	}
	//ͨ��id����
	public static int selectByName(String id) {
        int count = 0;
  	  Connection conn = Basedao.getconn();
  	  PreparedStatement ps = null;
  	  ResultSet rs = null;
  	  
  	  try {
  		String sql = "select count(*) from hrseeker where USER_ID=?";
  		ps = conn.prepareStatement(sql);
  		ps.setString(1, id);
  	
  		rs = ps.executeQuery();
         while(rs.next()) {
      	   count = rs.getInt(1);
         }
			
			
		
  	  }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
  	  return count;
    }
	//ͨ���û�����ѯ
	public static int selectByNM(String name, String pwd) {
  	  int count = 0;
  	  
  	  Connection conn = Basedao.getconn();
  	  PreparedStatement ps = null;
  	  ResultSet rs = null;
  	  
  	  
  	  
  	  try {
  		String sql = "select count(*) from hrseeker where USER_ID=? and USER_PASSWORD=?";
  		ps = conn.prepareStatement(sql);
  		
  		ps.setString(1, name);
  		ps.setString(2, pwd);
  	
  		rs = ps.executeQuery();
  		
         while(rs.next()) {
      	   count = rs.getInt(1);
         }
			
			
		
  	  }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
  	  return count;
    }
	//��ѯ����
	public static HRseeker_user selectAdmin(String name, String pwd) {
		
		 HRseeker_user u = null;
   	 
  	  ResultSet rs = null;//���������
  	  //��ȡ���Ӷ���
  	  Connection conn = Basedao.getconn();
  	  
  	   PreparedStatement ps = null;
  	   
  	   try {
      String sql = "select * from hrseeker where USER_ID=? and USER_PASSWORD=?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, name);
      ps.setString(2, pwd);
      
      
		rs=ps.executeQuery();
			
			
			while(rs.next()) {
				u = new HRseeker_user(
					 	rs.getString("USER_ID"),
						rs.getString("USER_NAME"), 
						rs.getString("USER_PASSWORD"), 
						rs.getString("USER_SEX"),
						rs.getString("USER_IDENITY_CODE"), 
						rs.getString("USER_EMAIL"), 
						rs.getString("USER_MOBILE"), 
						rs.getString("USER_ADDRESS"),
						rs.getInt("USER_STATUS")
					);
				
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Basedao.closeall(rs, ps, conn);
		}
  	  return u;

	}
    

}
