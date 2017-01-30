package com.websystique.springmvc.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.websystique.springmvc.core.MongoDBConnection;
import com.websystique.springmvc.model.User;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
    MongoDBConnection connection;
	
	private static final AtomicLong counter = new AtomicLong();
	
	@Override
	public void saveUser(User user) {
		BasicDBObject document = new BasicDBObject();
		document.put("name", user.getName());
		document.put("email",user.getEmail());
		document.put("password", user.getPassword());
		connection.createConnection().insert(document);
	}
	
	public boolean isUserExist(User user)
	{
		BasicDBObject searchForEmail= new BasicDBObject().append("email", user.getEmail());

	DBCursor checkEmailExistence = connection.createConnection().find(searchForEmail);

	while (checkEmailExistence.hasNext())
		{return true;}
		
			return false;
		
	}
	
	public String passwordEncryption(String password)
	{
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
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
}
