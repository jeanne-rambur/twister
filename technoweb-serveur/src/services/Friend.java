package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bd.FriendTools;
import bd.UserTools;

public class Friend {
	
	public static JSONObject getListFriends(String key) throws JSONException, SQLException {
		JSONObject js = new JSONObject();
		
		//Verif des parametres
		if(key == null) {
			return UserTools.serviceRefused("Champs manquants", -1);
		}
		
		
		int userID = UserTools.getIdUserByKey(key);
		js = FriendTools.getFriends(userID);
		return js;
	}
	
	public static JSONObject searchFriend(String pseudo, String user_key) throws JSONException, SQLException {
		JSONObject js = new JSONObject();
		
		//Test des champs
		if(pseudo==null || user_key == null) {
			return UserTools.serviceRefused("Champs manquants", -1);
		}
		
			
		//Recup id de l'ami et Recup id du demandeur
		int friendID = UserTools.getIdUser(pseudo);
		int id= UserTools.getIdUserByKey(user_key);
		js = FriendTools.searchFriend(friendID,id);
		//Insetion dans la BD de la relation
		if(js == null) {
			
			return UserTools.serviceRefused("Je ne trouve pas "+pseudo, 1000);
		}
			
		
		return js;
	}
}
