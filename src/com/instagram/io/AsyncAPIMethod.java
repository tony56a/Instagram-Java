package com.instagram.io;


public abstract class AsyncAPIMethod extends APIMethod implements Runnable {
	
	RequestListener listener;
	RequestResponse response;
	
	public RequestListener getListener() {
		return this.listener;
	}
	
	public void complete() {
		getListener().onComplete(response);
	}

	public void error() {
		getListener().onError(response);
	}

	public void run() {
		try {
			response = call();
			RequestDispatcher.informComplete(this);
		} catch(Exception e) {
			RequestDispatcher.informError(this);
		}
	}

	public void setCallback(RequestListener listener) {
		this.listener = listener;
	}
}
