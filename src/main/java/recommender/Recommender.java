package recommender;

import edu.cpp.Rafikie.data.User;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Recommender {

	private static final int MAX_RECOMMENDED = 100;

	public User[] mostSimilarUsers(User a, User[] allUsers) {
		Map<User, Double> userSimilarityMap = new LinkedHashMap<>();
		User[] similarUsers = new User[MAX_RECOMMENDED];
		for (User b : allUsers) {
			userSimilarityMap.put(b, getUserSimilarity(a, b));
		}
		userSimilarityMap = sortByValue(userSimilarityMap);

		int i = 0;
		for (User b : userSimilarityMap.keySet()) {
			if (i >= MAX_RECOMMENDED) {
				break;
			}
			similarUsers[i] = b;
			++i;
		}
		return similarUsers;
	}

	private Double getUserSimilarity(User a, User b) {
		// return a.getVectorRepr().cosine(b.getVectorRepr());
		return 0.0;
	}

	private Map<User, Double> sortByValue(Map<User, Double> unsortMap) {

		List<Map.Entry<User, Double>> list
			= new LinkedList<Map.Entry<User, Double>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<User, Double>>() {
			public int compare(Map.Entry<User, Double> o1,
				Map.Entry<User, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<User, Double> sortedMap = new LinkedHashMap<User, Double>();
		for (Map.Entry<User, Double> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

}
