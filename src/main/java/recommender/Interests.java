package recommender;

import com.websystique.springmvc.core.MongoDBConnection;
import com.websystique.springmvc.core.MongoDBConnectionImpl;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class Interests {

	MongoDBConnection connection;
	private String[] allInterests;

	public Interests() {
		connection = new MongoDBConnectionImpl();
	}

	public String[] getAllInterests() {
		System.out.println(connection);
		DBCollection collection = connection.createConnection();
	    	DBCursor cursor = collection.find();
    		while(cursor.hasNext()) {
        		System.out.println(cursor.next().get("interests"));
    		}
		return null;
	}

}
