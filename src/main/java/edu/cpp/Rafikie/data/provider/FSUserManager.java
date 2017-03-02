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
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import edu.cpp.Rafikie.data.FriendRequests;
import edu.cpp.Rafikie.data.Image;
import edu.cpp.Rafikie.data.Notifications;
import edu.cpp.Rafikie.data.Register;
import edu.cpp.Rafikie.data.UserDetails;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class FSUserManager implements UserManager {

	@Autowired
	MongoDBConnection connection;

	private static final ObjectMapper JSON = new ObjectMapper();

	@Override
	public String register(Register register) {
		BasicDBObject document = new BasicDBObject();
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
		System.out.println(document);
		if (checkEmailExistence.hasNext()) {
			connection.createConnectionforUserTable().update(searchForEmail, document);
			System.out.println("Updated");
		} else {
			connection.createConnectionforUserTable().insert(document);
		}

	}

	@Override
	public UserDetails fetchUserDetails(String email) {
		DBObject query = new BasicDBObject().append("email", email);
		System.out.println(query);
		DBObject data = connection.createConnectionforUserTable().findOne(query);
		Gson gson = new GsonBuilder().create();
		UserDetails details = new UserDetails();
		details = gson.fromJson(data.toString(), UserDetails.class);
		System.out.println(details);
		return details;

	}

	@Override
	public Image fetchImage(String email) {
		BasicDBObject searchForEmail = new BasicDBObject().append("email", email);
		DBCursor checkEmailExistence = connection.createConnectionforUserImageTable().find(searchForEmail);
		Gson gson = new GsonBuilder().create();
		Image image = new Image();
		while (checkEmailExistence.hasNext()) {
			image = gson.fromJson(checkEmailExistence.next().toString(), Image.class);
			return image;
		}
		return image;

	}

	@Override
	public List<String> isUserExist(String email) {
		List<String> list = new ArrayList<>();
		BasicDBObject searchForEmail = new BasicDBObject().append("email", email);
		DBCursor checkEmailExistence = connection.createConnection().find(searchForEmail);
		Gson gson = new GsonBuilder().create();
		while (checkEmailExistence.hasNext()) {
			Register register = gson.fromJson(checkEmailExistence.next().toString(), Register.class);
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

	@Override
	public String convertImageToBase64(HttpServletRequest request) {
		MultipartHttpServletRequest mRequest;
		String image = null;
		String filename = "upload.jpeg";
		try {
			mRequest = (MultipartHttpServletRequest) request;
			mRequest.getParameterMap();

			Iterator itr = mRequest.getFileNames();
			while (itr.hasNext()) {
				MultipartFile mFile = mRequest.getFile((String) itr.next());
				String fileName = mFile.getOriginalFilename();
				System.out.println(fileName);

				java.nio.file.Path path = Paths.get("D:/Data/" + filename);
				Files.deleteIfExists(path);
				InputStream in = mFile.getInputStream();
				Files.copy(in, path);
				File file = new File("D:/Data/" + filename);

				// Reading a Image file from file system
				FileInputStream imageInFile = new FileInputStream(file);
				byte imageData[] = new byte[(int) file.length()];
				imageInFile.read(imageData);
				image = new String(Base64.encodeBase64(imageData), "UTF-8");
				imageInFile.close();
				file.delete();
				return image;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return image;

	}

	@Override
	public void uploadImageToDatabase(HttpServletRequest request, String email) throws JsonParseException, JsonMappingException, IOException {
		MultipartHttpServletRequest mRequest;
		String filename = email;
		mRequest = (MultipartHttpServletRequest) request;
		mRequest.getParameterMap();
		java.nio.file.Path path = null;
		Iterator itr = mRequest.getFileNames();
		while (itr.hasNext()) {
			MultipartFile mFile = mRequest.getFile((String) itr.next());
			String fileName = mFile.getOriginalFilename();
			String[] split = fileName.split("\\.");

			path = Paths.get("src/main/resources/static/images/users/" + filename + "." + split[split.length - 1]);
			Files.deleteIfExists(path);
			InputStream in = mFile.getInputStream();
			Files.copy(in, path);
			filename = filename + "." + split[split.length - 1];

		}
		BasicDBObject document = new BasicDBObject();
		BasicDBObject searchForEmail = new BasicDBObject().append("email", email);
		DBCursor checkEmailExistence = connection.createConnectionforUserImageTable().find(searchForEmail);
		document.put("email", email);
		document.put("image", filename);
		if (checkEmailExistence.hasNext()) {
			connection.createConnectionforUserImageTable().update(searchForEmail, document);
		} else {
			connection.createConnectionforUserImageTable().insert(document);
		}
	}

	@Override
	public boolean addFriendRequests(String email) throws JsonParseException, JsonMappingException, IOException {
		HashMap<String, Object> result = new ObjectMapper().readValue(email, HashMap.class);

		BasicDBObject document = new BasicDBObject();
		BasicDBList friendDetails = new BasicDBList();
		BasicDBObject friend = new BasicDBObject();
		friend.put("email", (String) result.get("email"));
		friend.put("image", (String) result.get("image"));
		friendDetails.add(friend);
		Gson gson = new Gson();
		String userEmail = (String) result.get("email");
		FriendRequests friendRequests = new FriendRequests();
		BasicDBObject searchForEmail = new BasicDBObject().append("email", (String) result.get("friendEmail"));
		DBCursor checkEmailExistence = connection.createConnectionforUserNotificationTable().find(searchForEmail);
		document.put("email", (String) result.get("friendEmail"));

		if (checkEmailExistence.hasNext()) {

			friendRequests = gson.fromJson(checkEmailExistence.next().toString(), FriendRequests.class);

			for (Image image : friendRequests.getRequests()) {
				BasicDBObject fri = new BasicDBObject();
				if (!image.getEmail().equals((String) result.get("email"))) {
					fri.put("email", image.getEmail());
					fri.put("image", image.getImage());
					friendDetails.add(fri);
				}
			}
			document.put("requests", friendDetails);
			connection.createConnectionforUserNotificationTable().update(searchForEmail, document);

		} else {
			document.put("requests", friendDetails);
			connection.createConnectionforUserNotificationTable().insert(document);

		}

		return false;
	}

	public ArrayList<Notifications> getNotifications(String email) {
		BasicDBObject searchForEmail = new BasicDBObject().append("email", email);
		DBCursor checkEmailExistence = connection.createConnectionforUserNotificationTable().find(searchForEmail);
		Gson gson = new GsonBuilder().create();
		FriendRequests friendRequests = new FriendRequests();
		ArrayList<Notifications> arrayList = new ArrayList<>();

		UserManager manager = new FSUserManager();
		int count = 0;

		while (checkEmailExistence.hasNext()) {
			friendRequests = gson.fromJson(checkEmailExistence.next().toString(), FriendRequests.class);
			for (Image image : friendRequests.getRequests()) {
				Notifications notifications = new Notifications();
				notifications.setName(manager.fetchUserDetails(image.getEmail()).getName());
				notifications.setEmail(image.getImage());
				notifications.setImage(image.getImage());
				arrayList.add(notifications);
			}

		}
		return arrayList;

	}

}
