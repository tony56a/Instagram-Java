package com.instagram.io;

public class RequestDispatcher {
	
	public static final int MAX_NUM_THREADS = 4;
	
	public static int activeThreads = 0;
	
	public static RequestResponse dispatch(APIMethod request) {
		return null;
	}
	
	public static boolean asyncDispatch(AsyncAPIMethod request) {
		(new Thread(request)).start();
		return true;
	}	
	
	public static void informComplete(AsyncAPIMethod request) {
		request.complete();
	}

	public static void informError(AsyncAPIMethod request) {
		request.error();
	}
}