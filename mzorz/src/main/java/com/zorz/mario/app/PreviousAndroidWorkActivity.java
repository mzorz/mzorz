package com.zorz.mario.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zorz.mario.Application;
import com.zorz.mario.ConstantsMzorz;
import com.zorz.mario.R;
import com.zorz.mario.adapter.ProjectsListAdapter;
import com.zorz.mario.api.Service;
import com.zorz.mario.model.ProjectItem;

import java.util.ArrayList;

public class PreviousAndroidWorkActivity extends BaseActivity {
	
	private static String TAG = "Zorz";
    private Service mService;
	private ListView listNews;
	private ProjectsListAdapter newsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listNews = (ListView)findViewById(R.id.gallery);
		setActionBarTitle(getString(R.string.mn_android));
		initializeSlidingMenu();
		
		//DataBaseManager.initializeDB(this);
		
        mService = new Service();

		newsAdapter = new ProjectsListAdapter(PreviousAndroidWorkActivity.this, new ArrayList<ProjectItem>());
		listNews.setAdapter(newsAdapter);

		listNews.setOnItemClickListener(new OnItemClickListener() {

			/*
			 * (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
				if (null != newsAdapter) {
					//get touched object
					ProjectItem newsobj = (ProjectItem) newsAdapter.getItem(position);
					
                    Intent i = new Intent(getContext(), ItemDetailActivity.class);
                    i.putExtra(ConstantsMzorz.MZORZ_ITEM_TITLE, newsobj.title);
                    i.putExtra(ConstantsMzorz.MZORZ_ITEM_DESC, newsobj.excerpt_es);
                    i.putExtra(ConstantsMzorz.MZORZ_ITEM_ACTIONBARTITLE, getString(R.string.mn_cover));
                    i.putExtra(ConstantsMzorz.MZORZ_ITEM_PHOTO, newsobj.photos);

					startActivityForResult(i, 500);
					//overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
					overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
				}
			}
		});
		
		
	}

	
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 //overridePendingTransition(R.anim.animation_leave, R.anim.animation_enter);
		 overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	 }	
	
	@Override
	protected void onStart() {
		super.onStart();

        Application.getEventBus().register(this);
        mService.getPreviousWork();
	}

    @Override
    protected void onStop() {
        Application.getEventBus().unregister(this);
        super.onStop();
    }

//    public void onEvent(Event.NewsLoadStartEvent event) {
//        showProgressDialog(getString(R.string.server_login), false);
//    }
//
//    public void onEvent(Event.NewsLoadCompleteEvent event) {
//        dismissProgressDialog();
//        if (event.object == null || event.object.data == null || event.object.data.size() == 0)
//            Toast.makeText(this, R.string.error_no_items_found, Toast.LENGTH_SHORT).show();
//        else{
//            newsAdapter.setNewsList(event.object.data);
//            newsAdapter.notifyDataSetChanged();
//        }
//    }
//
//    public void onEvent(Event.NewsLoadFailEvent event) {
//        dismissProgressDialog();
//    }

}
