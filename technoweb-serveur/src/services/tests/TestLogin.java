package services.tests;

import java.sql.SQLException;

import services.Login;

public class TestLogin {
	public static void main(String[]args) throws SQLException{
		System.out.println(Login.login("hello", "hellooo"));
	}

}
