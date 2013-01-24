package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Manage database.
 * @author Victor(DIAO,PIGANG)
 */
public class DBConnection {
	private static Connection conn;
	private final static String url = "jdbc:mysql://localhost:3306/test?unicode=true&characterEncoding=UTF-8";
	private final static String user = "root";
	private final static String password = "root";
	private final static String DRIVER="com.mysql.jdbc.Driver";  

	public static Connection getConnection(){
		try {
			try {
				Class.forName(DRIVER).newInstance();
				conn = DriverManager.getConnection(url, user, password);	
			}  catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}	
		} catch (SQLException e) {
			System.out.println("ERROR: database connection wronge.");
		}
		return conn;
	}

	public void closeConnection() {
		if (!(conn == null)) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("ERROR: database close wronge.");
			}
		}
	}
	
	/**
	 * Test database connection.
	 */
	/*public static void main(String[] args){
		try {
			System.out.println(DBConnection.getConnection().isClosed());
			List<?> demoList = new ArrayList<Gcib>();
		    GcibDAO dao = new GcibDAO();
		    demoList = dao.getGcibList(0, 0);
		    String result = Object_Json.list2Json(demoList);
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}