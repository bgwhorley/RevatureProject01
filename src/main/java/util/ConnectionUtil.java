package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() {
	Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			Properties props = new Properties();
			FileInputStream in = new FileInputStream("C:\\Users\\Ben\\Documents\\workspace-spring-tool-suite-4-4.2.0.RELEASE\\Project1\\src\\main\\resources\\connection.properties");
			//FileInputStream in = new FileInputStream("src/main/resources/connection.properties");
			props.load(in);		
			
			String endpoint =  props.getProperty("endpoint");
			System.out.println(endpoint);
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			conn = DriverManager.getConnection(endpoint, username, password);
			
			
			in.close();
			//System.out.println(conn);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			//conn.close();
		}
		return conn;
	}
}
