package bd;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

public class UserTools {
	
	public static JSONObject serviceRefused(String message, int erreur ) {
		
		JSONObject j= new JSONObject();

		try {
			j.put("erreur",erreur);
			j.put("message",message);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.getMessage();

		}
		return j;

	}
	
	public static JSONObject serviceAccepted() {
		JSONObject j= new JSONObject();
		try {
			j.put("","ok");
			
		}catch(JSONException e) {
			e.getMessage();
		}
		return j;
	}

	
	public static boolean userExist(String login) throws SQLException{

		try {
			String query=" SELECT * FROM users WHERE login = '"+login+"';";
			Connection c = DataBase.getMySQLConnection();
			Statement s= c.createStatement();
			ResultSet rs= s.executeQuery(query);
			if(!rs.next()) {
				return false;
			}
			
			rs.close();
			s.close();
			c.close();

			return true;

		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static boolean checkpasswd(String login, String password) throws SQLException{
		
		boolean answer =false;
		String query=" SELECT * FROM users WHERE login='"+login+"';";
		String pass="";
		try{
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			ResultSet rs= s.executeQuery(query);
			if(rs.next()) {
				pass=rs.getString("password");
				if(pass.equals(password)){
					answer = true;	
				}
			}
			rs.close();
			s.close();
			c.close();
			
		}catch(SQLException e){
			e.getMessage();
		}
		
		return answer;
	}
	
	
	
	public static boolean keyVerif(String key) throws SQLException{
		
		String query="SELECT * FROM session WHERE key_session='"+key+"';";
		Connection c = DataBase.getMySQLConnection();
		Statement s= c.createStatement();
		ResultSet rs= s.executeQuery(query);
		boolean answer= rs.next();
		
		rs.close();
		s.close();
		c.close();
		
		return answer;
			
		
		
	}

	public static boolean addUser(String nom, String prenom, String login, String passwd) throws SQLException {
		
		
		String query="INSERT INTO users (`nom`,`prenom`,`login`,`password`) VALUES('"+nom+"','"+prenom+"','"+login+"','"+passwd+"');";
		
		Connection c = DataBase.getMySQLConnection();
		
		Statement s= c.createStatement();
		
		int rs= s.executeUpdate(query);
		if(rs!=0){
			s.close();
			return true;
			
		}
		s.close();
		return false;
	}
		
		public static String getLoginbyId(int id) throws SQLException {
			String login="";
			try {
				String query = "SELECT * FROM users WHERE id= '"+id+"';";
				Connection c = DataBase.getMySQLConnection();
				Statement s = c.createStatement();
				s.executeQuery(query);
				ResultSet rs = s.getResultSet();
				if (rs.next())
					login = rs.getString("login");
				// Fermeture de la connexion
				rs.close();
				s.close();
				c.close();
				return login;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			
	}


	

	public static void creerTables() {
		// TODO Auto-generated method stub
		
	}

	public static int getIdUser(String login) throws SQLException {
		
		String query="SELECT * FROM users WHERE login='"+login+"';";
		Connection c = DataBase.getMySQLConnection();
		Statement s= c.createStatement();
		ResultSet rs= s.executeQuery(query);
		int id=0;
		if(rs.next()){
			id = rs.getInt("id");
		}
		
		rs.close();
		s.close();
		c.close();
		
		return id;
	}
	

	public static boolean userIdExist(int id) throws SQLException {
		
		String query="SELECT * FROM users WHERE id="+id+";";
		Connection c = DataBase.getMySQLConnection();
		Statement s= c.createStatement();
		ResultSet rs= s.executeQuery(query);
		
		boolean answer= rs.next();
		
		rs.close();
		s.close();
		c.close();
		
		return answer;
	}

	public static String getLoginUser(int id_friend) throws SQLException {
		
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();

		String query = "SELECT users.login FROM users WHERE id='"+id_friend+"';" ;
		ResultSet rs = s.executeQuery(query);
		String login= null;
		while(rs.next()) {
			login = rs.getString("login");
		}
		rs.close();
		s.close();
		return login;
	}

	public static int getIdUserByKey(String key) throws SQLException {
		
		Connection c = DataBase.getMySQLConnection();
		Statement s = c.createStatement();

		String query = "SELECT session.id_user FROM session WHERE session.key_session='"+key+"';" ;
		ResultSet rs = s.executeQuery(query);
		
		if(rs.next()){
			int id = rs.getInt(1);
		
			rs.close();
			s.close();
			c.close();
			
			return id;
		}
		rs.close();
		s.close();
		c.close();
		return 0;
		
	}


	private static String generatekey() {
		String chars = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpP"
				+ "qQrRsStTuUvVwWxXyYzZ0123456789";
		String key = "";
		for (int i=0; i<32; i++) {
			int r = new Random().nextInt(chars.length());
			key += chars.charAt(r);
		}
		return key;
	}
	
	public static String insertSession(int id, boolean root) throws SQLException {
		
		String key= generatekey();
		Connection c =DataBase.getMySQLConnection();
		Statement s = c.createStatement();
		String query = "INSERT INTO session VALUES ('"+id+"','"+key+"','"+new Timestamp(System.currentTimeMillis())+"',"+root+");";
		s.executeUpdate(query);
		
		s.close();
		c.close();
		
		return key;
	}
	/*
	public static void logout(String key) throws SQLException{
		String query="UPDATE session SET expire=1 WHERE cle="+key+"";
		Connection c= DataBase.getMySQLConnection();
		Statement s=c.createStatement();
		ResultSet rs= s.executeQuery(query);
		
		rs.close();
		s.close();
		c.close();
	}
	*/
	public static void removeSession(String key) throws SQLException{
		Connection c= DataBase.getMySQLConnection();
		Statement s=c.createStatement();
		String query="DELETE from session WHERE session.key_session='"+key+"';";
		s.executeUpdate(query);
		
		s.close();
		c.close();
	}
	
	public static int idExistsByKey(String sessionKey) throws NumberFormatException {
		String query = "SELECT * FROM session WHERE key_session='"+sessionKey+"';";
		int idUser=-1;
		try {
			Connection c = DataBase.getMySQLConnection();
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery(query);
			if(rs.next())
				idUser=rs.getInt("id");
			rs.close();
			s.close();
			c.close();
		}catch(NumberFormatException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idUser;
	}


}

