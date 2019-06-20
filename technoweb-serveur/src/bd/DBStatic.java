package bd;

import com.mongodb.MongoClientException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class DBStatic {
	public static boolean mysql_pooling = false;
	public static String HOST="localhost:8888";
	public static String db_name = "siteweb";
	public static String mysql_username="root";
	public static String mysql_password="";
	public static DataBase database = null;
	
	public static MongoDatabase getMyMongoConnection(){
		try {
			MongoClient mongo = MongoClients.create("mongodb://localhost:27017");
			MongoDatabase mdb= mongo.getDatabase("db_message");
			return mdb;
			
		}catch(MongoClientException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
