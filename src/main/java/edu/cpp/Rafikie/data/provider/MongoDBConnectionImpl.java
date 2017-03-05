package edu.cpp.Rafikie.data.provider;

import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

@Service("mongoDBConnection")
public class MongoDBConnectionImpl implements MongoDBConnection {

	private static MongoClient mongo;

	public MongoDBConnectionImpl() {
		try {
			mongo = new MongoClient("localhost", 27017);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DBCollection createConnection() {
		try {
			/**
			 * ** Get database ***
			 */
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("testdb");

			/**
			 * ** Get collection / table from 'testdb' ***
			 */
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("users");
			return table;
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}

	public DBCollection createConnectionforUserTable() {
		try {
			/**
			 * ** Get database ***
			 */
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("testdb");

			/**
			 * ** Get collection / table from 'testdb' ***
			 */
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("userDetails");
			return table;

		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}

	public DBCollection createConnectionforUserImageTable() {
		try {
			/**
			 * ** Get database ***
			 */
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("testdb");

			/**
			 * ** Get collection / table from 'testdb' ***
			 */
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("userImage");
			return table;
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}

	public DBCollection createConnectionforUserNotificationTable() {
		try {
			/**
			 * ** Get database ***
			 */
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("testdb");

			/**
			 * ** Get collection / table from 'testdb' ***
			 */
			// if collection doesn't exists, MongoDB will create it for you
			DBCollection table = db.getCollection("notifications");
			return table;
		} catch (MongoException e) {
			e.printStackTrace();
		}
		return null;

	}

}
