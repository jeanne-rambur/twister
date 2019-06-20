package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class FriendTools {

	public static boolean alreadyFriend(String key, int id_friend) throws SQLException {
		
		boolean answer = false;
		int id_user= UserTools.getIdUserByKey(key);
		String query = "SELECT * FROM friendship WHERE id_user1="+id_user+" AND id_user2="+id_friend+";";
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(query);
		
		
		if(rs.next()){
			answer=true;
		}
		answer=false;
		
		rs.close();
		s.close();
		c.close();
		
		
		return answer;
	}
	
	public static void insertFriend(String key, int id_friend) throws SQLException {
		
		int id_user=UserTools.getIdUserByKey(key);
		String query = "INSERT INTO friendship VALUES ('"+id_user+"','"+id_friend+"','"+ new Timestamp(System.currentTimeMillis())+"')";
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		s.executeUpdate(query);
		System.out.println("Ami ajouté ! "+id_friend);
		
		s.close();
		c.close();
	}
	public static void insertFriend2(String key, int id_friend) throws SQLException {
		
		int id_user=UserTools.getIdUserByKey(key);
		String query = "INSERT INTO friendship VALUES ('"+id_friend+"','"+id_user+"','"+ new Timestamp(System.currentTimeMillis())+"')";
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		s.executeUpdate(query);
		System.out.println("Ami ajouté ! "+id_friend);
		
		s.close();
		c.close();
	}
	

	
	
	/*
	public static void deleteFriend(int id_user, int id_friend) throws SQLException{
		
		String query = "DELETE FROM friends WHERE id_user="+id_user+ "AND id_friend="+id_friend+";";
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(query);
		
		System.out.println("Ami supprimé ! "+id_friend);
		
		rs.close();
		s.close();
		c.close();

	}
	*/
	
	public static void deleteFriend(String key, int id_friend) throws SQLException{
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		int id_user=UserTools.getIdUserByKey(key);
		
		String query = "DELETE FROM friendship WHERE friendship.id_user1='"+id_user+ "'AND friendship.id_user2='"+id_friend+"';";
		s.executeUpdate(query);
		System.out.println("Ami Supprimé ! "+id_friend);
		
		
		s.close();
		c.close();

	}
	public static void deleteFriend2(String key, int id_friend) throws SQLException{
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		int id_user=UserTools.getIdUserByKey(key);
		
		String query = "DELETE FROM friendship WHERE friendship.id_user1='"+id_friend+ "'AND friendship.id_user2='"+id_user+"';";
		s.executeUpdate(query);
		System.out.println("Ami Supprimé ! "+id_friend);
		
		
		s.close();
		c.close();

	}
	
	public static JSONObject getFriends (int userID)throws SQLException, JSONException {
		
		JSONObject js = new JSONObject();
		Connection c = DataBase.getMySQLConnection();
		String query = "SELECT id_user2 FROM friendship WHERE id_user1='"+userID+"'";
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery(query);
		List<JSONObject> listFriend = new ArrayList<>();
		
		while(rs.next()) { 
			String name =  UserTools.getLoginbyId(rs.getInt("id_user2"));
			JSONObject j = new JSONObject();
			
			listFriend.add(j.put("login", name));
		}
		js.put("amis", listFriend);
		
		rs.close();
		s.close();
		return js;
	}
	
	public static JSONObject searchFriend(int ami,  int login) throws SQLException, JSONException {
		
		JSONObject retour = new JSONObject();
		Connection c = DataBase.getMySQLConnection();
		String query = "SELECT * FROM users WHERE id='"+ami+"'";
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			String name =  UserTools.getLoginUser(rs.getInt("id"));
			retour.put(name, ami);
		}
		rs.close();
		st.close();
		return retour;
	}
	
	public static ArrayList<Integer> friendsListId(int userId) throws SQLException {
		String query = "SELECT * FROM users WHERE id="+userId+";";
		ArrayList<Integer> list=new ArrayList<Integer>();
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			ResultSet res = s.executeQuery( query );
			while (res.next()) 
				list.add(res.getInt("friendid"));
			s.close();
			c.close();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return list;
	}
	


}
