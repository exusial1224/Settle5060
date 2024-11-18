package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

public class RootDAO {
	static DataSource ds;

	public Connection getConnection() throws Exception{
		Class.forName("org.h2.Driver");
		return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/settle", "sa", "");
	}
}