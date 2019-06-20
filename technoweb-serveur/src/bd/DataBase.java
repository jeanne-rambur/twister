package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class DataBase {

	private DataSource datasource;
	private static DataBase database = null;
	
	public DataBase(String jdbcname) throws SQLException{
		try {
			datasource=(DataSource) new InitialContext().lookup("java:copm/emv/"+jdbcname);
			
		}catch(NamingException e) {
			throw new SQLException(jdbcname+" is missing in jdbc !!! : "+e.getMessage());
		}
		
		
	}
	
	public Connection getConnection() throws SQLException{
		return datasource.getConnection();
	}
	
public static Connection getMySQLConnection()throws SQLException{
	Connection conn= null;
		if(DBStatic.mysql_pooling == false) {
			try {
				
				Class.forName("com.mysql.jdbc.Driver");
				
				String url="jdbc:mysql://localhost:3306/myapp";
				
				conn = DriverManager.getConnection(url,"root","pwdpwd");
				
				System.out.println("work");
				
				return conn;
				
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			
		}
		else {
			if(database == null) {
				database = new DataBase("jdbc/db");
			}
			return (database.getConnection());
					
		}
		
	}


   
}
	

