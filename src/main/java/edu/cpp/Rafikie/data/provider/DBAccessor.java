package edu.cpp.Rafikie.data.provider;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import edu.cpp.Rafikie.data.provider.MongoDBConnection;
import edu.cpp.Rafikie.data.provider.MongoDBConnectionImpl;
import java.util.ArrayList;

public class DBAccessor {

	private final static MongoDBConnection connection = new MongoDBConnectionImpl();

	public ArrayList<String> getAllEmails() {
		DBCollection collection = connection.createConnectionforUserTable();
		DBCursor cursor = collection.find();
		ArrayList<String> allEmails = new ArrayList<>();
		while (cursor.hasNext()) {
			try {
				String userEmail = cursor.next().get("email").toString();
				allEmails.add(userEmail);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return allEmails;
	}

}
