package com;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.sola.instagram.*;
import com.sola.instagram.auth.AccessToken;
import com.sola.instagram.auth.InstagramAuthentication;
import com.sola.instagram.exception.InstagramException;
import com.sola.instagram.model.*;

public class Main {

	public static void main(String[] args) {

		try {
			 InstagramAuthentication auth =  new InstagramAuthentication();
			 String x = auth.setRedirectUri("https://github.com/sola92")
			 	 .setClientSecret("dd859148a795427a98f1d9da817a6902")
			 	 .setClientId("2b2ca2d2d162478b97f7626114d294e9")
			 	 .getAuthorizationUri();
			 
			InstagramSession session = new InstagramSession(new AccessToken(
					com.sola.instagram.test.Constants.ACCESS_TOKEN));
			System.out.println(session.searchUsersByName("instagram").get(0)
					.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}