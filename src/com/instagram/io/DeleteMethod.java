package com.instagram.io;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.HttpResponse;

public class DeleteMethod extends APIMethod {
	DefaultHttpClient client;
	
	public DeleteMethod() {
		super();
		this.type = "GET";
		this.client = new DefaultHttpClient();
	}
	
	@Override
	protected InputStream performRequest() {
		HttpResponse response;
		BufferedReader rd = null;
		HttpDelete post = new HttpDelete(this.methodUri);
		InputStream stream = null;
		try {
			response = client.execute(post);
			stream = response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return stream;
	}

}
