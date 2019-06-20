package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bd.UserTools;

public class CreateUser {

	public static JSONObject createUser(String nom, String prenom, String login, String passwd) {
 
	try {
		if((nom==null)||(prenom==null)||(login==null)||(passwd==null)){
			return UserTools.serviceRefused("mauvais arguments",-3);
		}
		if(UserTools.userExist(login)) {
			return UserTools.serviceRefused("Existe déjà",1);
		}
		if(!UserTools.addUser(nom,prenom, login, passwd)) {
			return UserTools.serviceRefused("Impossible d'inserer dans la BD", 1000);
		}
			//UserTools.addUser(nom, prenom, login, passwd);
			
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	JSONObject j= new JSONObject();
	
	try {
		j.put("OK","bienvenu "+prenom);
		return j;
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return UserTools.serviceRefused("Wrong arg",2);
		
	}
	
}

//?nom=toto&prenom=sergio&login=3410&password=gfgf


}