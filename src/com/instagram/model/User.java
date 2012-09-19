package com.instagram.model;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONException;	
import com.instagram.io.GetMethod;
import com.instagram.io.UriFactory;
import com.instagram.util.UriConstructor;
import com.instagram.exception.InstagramException;


public class User extends InstagramModel {
	int id;
	String userName;
	String fullName;
	String profilePictureURI;
	String bio;
	String website;
	int mediaCount = -1;
	int followerCount = -1;
	int followingCount = -1;

	public User(JSONObject obj, String accessToken) throws InstagramException {
		super(obj, accessToken);
		try{
			setId(obj.getInt("id"));
			setUserName(obj.getString("username"));
			setFullName(obj.getString("full_name"));
			setProfilePictureURI(obj.getString("profile_picture"));
			
			setWebsite(obj.optString("website"));
			setBio(obj.optString("bio"));
			
			if(obj.has("counts")) {
				JSONObject counts = obj.getJSONObject("counts");
				setFollowerCount(counts.getInt("followed_by"));
				setFollowingCount(counts.getInt("follows"));
				setMediaCount(counts.getInt("media"));			
			} else {
				setFollowerCount(-1);
				setFollowingCount(-1);
				setMediaCount(-1);		
			}
		} catch(JSONException e) {
			throw new InstagramException("JSON parsing error");
		}
	}

	public int getId() {
		return id;
	}
	
	protected void setId(int id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	protected void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	protected void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getProfilePictureURI() {
		return profilePictureURI;
	}
	
	protected void setProfilePictureURI(String profilePictureURI) {
		this.profilePictureURI = profilePictureURI;
	}
	
	public String getBio() throws JSONException, InstagramException {
		if(website == null) {
			refreshObject();
		}
		return bio;
	}
	
	protected void setBio(String bio) throws JSONException {
		this.bio = bio;
	}
	
	public String getWebsite() throws JSONException, InstagramException {
		if(website == null) {
			refreshObject();
		}
		return website;
	}
	
	protected void setWebsite(String website) {
		this.website = website;
	}
	
	public int getMediaCount() throws InstagramException {
		if(this.followingCount == -1) 
			refreshObject();
		return mediaCount;
	}
	
	protected void setMediaCount(int mediaCount) {
		this.mediaCount = mediaCount;
	}

	public int getFollowerCount() throws InstagramException {
		if(this.followerCount == -1) 
			refreshObject();
		return followerCount;
	}
	
	protected void setFollowerCount(int followerCount) {
		this.followerCount = followerCount;
	}
	
	public int getFollowingCount() throws InstagramException {
		if(this.followingCount == -1) 
			refreshObject();
		return followingCount;
	}
	
	protected void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}
	
	private void refreshObject() throws InstagramException {
		try {
			UriConstructor uriConstructor = new UriConstructor(getAccessToken());
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", getId());
			
			JSONObject userObject = (new GetMethod()
									.setMethodURI(uriConstructor.constructUri(UriFactory.Users.GET_DATA, map, true))
									).call().getJSON();
			
			if(userObject.has("data")) {
				JSONObject counts = userObject.getJSONObject("data")
									.getJSONObject("counts");
				setFollowerCount(counts.getInt("followed_by"));
				setFollowingCount(counts.getInt("follows"));
				setMediaCount(counts.getInt("media"));		
			}
		} catch(JSONException e) {
			throw new InstagramException("JSON parsing error");
		} catch(InstagramException e) {
			throw new InstagramException(getUserName() + "'s data cannot be accessed. "
					+ "This user may have deleted their account");
		}
	}
}
