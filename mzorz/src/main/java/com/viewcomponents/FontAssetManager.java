package com.viewcomponents;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.HashMap;

/* this class is created to hold typeface fonts only once in memory, as otherwise it may leak */
public class FontAssetManager  {

	private static FontAssetManager _instance;
	private HashMap<String, Typeface> hshMap = new HashMap<String, Typeface>();
	
	/**
	 * Constructor.
	 */
	public FontAssetManager() {
		_instance = this;
	}

	public static FontAssetManager get() {
		if (_instance == null)
			_instance = new FontAssetManager();
		return _instance;
	}
	
	public Typeface createAsset(String a_assetName, Context context){
		Typeface tf = null;
		
		//first try to get it from the hashmap, if not there create it and put it into the hashmap
		tf = hshMap.get(a_assetName);
		if (tf != null)
			return tf;
		
		try {
				//tf = Typeface.createFromAsset(context.getAssets(), "MyriadPro-BoldCond.otf");
				tf = Typeface.createFromAsset(context.getAssets(), a_assetName);
				hshMap.put(a_assetName, tf);
 			} catch (Exception ex) {
	 			ex.printStackTrace();
	 			Log.e("FontTextView.init()", "Font no disponible");
 			}
	
		return tf;
		
	}
	
}
