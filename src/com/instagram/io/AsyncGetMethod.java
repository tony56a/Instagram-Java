package com.instagram.io;
import java.io.InputStream;

public class AsyncGetMethod extends AsyncAPIMethod {
	GetMethod getMethod;
	
	public AsyncGetMethod(String uri, RequestListener listener) {
		getMethod = new GetMethod();
		getMethod.setMethodURI(uri);
		setCallback(listener);
	}

	@Override
	protected InputStream performRequest() {
		return getMethod.performRequest();
	}
	
}
