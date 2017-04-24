package org.restproject.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

@Path("/secured")
public class GetUserDetails {
	public static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@GET
	@Path("/getdetails")
	@Produces(MediaType.TEXT_HTML)
	public String register(@Context UriInfo uriInfo){
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters(); 
	     String userId = queryParams.getFirst("userid");
	     List<String> list=new ArrayList<>();
		try {		
			@SuppressWarnings("deprecation")
			Query query=new Query("userapi").addFilter("UserId", FilterOperator.EQUAL, userId);
			PreparedQuery pq=datastore.prepare(query);
			
			for(Entity u:pq.asIterable()){
				String usrId=u.getProperty("UserId").toString();
				String pasword=u.getProperty("Password").toString();
				String name=u.getProperty("Name").toString();
				String email=u.getProperty("Email").toString();
				String age=u.getProperty("Age").toString();
			list.add(usrId);
			list.add(pasword);
			list.add(name);
			list.add(email);
			list.add(age);			
				
			}
	
		
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		return list.toString();
		}

}
