package services;

import java.sql.SQLException;

import org.json.JSONObject;

import bd.FriendTools;
import bd.UserTools;

public class DeleteFriend {
	public static JSONObject deleteFriend(String key, int id_friend) throws SQLException {
		
		if(key==null){
			return UserTools.serviceRefused("Mauvais argument", -3);
			
		}
		
	
		if(UserTools.userIdExist(id_friend)){
			
			FriendTools.deleteFriend(key, id_friend);
			FriendTools.deleteFriend2(key, id_friend);
			JSONObject js = new JSONObject();
			
			try {
				js.put("OK", "AMI SUPPRIME");
				js.put("id_friend", UserTools.getLoginUser(id_friend));
			} catch (org.json.JSONException e) {
				e.printStackTrace();
			}
			
			return js;
		}
		
		return UserTools.serviceRefused("Not Friends",-2);
	}


}
