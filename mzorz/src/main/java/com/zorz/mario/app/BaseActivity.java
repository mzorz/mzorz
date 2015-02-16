package com.zorz.mario.app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewcomponents.FontTextView;
import com.viewcomponents.SquareImageView;
import com.zorz.mario.R;


public class BaseActivity extends FragmentActivity implements OnCancelListener {

	protected InputMethodManager imm;
	protected Resources mResources;
	public SlidingMenu slidingMenu;
	protected int CENTER = 0;
	protected int LEFT = 1;
	protected int RIGHT = 2;

    private ProgressDialog progressDialog;


    @Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		super.setContentView(R.layout.base_layout);
		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mResources = getResources();

        progressDialog = new ProgressDialog(this);

	}

	//FLURRY STUFF
	@Override
	protected void onStart()
	{
		super.onStart();		
	}
 
	@Override
	protected void onStop()
	{
		super.onStop();		
	}
	
	@Override
	public void setContentView(int layoutId) {
		View content = getLayoutInflater().inflate(layoutId, null);

		((FrameLayout) findViewById(R.id.container_base)).addView(content);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	protected FragmentActivity getContext() {
		return BaseActivity.this;
	}

	public void handleOnCancel(DialogInterface dialog) {
		onCancel(dialog);
	}

	protected void initializeSlidingMenu() {
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		//slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		slidingMenu.setFadeDegree(0.35f);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		slidingMenu.setMenu(R.layout.menu_configurations);

		SquareImageView menuButton = new SquareImageView(getContext());
		menuButton.setBackgroundResource(R.drawable.menu);
		//menuButton.setId(R.integer.id_menuButton);
		menuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});
		addLeftViewToActionBar(menuButton);
	}

	@Override
	public void startActivity(Intent intent) {
		closeKeyboardIfIsOpen();
		super.startActivity(intent);
	}

	@Override
	public void finish() {
		closeKeyboardIfIsOpen();
		super.finish();
	}

	protected void closeKeyboardIfIsOpen() {
 		if (imm != null) {
 			imm.hideSoftInputFromWindow(this.findViewById(android.R.id.content).getWindowToken(), 0);
		}
	}

	protected void setActionBarTitle(CharSequence title) {
		FontTextView actionbarTitle = (FontTextView) findViewById(R.id.tv_title);
		actionbarTitle.setText(title);
		actionbarTitle.setVisibility(View.VISIBLE);
	}

	protected void addRightViewToActionBar(View view) {
		((LinearLayout) findViewById(R.id.container_actionbar_right))
				.addView(view);
	}

	protected void addLeftViewToActionBar(View view) {
		((RelativeLayout) findViewById(R.id.container_actionbar_left))
				.addView(view);
	}

	// ==== DIALOGS ===== //

    public void showProgressDialog(String strToShow, boolean cancelable) {
        progressDialog.setMessage(strToShow);
        progressDialog.show();
        progressDialog.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface arg0) {
            }
        });
        progressDialog.setCancelable(cancelable);
    }

    public void showProgressDialog(String strToShow, OnCancelListener listenerCancel) {
        progressDialog.setMessage(strToShow);
        progressDialog.show();
        if (listenerCancel != null){
            progressDialog.setOnCancelListener(listenerCancel);
        }
        progressDialog.setCancelable(true);
    }

    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

    public void setProgressDialogMessage(String message){
        progressDialog.setMessage(message);
    }

    public ProgressDialog getProgressDialog(){
        return progressDialog;
    }

	@Override
	public void onCancel(DialogInterface dialog) {
		// do nothing
	}
	
	protected void showError(){
		AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
		alert.setIcon(R.drawable.ic_launcher);
		alert.setMessage(getString(R.string.error_occurred_server));
		alert.setPositiveButton(getString(R.string.accept_lower), new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//finish();
				//dismiss();
			}
		});
		//alert.setNegativeButton(getString(R.string.cancel_lower), null);
		alert.show();
	}
	
	@Override
	public void onBackPressed() {
		if (slidingMenu != null){
			if (slidingMenu.isMenuShowing()) {
				slidingMenu.toggle();
				return;
			}
		}
		super.onBackPressed();
	}
	

	

}