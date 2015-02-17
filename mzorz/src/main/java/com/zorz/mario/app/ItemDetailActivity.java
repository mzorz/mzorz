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
import com.zorz.mario.model.ProjectItem;

public class ItemDetailActivity extends BaseActivity {
	
	private static String TAG = "Zorz";
    private String title;
    private String description;
    private String actionbartitle;
    private ProjectItem projectItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_news);

        title = getIntent().getStringExtra(ConstantsMzorz.MZORZ_ITEM_TITLE);
        description = getIntent().getStringExtra(ConstantsMzorz.MZORZ_ITEM_DESC);
        actionbartitle = getIntent().getStringExtra(ConstantsMzorz.MZORZ_ITEM_ACTIONBARTITLE);
        projectItem = getIntent().getParcelableExtra(ConstantsMzorz.MZORZ_ITEM_PHOTO);

		setActionBarTitle(actionbartitle);
		//initializeSlidingMenu();
		
		//DataBaseManager.initializeDB(this);

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
		
        if (projectItem.images != null){
            Picasso.with(this)
                    .load(projectItem.images != null ? projectItem.images.get(0).url : null)
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

}
