package com.zorz.mario.app;

import android.app.Application;

public class ZorzMario extends Application {

	private static ZorzMario _instance = new ZorzMario();
	
	/**
	 * Constructor.
	 */
	public ZorzMario() {
		super();
		_instance = this;
	}

	public static ZorzMario get() {
		return _instance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
}
