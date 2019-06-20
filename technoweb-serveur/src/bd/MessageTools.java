package bd;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MessageTools {

	public static String addMessage(int id_author, String text) throws SQLException {
		MongoDatabase db = DBStatic.getMyMongoConnection();
		boolean answer= false;
		String username = UserTools.getLoginbyId(id_author);
		//MongoClient mongo = MongoClients.create();
		//MongoDatabase db = mongo.getDatabase("db_message");
		
		MongoCollection<Document> message_collection = db.getCollection("messages");
		Document query = new Document();
		
		query.append("user_id", id_author);
		query.append("text", text);
		query.append("username", username);
		
		message_collection.insertOne(query);


		FindIterable<Document> fi = message_collection.find(query);
		MongoCursor<Document> cur = fi.iterator();
		
		Connection c = DataBase.getMySQLConnection();
		
		while(cur.hasNext()) {
			cur.next();
			answer = true;
		}
		
		ObjectId retour = query.getObjectId("_id");	
		
		if (!answer)
			return null;
		return retour.toString();
		
		
	}
	public static List<Document> getMessage(String username)throws SQLException {
		MongoDatabase db = DBStatic.getMyMongoConnection();
		MongoCollection<Document> collection = db.getCollection("db_message");
		
		
		List<Document> doc=new ArrayList<Document>();
		Document query = new Document();
		query.append("username", username);
		FindIterable<Document> cursor = collection.find(query);
		for(Document dbo:cursor) {
			doc.add(dbo);
		}
		return doc;
		
	}   
	
	public static List<Document> getMessageOfUserFriends(int userId) throws SQLException {
		MongoDatabase db = DBStatic.getMyMongoConnection();
		MongoCollection<Document> collection = db.getCollection("db_message");
	 	ArrayList<Integer> listAmis = FriendTools.friendsListId(userId);
	 	//System.out.println("la liiiste "+listAmis.toString());
		List<Document> doc=new ArrayList<Document>();
		Document docu = new Document();
		for (int i=0; i < listAmis.size();i++) {
			docu.append("author", listAmis.get(i));
			FindIterable<Document> cursor = collection.find(docu);
			for(Document dbo:cursor) {
				doc.add(dbo);
			}
		}		
		return doc;
	}
	
	

}
