package services.tests;

import java.sql.SQLException;

import services.AddFriend;
import services.JSONException;

public class TestAddFriend {
	public static void main(String[]args) throws JSONException, SQLException{
		System.out.println(AddFriend.addFriend("", 245));
	}
}
