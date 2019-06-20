package services;

import java.sql.SQLException;

import org.json.JSONObject;

import bd.MessageTools;
import bd.UserTools;

public class AddMessage {
	
	public static JSONObject addMessage(String key, String text) throws JSONException, SQLException{
		
		JSONObject js= new JSONObject();
		int id_author = UserTools.getIdUserByKey(key);
		
		//Verification paramètres
		if((key==null)||(text==null)){
			return UserTools.serviceRefused("Mauvais arguments", -1);
		//Verification de l'auteur
		}else if(!UserTools.userIdExist(id_author)){
			return UserTools.serviceRefused("User inexistant", -1);
			
		}else{
			String message = MessageTools.addMessage(id_author, text);
			String username = UserTools.getLoginUser(id_author);
			try {
				js.put("message",text);
				js.put("id_author", id_author);
				js.put("username", username);
				return js;
			} catch (org.json.JSONException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
		
	}
}
