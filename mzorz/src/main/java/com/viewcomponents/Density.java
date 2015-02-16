package com.viewcomponents;

import android.content.Context;

public class Density {
	
	public static Context context;

	public static int convertPx(int pixels) {
		float d = context.getResources().getDisplayMetrics().density;
		return (int) (d * pixels);
	}
}
