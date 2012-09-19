package com;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.instagram.*;
import com.instagram.auth.AccessToken;
import com.instagram.exception.InstagramException;
import com.instagram.io.RequestListener;
import com.instagram.model.*;

public class Main {

	public static void main(String[] args) {

		try {

			/*
			 * Instagram instagram = new Instagram()
			 * .setClientId("2b2ca2d2d162478b97f7626114d294e9")
			 * .setRedirectUri("https://github.com/sola92")
			 * .setClientSecret("dd859148a795427a98f1d9da817a6902")
			 * .build("c98d75ffcd7346dcaa6818dd555d62a8");
			 * System.out.println("Token is => " +
			 * instagram.getAccessToken().getTokenString());
			 */

			InstagramSession session = new InstagramSession(new AccessToken(
					com.instagram.test.Constants.ACCESS_TOKEN));

			session.asynchGetUserById(
					com.instagram.test.Constants.VALID_USER_ID,
					new RequestListener() {
						public void onComplete(Object result) {
							System.out.println("request 1 complete "
									+ ((User) result).getUserName());
						}
					});
			System.out.println(session.searchUsersByName("instagram").get(0)
					.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}