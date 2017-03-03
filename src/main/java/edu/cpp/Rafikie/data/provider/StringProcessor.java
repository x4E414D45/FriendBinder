package edu.cpp.Rafikie.data.provider;

public class StringProcessor {
	
	public static String[] preprocessInterests(String[] interests) {
		String[] processed = new String[interests.length];	
		for (int i = 0; i < interests.length; ++i) {
			processed[i] = preprocessInterest(interests[i]);
		}
		return processed;
	}

	public static String preprocessInterest(String interest) {
		return interest.trim().toLowerCase();
	}

}
