package com.HRseeker.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

public class Basedao {
	static {
		//加载驱动
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getconn() {
		//建立连接对象
		Connection conn=null;
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hrseeker_wzn?useSSL=false&serverTimezone=UTC","root","123456");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	public static int exetcuIUD(String sql,Object[] params) {//对sql语句处理
		int count =0;
		Connection connection=Basedao.getconn();
		//准备sql语句
		PreparedStatement ps=null;
		//
		try {
			ps=connection.prepareStatement(sql);//导入sql语句
			//绑定参数
			
			for(int i=0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			count=ps.executeUpdate();//这里count一开始忘记返回，导致页面跳转为空白页
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Basedao.closeall(null, ps, connection);
		}
		return count;//返回影响的行数
		
	}
	public static void closeall(ResultSet rs,PreparedStatement ps,Connection conn) {
		try {		
		if(rs!=null)
			rs.close();}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {if(ps!=null)
			ps.close();}
		catch (Exception e) {
			e.printStackTrace();
		}
		try {if(conn!=null)//连接对象不为空
			conn.close();}
		catch (Exception e) {
			e.printStackTrace();
		}

		
	}

}
