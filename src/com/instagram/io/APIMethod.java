package com.instagram.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONObject;

import com.instagram.exception.InstagramException;

public abstract class APIMethod {
	String methodUri;
	String type;
	String accessToken;

	abstract protected InputStream performRequest();

	public APIMethod() {}

	public RequestResponse call() throws InstagramException {
		String line = "", chunk;
		BufferedReader rd = new BufferedReader(new InputStreamReader(performRequest()));
		try {
			while ((chunk = rd.readLine()) != null) 
				line += chunk;
				//System.out.println(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RequestResponse(line);
	}
	
	/*
	System.out.println(line);
	obj = new JSONObject(new JSONTokener(line));
	if ((obj.has("meta") && obj.getJSONObject("meta").getInt("code") != 200)
			|| (obj.has("code") && obj.getInt("code") != 200)) {
		throw new InstagramException("\nUri => " + getMethodUri()
				+ "\nResponse => " + obj.toString());
	}
throw new InstagramException("JSON Parsing Error\nUri => "
					+ getMethodUri() + "\nResponse => " + obj.toString());
	*/
	public String getType() {
		return type;
	}

	public String getMethodUri() {
		return methodUri;
	}

	public APIMethod setMethodURI(String methodURI) {
		this.methodUri = methodURI;
		return this;
	}
}