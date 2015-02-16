package com.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.zorz.mario.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	
	public static String mSharedPreferencesName = "sPreferences";

	public static String toCamelCase(String s) {
		final StringTokenizer st = new StringTokenizer(s.toLowerCase(Locale.US), " ", true);
		final StringBuilder sb = new StringBuilder();

		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			token = String.format("%s%s", Character.toUpperCase(token.charAt(0)), token.substring(1));
			sb.append(token);
		}
		return sb.toString();
	}

	public static String getFirstLineFromHtml(String html) {
		Pattern patter = Pattern.compile("<br\\s?/>");
		Matcher matcher = patter.matcher(html);
		if (matcher.find()) {
			return html.substring(0, matcher.start());
		}
		return new String();
	}

	public static boolean isMyServiceRunning(Context context, Class<?> ServiceClass) {
		ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (ServiceClass.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}

	public static SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences(mSharedPreferencesName, 0);
	}

	public static SharedPreferences.Editor getPreferencesEditor(Context context) {
		return getSharedPreferences(context).edit();
	}



	public static boolean isNumericAndZero(String str, Context context, String msg){
 		try {
			String formattedMsg = str.replace(",", ".");

			double number = Double.parseDouble(formattedMsg);
			if (!(number > 0)) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(context);
				dialog.setMessage(msg);
				//dialog.setPositiveButton("Aceptar", null);
				dialog.setPositiveButton(context.getString(R.string.aceptar_lower), null);
				dialog.show();
				return false;
			}
			return true;
		} catch (NumberFormatException nfe) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(context);
			//dialog.setMessage("El valor ingresado no es un valor numerico ");
			dialog.setMessage(context.getString(R.string.value_not_numeric));
			//dialog.setPositiveButton("Aceptar", null);
			dialog.setPositiveButton(context.getString(R.string.aceptar_lower), null);
			dialog.show();
 		}
		return false;
	
	}
	
	public static boolean isNullField(Context context, EditText etext, String msg){
		if (etext.getText().length() == 0) {
			//showAlertWithDismissButton(context, msg, "Aceptar");
			showAlertWithDismissButton(context, msg, context.getString(R.string.aceptar_lower));
			setFocusToView(etext, context);
			return true;
		}
		return false;
	}
	
	public static void showAlert(Context context, String msg){
		AlertDialog.Builder dialog = getAlertDialog(context, msg);
 		dialog.show();
  	}
	
	public static void showAlertWithDismissButton(Context context, String msg, String buttonMessage){
		AlertDialog.Builder dialog = getAlertDialog(context, msg);
 		dialog.setPositiveButton(buttonMessage, null);
 		dialog.show();
  	}

	private static AlertDialog.Builder getAlertDialog(Context context, String msg){
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setMessage(msg);
 		return dialog;
	}
	
	public static void setFocusToView(final View v, final Context context){
		v.post(new Runnable() {
		    public void run() {
		        v.requestFocusFromTouch();
		        InputMethodManager lManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); 
		        lManager.showSoftInput(v, 0);
		    }
		});
	}


	public static String getString(Context mContext, int resID){
		return mContext.getResources().getString(resID);
 	}
	

    public static String getStringFromJSONObject(String a_key, JSONObject item){
    	String strToReturn = new String();
    	
    	try {
    		if (item.has(a_key)){
    			strToReturn =  item.getString(a_key);
    		}
    	} catch (JSONException ex){
    		ex.printStackTrace();
    	}
    	
    	return strToReturn;
    }
	
	
}
