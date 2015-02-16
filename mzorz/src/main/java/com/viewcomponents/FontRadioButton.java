package com.viewcomponents;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RadioButton;

public class FontRadioButton extends RadioButton {
	public FontRadioButton(Context context) {
		super(context);
		init();
	}

	public FontRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FontRadioButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void setTextBold(CharSequence text) {
		this.setText(Html.fromHtml("<b>" + text + "</b>"));
	}

	protected void init() {
		try {
			//Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Lato-Regular.ttf");
			Typeface tf = FontAssetManager.get().createAsset("Roboto-Regular.ttf", getContext());
			setTypeface(tf);
 		} catch (Exception e) {
 			Log.e("FontTextView.init()", "Font no disponible");
		}
		setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

 	}

}
