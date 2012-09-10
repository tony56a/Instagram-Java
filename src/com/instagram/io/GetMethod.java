package com.instagram.io;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.HttpResponse;

public class GetMethod extends APIMethod {
	DefaultHttpClient client;
	
	public GetMethod() {
		super();
		this.type = "GET";
		this.client = new DefaultHttpClient();
	}
	
	@Override
	protected BufferedReader performRequest() {
		HttpResponse response;
		BufferedReader rd = null;
		HttpGet post = new HttpGet(this.methodUri);
		try {
			response = client.execute(post);
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return rd;
	}
}
