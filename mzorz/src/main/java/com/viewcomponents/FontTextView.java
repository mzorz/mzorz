package com.viewcomponents;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

public class FontTextView extends TextView {

	public FontTextView(Context context) {
		super(context);
		init();
	}

	public FontTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public FontTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
 	public void setTextBold(CharSequence text) {
 		this.setText(Html.fromHtml("<b>" + text + "</b>"));
	}

	protected void init() {
		try {
			/*int iStyle = this.getTypeface().getStyle();
			if (iStyle == Typeface.BOLD)*/
			Typeface tfactual = this.getTypeface();
			if (tfactual != null){
				if (tfactual.isBold()){
					Typeface tf = FontAssetManager.get().createAsset("Roboto-Bold.ttf", getContext());
					setTypeface(tf);
				} else {
					Typeface tf = FontAssetManager.get().createAsset("Roboto-Regular.ttf", getContext());
					setTypeface(tf);
				}
			} else {
				Typeface tf = FontAssetManager.get().createAsset("Roboto-Regular.ttf", getContext());
 				setTypeface(tf);
			}
 		} catch (Exception e) {
 			e.printStackTrace();
 			Log.e("FontTextView.init()", "Font not available");
 			
 			//at least, try to generate the regular typeface
 			try
 			{
				Typeface tf = FontAssetManager.get().createAsset("Roboto-Regular.ttf", getContext());
 				setTypeface(tf);
 			} catch (Exception ex) {
	 			ex.printStackTrace();
	 			Log.e("FontTextView.init()", "Font not available");
 			}
 			
		}

 	}
}
