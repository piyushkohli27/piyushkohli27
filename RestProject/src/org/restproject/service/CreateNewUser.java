package org.restproject.service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class CreateNewUser {
	public static DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
	
	public boolean createNewUser(String userId,String password,String email,String name,String age){
		Entity user=new Entity("userapi",userId);
		user.setProperty("UserId", userId);
		user.setProperty("Password", password);
		user.setProperty("Name", name);
		user.setProperty("Email", email);
		user.setProperty("Age", age);
		datastore.put(user);	
		return true;
	}

}
