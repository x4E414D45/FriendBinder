package com.websystique.springmvc.core;

import java.net.UnknownHostException;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

@Service("mongoDBConnection")
public class MongoDBConnectionImpl implements MongoDBConnection {
		
	public	DBCollection createConnection()
		{
			try {

				/**** Connect to MongoDB ****/
				// Since 2.10.0, uses MongoClient
				MongoClient mongo = new MongoClient("localhost", 27017);

				/**** Get database ****/
				// if database doesn't exists, MongoDB will create it for you
				DB db = mongo.getDB("testdb");

				/**** Get collection / table from 'testdb' ****/
				// if collection doesn't exists, MongoDB will create it for you
				DBCollection table = db.getCollection("users");
				return table;


			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (MongoException e) {
				e.printStackTrace();
			}
			return null;

		}
		
		}
