package com.zorz.mario.service;

import android.graphics.Bitmap;

public interface ImageLoadingCallback {
	
	/**
	 * TODO: Tradusir
	 * Accion que se realisa cuando la operacion es correcta
	 */
	public void onSuccess(Bitmap b);
	
	/**
	 * TODO: Tradusir
	 * Accion que se realisa cuando la operacion falla
	 */
	public void onError(Throwable t);

}
