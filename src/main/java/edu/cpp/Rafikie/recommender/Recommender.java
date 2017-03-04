package edu.cpp.Rafikie.recommender;

import edu.cpp.Rafikie.data.FriendsWithSimilarInterests;
import edu.cpp.Rafikie.data.UserDetails;
import edu.cpp.Rafikie.data.provider.UserManager;
import edu.cpp.Rafikie.util.Geocoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("Recommender")
public class Recommender {

	private static final int MAX_RECOMMENDED = 100;

	@Autowired
	private UserManager userManager;

	public ArrayList<FriendsWithSimilarInterests> recommend(String userEmail) {
		ArrayList<FriendsWithSimilarInterests> recommendations = new ArrayList<>();
		UserDetails user = userManager.fetchUserDetails(userEmail);

		ArrayList<UserDetails> allUsers = new ArrayList<>();
		for (String email : new DBAccessor().getAllEmails()) {
			allUsers.add(userManager.fetchUserDetails(email));
		}

		ArrayList<UserDetails> similarUsers = mostSimilarUsers(user, allUsers);
		for (UserDetails similarUser : similarUsers) {
			if (!similarUser.getEmail().equals(user.getEmail())) {
				FriendsWithSimilarInterests friend = new FriendsWithSimilarInterests();
				friend.setAbout(similarUser.getAbout());
				friend.setName(similarUser.getName());
				friend.setInterests(intersectInterests(user, similarUser));
				friend.setLanguages(similarUser.getLanguage());
				friend.setLocation(similarUser.getLocation());
				friend.setImage(userManager.fetchImage(similarUser.getEmail()).getImage());
                                System.out.println(friend.getImage());
				friend.setEmail(similarUser.getEmail());
				recommendations.add(friend);
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
		Double[] a_v = a.getVectorRepr();
		Double[] b_v = b.getVectorRepr();
		if (a_v == null || b_v == null || a_v.length == 0 || b_v.length == 0) {
			System.out.println("Error: add interests to get recommendations!");
			return 0.0;
		}
		RealVector rv_a = new ArrayRealVector(a.getVectorRepr());
		RealVector rv_b = new ArrayRealVector(b.getVectorRepr());
		Double interestSimilarity = rv_a.cosine(rv_b);
		// Double physicalDistanceNorm = getNormalizedPhysicalDistance(a, b);

		//return interestSimilarity - physicalDistanceNorm;
		return interestSimilarity;
	}

	private Double getNormalizedPhysicalDistance(UserDetails a, UserDetails b) {
		Double MAX_DIST = 6356000.0;
		/*
		try {
			Double dist = Geocoder.getDistanceMeters(a.getCoordinates(), b.getCoordinates());
			Double distNormalized = dist / MAX_DIST;
			return distNormalized;
		} catch(NullPointerException e) {
			System.out.println("Error: location is not set");
			return 1.0;
		}
		*/
		return null;
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

	private String[] intersectInterests(UserDetails a, UserDetails b) {
		Set<String> a_interests = new HashSet<>();
		Collections.addAll(a_interests, StringProcessor.preprocessInterests(a.getInterests()));
		Set<String> b_interests = new HashSet();
		Collections.addAll(b_interests, StringProcessor.preprocessInterests(b.getInterests()));
		a_interests.retainAll(b_interests);
		String[] intersect = a_interests.toArray(new String[a_interests.size()]);
		return intersect;
	}

}
