package com.viewcomponents;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.CheckBox;

public class FontCheckBox extends CheckBox {

	public FontCheckBox(Context context) {
		super(context);
		init();
	}

	public FontCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FontCheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		try {
			//Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Lato-Regular.ttf");
			Typeface tf = FontAssetManager.get().createAsset("Roboto-Regular.ttf", getContext());
			setTypeface(tf);
		} catch (Exception e) {
			Log.e("FontTextView.init()", "Font no disponible");
		}
//		setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
 	}

}
