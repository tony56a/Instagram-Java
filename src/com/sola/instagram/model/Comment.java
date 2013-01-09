package com.sola.instagram.model;

import org.json.JSONObject;
import org.json.JSONException;

import com.sola.instagram.exception.InstagramException;

public class Comment extends InstagramModel {
	String createdTimestamp;
	String text;
	User sender;
	String id;

	public Comment(JSONObject obj, String accessToken)
			throws InstagramException {
		super(obj, accessToken);
		try {
			setCreatedTimestamp(obj.getString("created_time"));
			setText(obj.getString("text"));
			setId(obj.getString("id"));
			setSender((new User(obj.getJSONObject("from"), accessToken)));
		} catch (JSONException e) {
			throw new InstagramException("JSON parsing error");
		}
	}

	public String getCreatedTimestamp() {
		return createdTimestamp;
	}

	protected void setCreatedTimestamp(String createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public String getText() {
		return text;
	}

	protected void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	protected void setSender(User sender) {
		this.sender = sender;
	}

}