package org.restproject.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.restproject.service.CreateNewUser;

@Path("/register")
public class Register {
	@POST	
	@Produces(MediaType.TEXT_PLAIN)
	public String register(@Context UriInfo uriInfo){
		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
		String userId=queryParams.getFirst("userid");
		String password=queryParams.getFirst("password");
		String email=queryParams.getFirst("email");
		String age=queryParams.getFirst("age");
		String name=queryParams.getFirst("name");
		CreateNewUser newUser=new CreateNewUser();
		newUser.createNewUser(userId,password,email,age,name);		
	    return "you have registered successfully";
		
		
	}

}
