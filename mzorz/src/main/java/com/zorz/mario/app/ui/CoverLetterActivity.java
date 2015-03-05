package com.zorz.mario.app.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.zorz.mario.Application;
import com.zorz.mario.R;
import com.zorz.mario.api.Event;
import com.zorz.mario.api.Service;

public class CoverLetterActivity extends BaseActivity {
	
	private static String TAG = "Zorz";
    private WebView myWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cover_letter);
        setActionBarIcon(R.drawable.ic_ab_drawer);
        myWebView = (WebView) findViewById(R.id.webview);
		//setActionBarTitle(getString(R.string.mn_cover));
		//initializeSlidingMenu();

        setDrawerSelectedOption(R.id.btnCoverLetter);

	}

	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	 }	
	
	@Override
	protected void onStart() {
		super.onStart();

        Application.getEventBus().register(this);
        Service.getInstance().getCoverLetter();
	}

    @Override
    protected void onStop() {
        Application.getEventBus().unregister(this);
        super.onStop();
    }

    public void onEvent(Event.CoverLetterLoadStartEvent event) {
        showProgressDialog(getString(R.string.server_login), false);
    }

    public void onEvent(Event.CoverLetterLoadCompleteEvent event) {
        dismissProgressDialog();
        if (event.object == null || event.object.data == null)
            //Toast.makeText(this, R.string.error_no_items_found, Toast.LENGTH_SHORT).show();
            showError(this, getResources().getString(R.string.error_no_items_found), false, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        else{
            //myWebView.loadData(event.object.data,"text/html","utf-8");
            myWebView.loadDataWithBaseURL("file:///asset/", event.object.data,"text/html","utf-8", null);
        }
    }

    public void onEvent(Event.CoverLetterLoadFailEvent event) {
        dismissProgressDialog();
        showError();
    }

}
