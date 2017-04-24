package org.restproject.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

public class GetUserDetailsDAO {
	public static DatastoreService datastore=DatastoreServiceFactory.getDatastoreService();
	
	public boolean getUserDetailDAO(String userId,String Password){
	String userIdReq=userId;
	String passwordReq=Password;
	try {
	
		@SuppressWarnings("deprecation")
		Query query=new Query("userapi").addFilter("UserId", FilterOperator.EQUAL, userIdReq);
		PreparedQuery pq=datastore.prepare(query);
		List<String> list=new ArrayList<>();
		for(Entity u:pq.asIterable()){
			String usrId=u.getProperty("UserId").toString();
			String password=u.getProperty("Password").toString();
			String name=u.getProperty("Name").toString();
			String email=u.getProperty("Email").toString();
			String age=u.getProperty("Age").toString();
		list.add(usrId);
		list.add(password);
		list.add(name);
		list.add(email);
		list.add(age);			
			
		}
		if(list.isEmpty()){
			return false;
		}
		if(list.get(1).equals(passwordReq)){
			return true;
		}
		
	}catch(Exception e){
		
	}
	return false;
	}
}
