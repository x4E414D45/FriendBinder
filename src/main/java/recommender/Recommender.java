package recommender;

import edu.cpp.Rafikie.data.UserDetails;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Recommender {

	private static final int MAX_RECOMMENDED = 100;

	public UserDetails[] mostSimilarUsers(UserDetails a, UserDetails[] allUsers) {
		Map<UserDetails, Double> userSimilarityMap = new LinkedHashMap<>();
		UserDetails[] similarUsers = new UserDetails[MAX_RECOMMENDED];
		for (UserDetails b : allUsers) {
			userSimilarityMap.put(b, getUserSimilarity(a, b));
		}
		userSimilarityMap = sortByValue(userSimilarityMap);

		int i = 0;
		for (UserDetails b : userSimilarityMap.keySet()) {
			if (i >= MAX_RECOMMENDED) {
				break;
			}
			similarUsers[i] = b;
			++i;
		}
		return similarUsers;
	}

	private Double getUserSimilarity(UserDetails a, UserDetails b) {
		return a.getVectorRepr().cosine(b.getVectorRepr());
	}

	private Map<UserDetails, Double> sortByValue(Map<UserDetails, Double> unsortMap) {

		List<Map.Entry<UserDetails, Double>> list
			= new LinkedList<Map.Entry<UserDetails, Double>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<UserDetails, Double>>() {
			public int compare(Map.Entry<UserDetails, Double> o1,
				Map.Entry<UserDetails, Double> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<UserDetails, Double> sortedMap = new LinkedHashMap<UserDetails, Double>();
		for (Map.Entry<UserDetails, Double> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

}
