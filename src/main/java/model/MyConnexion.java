package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
public class MyConnexion {
	Connection connection = null;
	PreparedStatement pst;
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public PreparedStatement getPst() {
		return pst;
	}
	public void setPst(PreparedStatement pst) {
		this.pst = pst;
	}
	public Connection connect() {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 //connection = DriverManager.getConnection( "jdbc:mysql://localhost/pharma1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root", "123456");
			connection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/bd_sable","root", "123456");
		 } catch (Exception exception) {
			 System.out.println("-------------------------------------");
	         System.out.println(exception);
	         System.out.println("-------------------------------------");
		 }
		 System.out.println("conoeinfoere");
		 
		 System.out.println(connection);
		 return connection;

}

}
