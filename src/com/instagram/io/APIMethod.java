package com.instagram.io;	
import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONObject;

import com.instagram.exception.InstagramException;


public abstract class APIMethod {
	String methodUri;
	String type;
	String accessToken;

	
	abstract protected BufferedReader performRequest();
	
	public APIMethod() {}

	public JSONObject call() throws InstagramException {
		JSONObject obj = null;
		String line = "", chunk;
		BufferedReader rd = performRequest();
		try {
			while ((chunk = rd.readLine()) != null) {
				line += chunk;
			}
			System.out.println(line);
			obj = new JSONObject(new JSONTokener(line));
			if((obj.has("meta") && 
				obj.getJSONObject("meta").getInt("code") != 200) ||
				(obj.has("code") && obj.getInt("code") != 200)
			  ) {
				throw new InstagramException("\nUri => " + getMethodUri() 
						+ "\nResponse => " + obj.toString());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		catch (JSONException e) {
			throw new InstagramException("JSON Parsing Error\nUri => " + getMethodUri() 
					+ "\nResponse => " + obj.toString());
		}
		return obj;
	}
	
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