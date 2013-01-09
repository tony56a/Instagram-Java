package com.sola.instagram.model;
import org.json.JSONException;
import org.json.JSONObject;

import com.sola.instagram.exception.InstagramException;

public class Relationship extends InstagramModel {
	
	public enum OutgoingStatus {
		FOLLOWS, REQUESTED, NONE
	}
	
	public static enum IncomingStatus {
		FOLLOWED_BY, REQUESTED_BY, BLOCKED_BY_YOU, NONE
	}
	
	public static enum Action {
		FOLLOW, UNFOLLOW, BLOCK, UNBLOCK, APPROVE, DENY
	}
	
	OutgoingStatus outgoingStatus;
	IncomingStatus incomingStatus;
	boolean targetUserIsPrivate;

	public Relationship(JSONObject obj, String accessToken) throws InstagramException {
		super(obj, accessToken);
		try {
			setOutgoingStatus(obj.getString("outgoing_status"));
			setIncomingStatus(obj.getString("incoming_status"));
			this.targetUserIsPrivate = obj.getBoolean("target_user_is_private");
			
		} catch(JSONException e) {
			throw new InstagramException("JSON parsing error");
		}
	}
	
	public OutgoingStatus getOutgoingStatus() {
		return outgoingStatus;
	}
	
	private void setOutgoingStatus(String outgoingStatus) {

		if(outgoingStatus.equals("follows")) {
			this.outgoingStatus = OutgoingStatus.FOLLOWS;
		}
		else if(outgoingStatus.equals("requested")) {
			this.outgoingStatus = OutgoingStatus.REQUESTED;
		}
		else if(outgoingStatus.equals("none")) {
			this.outgoingStatus = OutgoingStatus.NONE;
		}
	}

	public IncomingStatus getIncomingStatus() {
		return incomingStatus;
	}

	public boolean targetUserIsPrivate() {
		return this.targetUserIsPrivate;
	}

	private void setIncomingStatus(String incomingStatus) {
		if(incomingStatus.equals("followed_by")) {
			this.incomingStatus = IncomingStatus.FOLLOWED_BY;
		}
		else if(incomingStatus.equals("requested_by"))  {
			this.incomingStatus = IncomingStatus.REQUESTED_BY;
		}
		else if(incomingStatus.equals("blocked_by_you")) {
			this.incomingStatus = IncomingStatus.BLOCKED_BY_YOU;
		} 
		else if(incomingStatus.equals("none")) {
			this.incomingStatus = IncomingStatus.NONE;
		}
	}
}

