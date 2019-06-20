package services;

import java.net.UnknownHostException;
import java.sql.SQLException;

import org.json.JSONObject;

import bd.UserTools;

public class Search {

	public static JSONObject searchPost(String key, int amis, String query) throws SQLException, UnknownHostException {
		int userId;
		JSONObject jrec = new JSONObject();
		try {
			try {
				userId = UserTools.idExistsByKey(key);

				JSONObject juser = new JSONObject();
				juser.put("id", userId);
				if(userId!=-1)
					juser.put("login", UserTools.getLoginbyId(userId));
				else
					juser.put("login", "guest");

				jrec.put("auteur",juser);
				jrec.put("recherche",query);

				if(userId==-1){	//Non connecte
					if(query=="")//sans query		
						jrec.put("resultats",PostsBd.getAllPost(userId));
					else
						jrec.put("resultats",PostsBd.getPostByQuery(query,userId));
				}else{	//connecte
					if(query==""){//sans querry
						if(amis==0)
							jrec.put("resultats",PostsBd.getAllPost(userId));
						else if(amis==1){
							jrec.put("resultats",PostsBd.getPostOfUserFriends(userId));		
							jrec.put("contacts_only",true);
						}
					}else{//Avec querry
						if(amis==0)
							jrec.put("resultats",PostsBd.getPostByQuery(query,userId));
						else if(amis==1){
							jrec.put("resultats",PostsBd.getFriendsPostByQuery(userId,query));
							jrec.put("contacts_only",true);
						}
					}
				}
			}
			catch(NumberFormatException e){
				return Outils.refusedProbleme(e.getMessage()+" code: "+ErrorCodesList.NFE);
			}catch(DbException e){
				return Outils.refusedProbleme(e.getMessage()+"code "+ErrorCodesList.NFE);
			} catch (MongoException e){
				return Outils.refusedProbleme(e.getMessage()+"code "+ErrorCodesList.NFE);
			} catch (JSONException e){
				return Outils.refusedProbleme(e.getMessage()+"code "+ErrorCodesList.NFE);
			}
		}catch(JSONException e) {
			e.printStackTrace();;
			return null;
		}
		return jrec;
	
	}
}
