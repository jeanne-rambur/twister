package services;

import java.sql.SQLException;

import org.json.JSONObject;

import bd.UserTools;

public class Logout {
	public static JSONObject logout(String key) throws SQLException {
		if(key==null) {
			return UserTools.serviceRefused("Mauvais argument", 1);
			
		}
		
		if(UserTools.keyVerif(key)) {
				
			bd.UserTools.removeSession(key);
				
			return UserTools.serviceAccepted();
				
				
		}else {
				return UserTools.serviceRefused("la clé n'existe pas", 1);
		}
		
	}
}
