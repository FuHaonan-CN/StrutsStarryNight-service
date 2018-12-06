package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 */
public class DataBaseConnection {

	private Connection con = null;
	private static Properties prop = new Properties();

	public DataBaseConnection() throws IOException {
		InputStream in = DataBaseConnection.class.getResourceAsStream("/db.properties");
		prop.load(in);
		in.close();
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException{

		Class.forName(prop.getProperty("driver"));

		con = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("username"), prop.getProperty("password"));

		return con;
	}
	//----------------------------------------

	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
