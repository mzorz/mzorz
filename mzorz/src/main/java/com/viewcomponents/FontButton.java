package com.viewcomponents;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

public class FontButton extends Button {

	public FontButton(Context context) {
		super(context);
		init();
	}

	public FontButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FontButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void setTextBold(CharSequence text) {
		this.setText(Html.fromHtml("<b>" + text + "</b>"));
	}

	protected void init() {
		try {
			/*Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
					"MyriadPro-Regular.otf");*/
			Typeface tf = FontAssetManager.get().createAsset("Roboto-Regular.ttf", getContext());
			setTypeface(tf);
		} catch (Exception e) {
			Log.e("FontTextView.init()", "Font no disponible");
		}
	}

}
