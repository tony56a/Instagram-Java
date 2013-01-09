package com.sola.instagram.io;
import org.apache.http.client.methods.HttpGet;
import java.io.InputStream;


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
	protected InputStream performRequest() {
		HttpResponse response;
		InputStream stream = null;
		HttpGet post = new HttpGet(this.methodUri);
		try {
			response = client.execute(post);
			stream = response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return stream;
	}
}
