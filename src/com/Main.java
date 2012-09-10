package com;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.instagram.*;
import com.instagram.auth.AccessToken;
import com.instagram.exception.InstagramException;
import com.instagram.model.*;


public class Main {
	
	public static void main(String[] args) {
		
		try {
			
			Instagram instagram = new Instagram()
								  .setClientId("2b2ca2d2d162478b97f7626114d294e9")
								  .setRedirectUri("https://github.com/sola92")
								  .setClientSecret("dd859148a795427a98f1d9da817a6902")
								  .build("a3c0484e2315448e85f37f2d088c4a7d");

			InstagramSession session = new InstagramSession(instagram.getAccessToken());
			for(Tag tag : session.searchTags("yolo")) {
				System.out.println(tag.getName() + ", count: " + tag.getMediaCount());
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}