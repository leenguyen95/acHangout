package com.amzi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;

public class Like extends HttpServlet{

	private static final long serialVersionUID = -1203125235252940220L;

	@SuppressWarnings("resource")
	public static boolean validate(String values[], int choice) {        
		boolean status = false;

		Connection conn = null;
		ResultSet rs = null;

		PreparedStatement pst = null;

		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "form";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "xxxx";

		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url + dbName, userName, password);

			pst = conn.prepareStatement("SELECT * FROM users WHERE username=? and password=?");
			pst.setString(1, values[0]);
			pst.setString(2, values[1]);
			//result set
			rs = pst.executeQuery();

			if(rs.next()) {
				String id = rs.getString("id");
				pst = conn.prepareStatement("SELECT * FROM post WHERE id=?");
				pst.setString(1, values[2]);

				rs = pst.executeQuery();

				if(rs.next()) {
					String postid = rs.getString("id");

					if(choice == 1) {
						pst = conn.prepareStatement("Insert into rating(id,liked,disliked,postid)values(1,1)ON DUPLICATE KEY UPDATE liked=liked+1;");


					}
					else {
						pst = conn.prepareStatement("Insert into rating(id,liked,disliked,postid)values(1,1)ON DUPLICATE KEY UPDATE disliked = disliked+1;");

					}

					pst.setString(1, values[2]);
					pst.executeUpdate();

					status = true;	
				}
			}
			pst.setString(1, values[2]);

		}catch(Exception e) {
			System.out.println(e);
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return status;
	}
}