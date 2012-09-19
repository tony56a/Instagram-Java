package com.instagram.io;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class PostMethod extends APIMethod {
	Map<String, Object> postParameters;
	DefaultHttpClient client;
	
	public PostMethod() {
		super();
		this.type = "POST";
		this.client = new DefaultHttpClient();
	}
	
	
	@Override
	protected InputStream performRequest() {
		HttpResponse response;
		BufferedReader rd = null;
		HttpPost post = new HttpPost(this.methodUri);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		for (Map.Entry<String, Object> arg : postParameters.entrySet()) {
			nameValuePairs.add(new BasicNameValuePair(arg.getKey(), arg.getValue().toString()));
		}
		InputStream stream = null;
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = client.execute(post);
			stream = response.getEntity().getContent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}

	
	public Map<String, Object> getPostParameters() {
		return postParameters;
	}


	public PostMethod setPostParameters(Map<String, Object> postParameters) {
		this.postParameters = postParameters;
		return this;
	}

}
