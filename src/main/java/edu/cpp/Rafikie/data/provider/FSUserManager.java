package edu.cpp.Rafikie.data.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.Basic;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

import edu.cpp.Rafikie.data.AddedFriends;
import edu.cpp.Rafikie.data.FetchMessage;
import edu.cpp.Rafikie.data.FriendDetails;
import edu.cpp.Rafikie.data.FriendRequests;
import edu.cpp.Rafikie.data.Image;
import edu.cpp.Rafikie.data.Message;
import edu.cpp.Rafikie.data.MessageDetails;
import edu.cpp.Rafikie.data.Notifications;
import edu.cpp.Rafikie.data.Receiver;
import edu.cpp.Rafikie.data.Register;
import edu.cpp.Rafikie.data.UserDetails;
import edu.cpp.Rafikie.recommender.InterestsImpl;

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
		InterestsImpl impl=new InterestsImpl();
		
		BasicDBObject searchForEmail = new BasicDBObject().append("email", object);
		DBCursor checkEmailExistence = connection.createConnectionforUserTable().find(searchForEmail);
		document.putAll(result);
		System.out.println(document);
		if (checkEmailExistence.hasNext()) {
			connection.createConnectionforUserTable().update(searchForEmail, document);
			System.out.println("Updated");
			impl.updateUserVector(object);
		} else {
			connection.createConnectionforUserTable().insert(document);
			impl.updateUserVector(object);
		}

	}

	@Override
	public UserDetails fetchUserDetails(String email) {
		DBObject query = new BasicDBObject().append("email", email);
		MongoDBConnection connect=new MongoDBConnectionImpl();
		System.out.println(query);
		DBObject data = connect.createConnectionforUserTable().findOne(query);
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
			path = Paths.get("/home/ec2-user/images/" + filename + "." + split[split.length - 1]);
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
		int count=0;
		HashMap<String, Object> result = new ObjectMapper().readValue(email, HashMap.class);

		BasicDBObject document = new BasicDBObject();
		BasicDBList friendDetails = new BasicDBList();
		BasicDBObject friend = new BasicDBObject();
		friend.put("email", (String) result.get("email"));
		friend.put("image", (String) result.get("image"));
		friend.put("name", (String) result.get("friendName"));
		friend.put("isAdded", (Boolean) result.get("isAdded"));
		friendDetails.add(friend);
		Gson gson = new Gson();
		String userEmail = (String) result.get("email");
		FriendRequests friendRequests = new FriendRequests();
		BasicDBObject searchForEmail = new BasicDBObject().append("email", (String) result.get("friendEmail"));
		DBCursor checkEmailExistence = connection.createConnectionforUserNotificationTable().find(searchForEmail);
		document.put("email", (String) result.get("friendEmail"));

		if (checkEmailExistence.hasNext()) {

			friendRequests = gson.fromJson(checkEmailExistence.next().toString(), FriendRequests.class);

			for (FriendDetails details : friendRequests.getRequests()) {
				BasicDBObject fri = new BasicDBObject();
				if (!details.getEmail().equals((String) result.get("email"))) {
					fri.put("email", details.getEmail());
					fri.put("image", details.getImage());
					fri.put("name", details.getName());
					fri.put("isAdded",details.isAdded());
					fri.put("isIgnored", details.isIgnored());
					fri.put("isBlocked", details.isIgnored());
					friendDetails.add(fri);
				}
				else if(details.getEmail().equals((String) result.get("email"))&& (Boolean) result.get("isAdded"))
				{
					count++;
				}
			}
			if(count!=0)
			{
				connection.createConnectionforUserNotificationTable().remove(searchForEmail);
				document.put("requests", friendDetails);
				connection.createConnectionforUserNotificationTable().insert(document);
				
			}
			else
			{
			document.put("requests", friendDetails);
			connection.createConnectionforUserNotificationTable().update(searchForEmail, document);
			}

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
			for (FriendDetails details : friendRequests.getRequests()) {
				Notifications notifications = new Notifications();
				if(!details.isAdded())
				{
				notifications.setName(details.getName());
				notifications.setEmail(details.getEmail());
				notifications.setImage(details.getImage());
				arrayList.add(notifications);
				}
			}

		}
		return arrayList;

	}

	@Override
	public void addFriend(String email) throws JsonParseException, JsonMappingException, IOException {
		

		
	}

	@Override
	public List<FriendDetails> getFriendDetails(String email) throws JsonParseException, JsonMappingException, IOException {
		
		BasicDBObject searchForEmail = new BasicDBObject().append("email", email);
		DBCursor checkEmailExistence = connection.createConnectionforUserNotificationTable().find(searchForEmail);
		FriendRequests friendRequests = new FriendRequests();
		Gson gson=new Gson();
		List<FriendDetails> friendDetails=new ArrayList<>();
		if (checkEmailExistence.hasNext()) {

			friendRequests = gson.fromJson(checkEmailExistence.next().toString(), FriendRequests.class);

			for (FriendDetails details : friendRequests.getRequests()) {
				if(details.isAdded())
				friendDetails.add(details);
				
			}
				
			}
		return friendDetails;
		
		
		
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<String> allFriends(String email) throws JsonParseException, JsonMappingException, IOException {
		
		BasicDBObject searchForEmail = new BasicDBObject().append("email", email);
		DBCursor checkEmailExistence = connection.createConnectionforUserNotificationTable().find(searchForEmail);
		FriendRequests friendRequests = new FriendRequests();
		Gson gson=new Gson();
		List<String> friends=new ArrayList<>();
		if (checkEmailExistence.hasNext()) {

			friendRequests = gson.fromJson(checkEmailExistence.next().toString(), FriendRequests.class);

			for (FriendDetails details : friendRequests.getRequests()) {
				friends.add(details.getEmail());
				
			}
				
			}
		return friends;
		
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageDetails(String details) throws JsonParseException, JsonMappingException, IOException {
		
		HashMap<String, Object> result = new ObjectMapper().readValue(details, HashMap.class);
		BasicDBObject document = new BasicDBObject();
		BasicDBList messageDetails = new BasicDBList();
		BasicDBList messageData = new BasicDBList();
		BasicDBList receiverList=new BasicDBList();
		BasicDBObject message=new BasicDBObject();
		BasicDBObject senderDetails = new BasicDBObject();
		UserManager manager=new FSUserManager();
		//int lastCount=0;
		int lastCount=manager.getLastCount((String) result.get("senderEmail"), (String) result.get("receiverEmail"));

		Gson gson = new Gson();
		MessageDetails mdetails=new MessageDetails();
		BasicDBObject searchForEmail = new BasicDBObject().append("email", (String) result.get("senderEmail"));
		DBCursor checkEmailExistence = connection.createConnectionforMessageTable().find(searchForEmail);
		int checkForSenderTable=0;
	
		
		while (checkEmailExistence.hasNext()) {
			checkForSenderTable++;
			mdetails = gson.fromJson(checkEmailExistence.next().toString(), MessageDetails.class);
			int c=0;
			
			for (Receiver receiverDetails : mdetails.getReceiverDetails()) {//working fine
				
				if(receiverDetails.getReceiverEmail().equals((String) result.get("receiverEmail")))
				{
					c++;
					for(Message message2:receiverDetails.getMessage())
					{   BasicDBObject messages=new BasicDBObject();
						messages.put("message", message2.getMessage());
						messages.put("count",message2.getCount());
						messageData.add(messages);
					}
					message.put("message", (String) result.get("message"));
					message.put("count",lastCount+1);
					messageData.add(message);
					/*senderDetails.put("count",lastCount+1);*/
					/*senderDetails.put("receiverEmail", (String) result.get("receiverEmail"));
					senderDetails.put("receiverName", (String) result.get("receiverName"));
					senderDetails.put("message", messageData);*/
					/*receiverList.add(senderDetails);*/
					DBObject queryForElem = QueryBuilder.start("receiverDetails").elemMatch(new BasicDBObject("receiverEmail", (String) result.get("receiverEmail"))).get();
					DBObject updateMatchingElem = new BasicDBObject("$set", new BasicDBObject("receiverDetails.$.message", messageData).append("receiverDetails.$.count", lastCount+1));
					connection.createConnectionforMessageTable().update(queryForElem, updateMatchingElem);
					//document.put("receiverDetails",receiverList);
					//connection.createConnectionforMessageTable().update(searchForEmail, document);
					
				}
				
				
					
				}
			if(c==0)
			{
				
				message.put("message", (String) result.get("message"));
				message.put("count",lastCount+1);
				messageData.add(message);
				senderDetails.put("count",lastCount+1);
				senderDetails.put("receiverEmail", (String) result.get("receiverEmail"));
				senderDetails.put("receiverName", (String) result.get("receiverName"));
				senderDetails.put("message", messageData);
				receiverList.add(senderDetails);
				/*document.put("email", (String) result.get("senderEmail"));
				document.put("name",(String) result.get("senderName"));*/
				document.put("receiverDetails",senderDetails);
				BasicDBObject basicDBList=new BasicDBObject();
				DBObject updateQuery = new BasicDBObject("$push", document);
				connection.createConnectionforMessageTable().update( searchForEmail, updateQuery);
				
			}
			
			
			
				}
		//working fine
		if(checkForSenderTable==0)
		{
			
			//BasicDBList messageData = new BasicDBList();
			message.put("message", (String) result.get("message"));
			message.put("count",lastCount+1);
			messageData.add(message);
			senderDetails.put("receiverEmail", (String) result.get("receiverEmail"));
			senderDetails.put("receiverName", (String) result.get("receiverName"));
			senderDetails.put("count",lastCount+1);
			senderDetails.put("message", messageData);
			//receiverList.put(0, senderDetails);
			receiverList.add(senderDetails);
			document.put("email", (String) result.get("senderEmail"));
			document.put("name",(String) result.get("senderName"));
			document.put("receiverDetails",receiverList);
			connection.createConnectionforMessageTable().insert(document);
		}
		
		
	}
	
	
	@Override
	public int getLastCount(String email, String receiverEmail) throws JsonParseException, JsonMappingException, IOException {
		int lastCount=0;
		Gson gson = new Gson();
		MessageDetails mdetails=new MessageDetails();
		BasicDBObject searchForEmail = new BasicDBObject().append("email",email);
		MongoDBConnection connection=new MongoDBConnectionImpl();
		DBCursor checkEmailExistence = connection.createConnectionforMessageTable().find(searchForEmail);
		while (checkEmailExistence.hasNext()) {
			mdetails = gson.fromJson(checkEmailExistence.next().toString(), MessageDetails.class);
			for (Receiver receiverDetails : mdetails.getReceiverDetails()) {//working fine
				
				if(receiverDetails.getReceiverEmail().equals(receiverEmail))
				lastCount=receiverDetails.getCount();			
				}
		
	}
		BasicDBObject searchForAnotherEmail = new BasicDBObject().append("email", receiverEmail);
		DBCursor checkAnotherEmailExistence = connection.createConnectionforMessageTable().find(searchForAnotherEmail);
		while (checkAnotherEmailExistence.hasNext()) {
			mdetails = gson.fromJson(checkAnotherEmailExistence.next().toString(), MessageDetails.class);
			for (Receiver receiverDetails : mdetails.getReceiverDetails()) {//working fine
				
				if(receiverDetails.getReceiverEmail().equals(email))
				{
					if(receiverDetails.getCount()>lastCount)
					   lastCount=receiverDetails.getCount();
				}

				}
		
	}
		return lastCount;

		
	}

	@Override
	public ArrayList<FetchMessage> fetchMessages(String details)
			throws JsonParseException, JsonMappingException, IOException {
		BasicDBObject searchForEmail ;
		Gson gson=new Gson();
		DBCursor checkEmailExistence;
		TreeMap<Integer, FetchMessage> message=new TreeMap<>();
		HashMap<String, Object> result = new ObjectMapper().readValue(details, HashMap.class);
		searchForEmail= new BasicDBObject().append("email",(String) result.get("senderEmail"));
		MessageDetails mdetails=new MessageDetails();
		 checkEmailExistence = connection.createConnectionforMessageTable().find(searchForEmail);
		while (checkEmailExistence.hasNext()) {
			mdetails = gson.fromJson(checkEmailExistence.next().toString(), MessageDetails.class);
			for (Receiver receiverDetails : mdetails.getReceiverDetails()) {//working fine
				
				if(receiverDetails.getReceiverEmail().equals((String) result.get("receiverEmail")))
				{
					
					for(Message message2:receiverDetails.getMessage())
					{
						FetchMessage fetchMessage=new FetchMessage();
						fetchMessage.setName(mdetails.getName());
						fetchMessage.setMessage(message2.getMessage());
						System.out.println(fetchMessage.getMessage());
						message.put(message2.getCount(),fetchMessage );
					}
				}
					
				}
				
			}
		searchForEmail= new BasicDBObject().append("email",(String) result.get("receiverEmail"));
		checkEmailExistence= connection.createConnectionforMessageTable().find(searchForEmail);
		while (checkEmailExistence.hasNext()) {
			mdetails = gson.fromJson(checkEmailExistence.next().toString(), MessageDetails.class);
			for (Receiver receiverDetails : mdetails.getReceiverDetails()) {//working fine
				
				if(receiverDetails.getReceiverEmail().equals((String) result.get("senderEmail")))
				{
					
					for(Message message2:receiverDetails.getMessage())
					{
						FetchMessage fetchMessage=new FetchMessage();
						fetchMessage.setName(mdetails.getName());
						fetchMessage.setMessage(message2.getMessage());
						System.out.println(fetchMessage.getMessage());
						message.put(message2.getCount(),fetchMessage );
					}
				}
					
				}
				
			}
		
		ArrayList<FetchMessage> arrayList=new ArrayList<>();
		for(FetchMessage fetchMessage:message.values())
		{
			arrayList.add(fetchMessage);
		}
		
		
		
		
		
		// TODO Auto-generated method stub
		return arrayList;
	}


	
	
	
	

}
