package com.sola.instagram.auth;

import com.sola.instagram.InstagramSession;
import com.sola.instagram.auth.AccessToken;
import com.sola.instagram.exception.InstagramException;
import com.sola.instagram.io.PostMethod;
import com.sola.instagram.io.UriFactory;
import com.sola.instagram.model.User;
import com.sola.instagram.util.UriConstructor;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class InstagramAuthentication {
	String redirectUri;
	String clientId;
	String clientSecret;
	AccessToken accessToken;
	User sessionUser;
	
	protected String getRedirectUri() {
		return redirectUri;
	}

	public InstagramAuthentication setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
		return this;
	}

	protected String getClientId() {
		return clientId;
	}

	public InstagramAuthentication setClientId(String clientId) {
		this.clientId = clientId;
		return this;
	}

	public String getAuthorizationUri() throws InstagramException {
		if (getClientId() == null || getRedirectUri() == null) {
			throw new InstagramException("Please make sure that the "
					+ "clientId and redirectUri fields are set");
		}
		HashMap<String, Object> args = new HashMap<String, Object>();
		args.put("client_id", getClientId());
		args.put("redirect_uri", getRedirectUri());
		args.put("response_type", "code");
		args.put("scope", "likes+comments+relationships+basic");
		return (new UriConstructor()).constructUri(
				UriFactory.Auth.USER_AUTHORIZATION, args, false);
	}

	public AccessToken build(String code) throws InstagramException {
		if (getClientSecret() == null || getClientId() == null
				|| getRedirectUri() == null) {
			throw new InstagramException("Please make sure that the"
					+ "clientId, clientSecret and redirectUri fields are set");
		}
		HashMap<String, Object> postArgs = new HashMap<String, Object>();
		postArgs.put("client_id", getClientId());
		postArgs.put("client_secret", getClientSecret());
		postArgs.put("grant_type", "authorization_code");
		postArgs.put("redirect_uri", getRedirectUri());
		postArgs.put("code", code);

		
		  JSONObject response = (new PostMethod() .setPostParameters(postArgs)
		  .setMethodURI(UriFactory.Auth.GET_ACCESS_TOKEN) ).call().getJSON();
		 
		try {
			setAccessToken(new
					AccessToken(response.getString("access_token")));
			 setSessionUser(new User(response.getJSONObject("user"),
					 getAccessToken().getTokenString()));
		} catch (Exception e) {
			throw new InstagramException("JSON parsing error");
		}
		return getAccessToken();
	}

	public AccessToken getAccessToken() throws InstagramException {
		if (accessToken == null)
			throw new InstagramException(
					"Token has not been fetched, please call build(String code) "
							+ "before calling getAccessToken()");
		else
			return accessToken;
	}

	protected void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}

	public User getAuthenticatedUser() throws InstagramException {
		if (accessToken == null)
			throw new InstagramException(
					"No user has been authenticated yet");
		else
			return sessionUser;
	}

	protected void setSessionUser(User sessionUser) {
		this.sessionUser = sessionUser;
	}

	protected String getClientSecret() {
		return clientSecret;
	}

	public InstagramAuthentication setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
		return this;
	}

}
