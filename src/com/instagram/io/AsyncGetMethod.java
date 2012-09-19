package com.instagram.io;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class AsyncGetMethod extends AsyncAPIMethod {
	DefaultHttpClient client;
	
	public AsyncGetMethod(String uri, RequestListener listener) {
		this.setMethodURI(uri);
		this.setCallback(listener);
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
