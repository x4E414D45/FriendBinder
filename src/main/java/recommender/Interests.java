package recommender;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import edu.cpp.Rafikie.data.provider.MongoDBConnection;
import edu.cpp.Rafikie.data.provider.MongoDBConnectionImpl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;

public class Interests {

	MongoDBConnection connection;
	private ArrayList<String> allInterests;
	private Hashtable<String, Integer> interestIndex;

	public Interests() {
		connection = new MongoDBConnectionImpl();
		allInterests = getInterestsFromDB();
		interestIndex = createInterestIndex();
	}

	private ArrayList<String> getInterestsFromDB() {
		DBCollection collection = connection.createConnection();
		DBCursor cursor = collection.find();
		HashSet<String> interestsSet = new HashSet();
		while (cursor.hasNext()) {
			String userInterests = cursor.next().get("interests").toString();
			for (String interest : userInterests.split(",")) {
				interestsSet.add(interest.trim().toLowerCase());
			}
		}
		ArrayList<String> interests = new ArrayList<>(interestsSet);
		Collections.sort(interests);
		return interests;
	}

	private Hashtable<String, Integer> createInterestIndex() {
		Hashtable<String, Integer> index = new Hashtable<>();
		int i = 0;
		for (String interest : allInterests) {
			index.put(interest, i);
			++i;
		}
		return index;
	}

	public void updateUserVector(String userEmail) {
		// TODO
	}

	public void updateAllUserVectors() {
		// TODO
	}

}
