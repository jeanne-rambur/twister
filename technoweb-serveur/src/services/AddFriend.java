package services;

import java.sql.SQLException;

import org.json.JSONObject;

import bd.FriendTools;
import bd.UserTools;

public class AddFriend {
	public static JSONObject addFriend(String key, int id_friend) throws JSONException, SQLException{
		
		String friend = UserTools.getLoginbyId( id_friend);
		if((key==null)||(friend==null)){
			return UserTools.serviceRefused("Erreur, mauvais arguments", -1);
		}
		if(!UserTools.userIdExist(id_friend))
			return UserTools.serviceRefused("L'identifiant de l'ami que vous cherchez est introuvable",-1);
		//Verifier si ils sont déjà amis
		boolean is_friend = FriendTools.alreadyFriend(key, id_friend);
		
		if (is_friend){
			return UserTools.serviceRefused("Already Friends", -2);
		}
		
		//Verifier si l'id de l'ami existe
		if(UserTools.userIdExist(id_friend)){
			
			FriendTools.insertFriend(key, id_friend);
			FriendTools.insertFriend2(key, id_friend);
			
			JSONObject js = new JSONObject();
			
			try {
				js.put("OK", "AMI AJOUTE");
				js.put("id_friend", UserTools.getLoginUser(id_friend));
			} catch (org.json.JSONException e) {
				e.printStackTrace();
			}
			
			return js;
		}
		return UserTools.serviceRefused("L'id de l'utilisateur est inconnu", -2);
		
		
	}
}
