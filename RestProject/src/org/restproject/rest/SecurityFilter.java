package org.restproject.rest;

import java.io.IOException;

import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.apache.geronimo.mail.util.Base64;
import org.restproject.dao.GetUserDetailsDAO;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
	private static final String SECURED_URL_PREFIX = "secured";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (null != authHeader && authHeader.size() > 0) {
				String authToken = authHeader.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				byte[] decodedString = Base64.decode((authToken));
				String newDecodedString = new String(decodedString);
				StringTokenizer tokenizer = new StringTokenizer(newDecodedString, ":");
				String userId = tokenizer.nextToken();
				String password = tokenizer.nextToken();
				GetUserDetailsDAO dao = new GetUserDetailsDAO();
				if (dao.getUserDetailDAO(userId, password)) {
					return;
				}

			}

			Response unauthorized = Response.status(Response.Status.UNAUTHORIZED).entity("You dont have access")
					.build();
			requestContext.abortWith(unauthorized);
			return;

		}

	}
}
