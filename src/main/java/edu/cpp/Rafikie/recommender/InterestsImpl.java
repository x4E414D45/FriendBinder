package edu.cpp.Rafikie.recommender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cpp.Rafikie.data.UserDetails;
import edu.cpp.Rafikie.data.UserMap;
import edu.cpp.Rafikie.data.provider.FSUserManager;
import edu.cpp.Rafikie.data.provider.MongoDBConnection;
import edu.cpp.Rafikie.data.provider.UserManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("Interests")
public class InterestsImpl implements Interests {

	MongoDBConnection connection;
	private ArrayList<String> allInterests;
	private Hashtable<String, Integer> interestIndex;

@Autowired
	private UserManager userManager;
	//UserManager userManager=new FSUserManager();

	public InterestsImpl() {
	}

	private ArrayList<String> getInterestsFromDB() {
		HashSet<String> interestsSet = new HashSet();
		ArrayList<String> allUserEmails = new DBAccessor().getAllEmails();
		for (String email : allUserEmails) {
			try {
				UserDetails userDetails = userManager.fetchUserDetails(email);
				for (String interest : userDetails.getInterests()) {
					interestsSet.add(StringProcessor.preprocessInterest(interest));
				}
			} catch (NullPointerException e) {
				System.out.println(e);
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

	// Call this every time the user updates their interests
	public void updateUserVector(String userEmail) {
		UserManager manager=new FSUserManager();
		UserDetails userDetails = manager.fetchUserDetails(userEmail);
		String[] userInterests = userDetails.getInterests();
		if (userInterests != null) {
			Double[] d_v = new Double[interestIndex.size()];
			Arrays.fill(d_v, 0.0);
			for (String interest : userInterests) {
				interest = StringProcessor.preprocessInterest(interest);
				try {
					d_v[interestIndex.get(interest)] = 1.0;
				} catch (Exception e) {
					System.out.println("Interest not in allInterests");
				}
			}
			userDetails.setVectorRepr(d_v);
			Gson gson = new GsonBuilder().create();
			try {
				String userDetailsStr = gson.toJson(userDetails);
				userManager.insertUserDetails(userDetailsStr);
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Could not convert UserDetails to string and add to DB.");
			}
		}
	}

	// Call this everytime the server is started
	public void updateAllUserVectors() {
		allInterests = getInterestsFromDB();
		interestIndex = createInterestIndex();

		ArrayList<String> allUserEmails = new DBAccessor().getAllEmails();
		for (String email : allUserEmails) {
			updateUserVector(email);
		}
		
	}

}
