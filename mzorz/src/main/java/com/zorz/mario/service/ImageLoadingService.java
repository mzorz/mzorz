package com.zorz.mario.service;

public interface ImageLoadingService {
	
	/**
	 * Download and asing image of movie
	 * @param url Url of the movie image
	 * @param callback TODO
	 */
	public void request(String url, ImageLoadingCallback callback);

}
