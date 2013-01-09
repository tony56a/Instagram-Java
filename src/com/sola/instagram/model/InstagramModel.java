package com.sola.instagram.model;

import org.json.JSONObject;

public abstract class InstagramModel {
	String accessToken;
	JSONObject jsonRepresentation;


	public InstagramModel(JSONObject obj, String accessToken) {
		setJsonRepresentation(obj);
		setAccessToken(accessToken);
	}
	
	public String toString() {
		return getJsonRepresentation().toString();
	}
	
	protected void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	protected String getAccessToken() {
		return this.accessToken;
	}
	
	private JSONObject getJsonRepresentation() {
		return jsonRepresentation;
	}

	private void setJsonRepresentation(JSONObject jsonRepresentation) {
		this.jsonRepresentation = jsonRepresentation;
	}
}
