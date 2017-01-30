package com.websystique.springmvc.core;

import com.mongodb.DBCollection;

public interface MongoDBConnection {
	
	 public	DBCollection createConnection();

}
