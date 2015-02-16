package com.zorz.mario.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.zorz.mario.io.stream.FlushedInputStream;

import java.io.InputStream;
import java.net.URL;

public class ImageLoadingServiceImpl implements ImageLoadingService {

	@Override
	public void request(final String url, final ImageLoadingCallback callback) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				InputStream content = null;
				try {
					URL urlImage = new URL(url);
					content = (InputStream) urlImage.getContent();
				} catch (Exception e) {
					callback.onError(e);
					return;
				}
				// TODO : how do we handle status codes such as 404? 301?
				Bitmap b = BitmapFactory.decodeStream(new FlushedInputStream(content));
				callback.onSuccess(b);
			}
		}).start();
	}
}
