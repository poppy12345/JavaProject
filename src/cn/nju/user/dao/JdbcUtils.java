package cn.nju.user.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * v1.0
 * @author hrh
 */
public class JdbcUtils {
	private static Properties props=null;
	static{
		InputStream in=JdbcUtils.class.getClassLoader()
				.getResourceAsStream("dbconfig.properties");
		props=new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		try {
			Class.forName(props.getProperty("driverClassName"));
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(props.getProperty("url"), 
				props.getProperty("username"), props.getProperty("password"));
	}
}
