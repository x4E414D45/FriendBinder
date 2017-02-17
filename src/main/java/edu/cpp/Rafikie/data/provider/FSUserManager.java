package edu.cpp.Rafikie.data.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import edu.cpp.Rafikie.data.Register;
import edu.cpp.Rafikie.data.User;
import edu.cpp.Rafikie.data.UserDetails;
import edu.cpp.Rafikie.data.provider.MongoDBConnection;

public class FSUserManager implements UserManager {

	@Autowired
	MongoDBConnection connection;

	private static final ObjectMapper JSON = new ObjectMapper();

	@Override
	public String register(Register register) {
		BasicDBObject document = new BasicDBObject();
		document.put("name", register.getName());
		document.put("email", register.getEmail());
		document.put("password", register.getPassword());
		connection.createConnection().insert(document);
		return "true";
	}

	@Override
	public void insertUserDetails(String userDetails) throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, Object> result = new ObjectMapper().readValue(userDetails, HashMap.class);
		BasicDBObject document = new BasicDBObject();
		String object = (String) result.get("email");
		BasicDBObject searchForEmail = new BasicDBObject().append("email", object);
		DBCursor checkEmailExistence = connection.createConnectionforUserTable().find(searchForEmail);
		document.putAll(result);
		if (checkEmailExistence.hasNext()) {
			connection.createConnectionforUserTable().update(searchForEmail, document);
		} else {
			connection.createConnectionforUserTable().insert(document);
		}

	}

	@Override
	public UserDetails fetchUserDetails(String email) {
		BasicDBObject searchForEmail = new BasicDBObject().append("email", email);
		DBCursor checkEmailExistence = connection.createConnectionforUserTable().find(searchForEmail);
		Gson gson = new GsonBuilder().create();
		UserDetails details = new UserDetails();
		while (checkEmailExistence.hasNext()) {
			details = gson.fromJson(checkEmailExistence.next().toString(), UserDetails.class);
			return details;
		}
		return details;

	}

	@Override
	public List<String> isUserExist(String email) {
		List<String> list = new ArrayList<>();
		BasicDBObject searchForEmail = new BasicDBObject().append("email", email);
		DBCursor checkEmailExistence = connection.createConnection().find(searchForEmail);
		Gson gson = new GsonBuilder().create();
		while (checkEmailExistence.hasNext()) {
			Register register = gson.fromJson(checkEmailExistence.next().toString(), Register.class);
			list.add(register.getName());
			list.add(register.getPassword());
			list.add(register.getEmail());
			return list;
		}

		return list;
	}

	@Override
	public String passwordEncryption(String password) {
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
