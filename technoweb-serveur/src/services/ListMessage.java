package services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import bd.FriendTools;
import bd.MessageTools;
import bd.UserTools;

public class ListMessage {
	public static JSONObject getAllMessage(String login) throws JSONException, SQLException {
		List<Document> d  = new ArrayList<Document>();
		
		//Verif des parametres
		if(login == null) {
			return UserTools.serviceRefused("Champs manquants", -1);
		}
		
		
		//int userID = UserTools.getIdUserByKey(key);
		d = MessageTools.getMessage(login);
		return (JSONObject) d;
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
