package com.zorz.mario.app.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zorz.mario.R;


public class BaseActivity extends ActionBarActivity implements OnCancelListener {

    protected Toolbar toolbar;
	protected InputMethodManager imm;
	protected Resources mResources;

    private ProgressDialog progressDialog;
    private Handler mHandler;

    private DrawerLayout drawer;

    // delay to launch nav drawer item, to allow close animation to play
    private static final int NAVDRAWER_LAUNCH_DELAY = 250;

    @Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		super.setContentView(R.layout.base_layoutv21);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

		imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		mResources = getResources();

        progressDialog = new ProgressDialog(this);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(Gravity.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    protected ActionBarActivity getContext() {
		return BaseActivity.this;
	}

	public void handleOnCancel(DialogInterface dialog) {
		onCancel(dialog);
	}

    protected void setActionBarIcon(int iconRes) {
        toolbar.setNavigationIcon(iconRes);
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
//		FontTextView actionbarTitle = (FontTextView) findViewById(R.id.tv_title);
//		actionbarTitle.setText(title);
//		actionbarTitle.setVisibility(View.VISIBLE);
        toolbar.setTitle(title);
	}

	protected void addRightViewToActionBar(View view) {
		((LinearLayout) findViewById(R.id.container_actionbar_right))
				.addView(view);
	}

	protected void addLeftViewToActionBar(View view) {
		((RelativeLayout) findViewById(R.id.container_actionbar_left))
				.addView(view);
	}

    protected void setActionBarBackgroundColor(int color){
        ((RelativeLayout) findViewById(R.id.actionBarbkg)).setBackgroundColor(color);
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
			}
		});
		alert.show();
	}
	
	@Override
	public void onBackPressed() {
        if (isTaskRoot()){
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            alert.setIcon(R.drawable.ic_launcher);
            alert.setMessage(getString(R.string.exit_app));
            alert.setPositiveButton(getString(R.string.accept_lower), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.setNegativeButton(getString(R.string.cancel_lower), null);
            alert.show();
        } else {
            super.onBackPressed();
        }

	}


    private void goToNavDrawerItem(int itemId){

        Class<?> activityToGo;

        switch(itemId)
        {
            case R.id.btnCoverLetter:
                activityToGo = CoverLetterActivity.class;
            break;
            case R.id.btnAndroidProjects:
                activityToGo = PreviousAndroidWorkActivity.class;
                break;
            case R.id.btnOtherProjects:
                activityToGo = PreviousOtherWorkActivity.class;
                break;
            case R.id.btnAboutApp:
                activityToGo = AboutAppActivity.class;
                break;
            case R.id.btnAboutMe:
                activityToGo = AboutMeActivity.class;
                break;
            case R.id.btnWantToWorkOn:
                activityToGo = WantToWorkOnActivity.class;
                break;

            default:
                activityToGo = CoverLetterActivity.class; //default CoverLetter
                break;
        }

        Intent i = new Intent(this, activityToGo);
        startActivity(i);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    public void goToAppSection(final int itemId){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNavDrawerItem(itemId);
            }
        }, NAVDRAWER_LAUNCH_DELAY);

    }

}