package recommender;

import edu.cpp.Rafikie.data.UserDetails;
import edu.cpp.Rafikie.data.provider.UserManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("Recommender")
public class Recommender {

	private static final int MAX_RECOMMENDED = 100;

	@Autowired
	private UserManager userManager;

	public ArrayList<String> recommend(String userEmail) {
		ArrayList<String> recommendations = new ArrayList<>();
		ArrayList<UserDetails> allUsers = new ArrayList<>();
		UserDetails user = userManager.fetchUserDetails(userEmail);
		for (String email : new DBAccessor().getAllEmails()) {
			allUsers.add(userManager.fetchUserDetails(email));
		}

		ArrayList<UserDetails> similarUsers = mostSimilarUsers(user, allUsers); 
		for (UserDetails similarUser : similarUsers) {
			if (!similarUser.getEmail().equals(user.getEmail())) {
				recommendations.add(similarUser.getEmail());
			}
		}	
		
		return recommendations;
	}

	public ArrayList<UserDetails> mostSimilarUsers(UserDetails a, ArrayList<UserDetails> allUsers) {
		Map<UserDetails, Double> userSimilarityMap = new LinkedHashMap<>();
		ArrayList<UserDetails> similarUsers = new ArrayList<>();
		for (UserDetails b : allUsers) {
			userSimilarityMap.put(b, getUserSimilarity(a, b));
		}
		userSimilarityMap = sortByValue(userSimilarityMap);

		int i = 0;
		for (UserDetails b : userSimilarityMap.keySet()) {
			if (i >= MAX_RECOMMENDED) {
				break;
			}
			similarUsers.add(b);
			++i;
		}
		return similarUsers;
	}

	private Double getUserSimilarity(UserDetails a, UserDetails b) {
		if (a.getVectorRepr() == null || b.getVectorRepr() == null) {
			System.out.println("Error: add interests to get recommendations!");
			return 0.0;
		}
		RealVector rv_a = new ArrayRealVector(a.getVectorRepr());
		RealVector rv_b = new ArrayRealVector(b.getVectorRepr());
		return rv_a.cosine(rv_b);
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
