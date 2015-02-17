package com.zorz.mario.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.utils.database.DataBaseManager;
import com.viewcomponents.SquareImageView;
import com.zorz.mario.ConstantsMzorz;
import com.zorz.mario.R;
import com.zorz.mario.model.PhotoItem;

public class ItemDetailActivity extends BaseActivity {
	
	private static String TAG = "Zorz";
    private String title;
    private String description;
    private String actionbartitle;
    private PhotoItem photos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_news);

        title = getIntent().getStringExtra(ConstantsMzorz.MZORZ_ITEM_TITLE);
        description = getIntent().getStringExtra(ConstantsMzorz.MZORZ_ITEM_DESC);
        actionbartitle = getIntent().getStringExtra(ConstantsMzorz.MZORZ_ITEM_ACTIONBARTITLE);
        photos = getIntent().getParcelableExtra(ConstantsMzorz.MZORZ_ITEM_PHOTO);

		setActionBarTitle(actionbartitle);
		//initializeSlidingMenu();
		
		DataBaseManager.initializeDB(this);
		
		initializeButtons();

	}
	
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 //overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
		 overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	 }	

	@Override
	protected void onStart() {
		super.onStart();
		
        if (photos != null){
            Picasso.with(this)
                    .load(photos.url)
                    .placeholder(R.drawable.mz_logo_splash_ic)
                    .error(R.drawable.mz_logo_splash_ic)
                    .into((ImageView)findViewById(R.id.newspic));
        }

	}
	

	private void initializeButtons(){

		SquareImageView menuButton = new SquareImageView(getContext());
		menuButton.setBackgroundResource(R.drawable.ic_atras);
		menuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ItemDetailActivity.this.onBackPressed();
				//overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
				//overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
				overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
				
			}
		});
		addLeftViewToActionBar(menuButton);

		
		if (title != null) {
            //populate title and so on

            String strTmp = title;
            if (strTmp.length() == 0)
                strTmp = getContext().getResources().getString(R.string.no_title);
            setActionBarTitle(Html.fromHtml(strTmp));
        }

        if (description != null){

            String strTmp = description;
            if (strTmp.length() == 0)
                strTmp = getContext().getResources().getString(R.string.no_title);
			TextView txtDesc = (TextView)findViewById(R.id.item_detail);
			txtDesc.setText(Html.fromHtml(strTmp));
			
		}
		
	}
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
	

}
