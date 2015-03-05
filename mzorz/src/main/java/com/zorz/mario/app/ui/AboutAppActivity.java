package com.zorz.mario.app.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.zorz.mario.Application;
import com.zorz.mario.R;
import com.zorz.mario.api.Event;
import com.zorz.mario.api.Service;

public class AboutAppActivity extends BaseActivity {
	
	private static String TAG = "Zorz";
    private WebView myWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover_letter);
        myWebView = (WebView) findViewById(R.id.webview);
		setActionBarTitle(getString(R.string.mn_aboutapp));
        setActionBarIcon(R.drawable.ic_ab_drawer);
        setDrawerSelectedOption(R.id.btnAboutApp);
	}

	
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	 }	
	
	@Override
	protected void onStart() {
		super.onStart();

        Application.getEventBus().register(this);
        Service.getInstance().getAboutApp();
	}

    @Override
    protected void onStop() {
        Application.getEventBus().unregister(this);
        super.onStop();
    }

    public void onEvent(Event.AboutAppLoadStartEvent event) {
        showProgressDialog(getString(R.string.server_login), false);
    }

    public void onEvent(Event.AboutAppLoadCompleteEvent event) {
        dismissProgressDialog();
        if (event.object == null || event.object.data == null)
            //Toast.makeText(this, R.string.error_no_items_found, Toast.LENGTH_SHORT).show();
            showError(this, getResources().getString(R.string.error_no_items_found), false, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

        else{
            myWebView.loadData(event.object.data,"text/html","utf-8");
        }
    }

    public void onEvent(Event.AboutAppLoadFailEvent event) {
        dismissProgressDialog();
        showError();
    }

}
