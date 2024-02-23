package common;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConnectDB {
	private static Connection con = null;
	private static String url = "jdbc:sqlserver://";
	private static String serverName="DESKTOP-07M7T3J";
//	private static String serverName="MSI";
	private static String port = "1433";
	private static String dbName = "apartment";
	private static String user = "sa";
	private static String pw="1234567";
//	private static String pw = "1564";
//	private static String pw = "11";
	
	private static String getConnectURL() {
		return url + serverName + ":" + port + ";databaseName=" + dbName + ";user=" + user + ";password=" + pw;
	}
	
	public static Connection getConnect() {
		try {
			con = DriverManager.getConnection(getConnectURL());
			System.out.println("Success");
		} catch(Exception e) {
			con = null;
			e.printStackTrace();
			System.out.println("failed");
		}
		return con;
	}
}
