package com.viewcomponents;


import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

public class DatePickerButton extends Button {

	public DatePickerButton(Context context) {
		super(context);
		init();
	}

	public DatePickerButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DatePickerButton(Context context, AttributeSet attrs, int defStyle) {
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
 	}
	
	@Override
	public void setText(CharSequence text, BufferType type) {
		/*if (text.length() == 0) {
			this.setBackgroundResource(R.drawable.selector_date_button);
		} else {
			this.setBackgroundResource(R.drawable.selector_rounded_hour_date_button);
		}*/
 		super.setText(text, type);
	}
	
}
