package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bd.UserTools;

public class Login {
	public static JSONObject login(String login, String password) throws SQLException {
		
		JSONObject j = new JSONObject(); 
		
		if((login==null)||(password==null)) {
			return UserTools.serviceRefused("Mauvais arguments", -2);
			
		}else if(!UserTools.userExist(login)) {
			System.out.println("login"+login+"password"+password);
			return UserTools.serviceRefused("User inexistant", -1);
			
		}else if(!UserTools.checkpasswd(login, password)){
			return UserTools.serviceRefused("Mot de passe incorrect", -1);
			
		}else{
			
			int id_user = UserTools.getIdUser(login);
			String key = UserTools.insertSession(id_user, false);
			j = UserTools.serviceAccepted();
			
			try {
				j.put("key",key);
				j.put("id",id_user);
				j.put("login",login);
				return j;
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
			
			
		}
		
			
	}
}
				
				
					

	
		
		


		