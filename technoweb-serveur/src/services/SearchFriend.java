package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bd.UserTools;

public class SearchFriend {
	public static JSONObject searchFriend(String login) throws SQLException {
		
		JSONObject j = new JSONObject(); 
		
		if(login==null){
			return UserTools.serviceRefused("Mauvais arguments", -2);
			
		}else if(!UserTools.userExist(login)) {
			System.out.println("login"+login);
			return UserTools.serviceRefused("User inexistant", -1);

			
		}else{
			
			int id_user_friend = UserTools.getIdUser(login);
			j = UserTools.serviceAccepted();
			
			try {
				j.put("id",id_user_friend);
				j.put("login",login);
				return j;
			} catch (JSONException e) {
				e.printStackTrace();
				return null;
			}
			
			
		}
		
			
	}
}
