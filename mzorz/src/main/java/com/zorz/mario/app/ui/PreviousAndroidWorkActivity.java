package com.zorz.mario.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.zorz.mario.Application;
import com.zorz.mario.ConstantsMzorz;
import com.zorz.mario.R;
import com.zorz.mario.adapter.ProjectsListAdapter;
import com.zorz.mario.api.Event;
import com.zorz.mario.api.Service;
import com.zorz.mario.model.ProjectItem;

import java.util.ArrayList;

public class PreviousAndroidWorkActivity extends BaseActivity {
	
	private static String TAG = "Zorz";
	private ListView listProjs;
	private ProjectsListAdapter projsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listProjs = (ListView)findViewById(R.id.gallery);
		setActionBarTitle(getString(R.string.mn_android));
        setActionBarIcon(R.drawable.ic_ab_drawer);
		//DataBaseManager.initializeDB(this);
		
        projsAdapter = new ProjectsListAdapter(PreviousAndroidWorkActivity.this, new ArrayList<ProjectItem>());
		listProjs.setAdapter(projsAdapter);

		listProjs.setOnItemClickListener(new OnItemClickListener() {

            /*
             * (non-Javadoc)
             * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
             */
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                if (null != projsAdapter) {
                    //get touched object
                    ProjectItem newsobj = (ProjectItem) projsAdapter.getItem(position);

                    Intent i = new Intent(getContext(), ItemDetailActivity.class);
                    i.putExtra(ConstantsMzorz.MZORZ_ITEM, newsobj);

                    startActivityForResult(i, 500);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        });
		
		
	}

	
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	 }	
	
	@Override
	protected void onStart() {
		super.onStart();

        Application.getEventBus().register(this);
        Service.getInstance().getAndroidProjects();
	}

    @Override
    protected void onStop() {
        Application.getEventBus().unregister(this);
        super.onStop();
    }

    public void onEvent(Event.AndroidProjectsLoadStartEvent event) {
        showProgressDialog(getString(R.string.server_login), false);
    }

    public void onEvent(Event.AndroidProjectsLoadCompleteEvent event) {
        dismissProgressDialog();
        if (event.object == null || event.object.projects == null || event.object.projects.size() == 0)
            Toast.makeText(this, R.string.error_no_items_found, Toast.LENGTH_SHORT).show();
        else{
            projsAdapter.setProjectsList(event.object.projects);
            projsAdapter.notifyDataSetChanged();
        }
    }

    public void onEvent(Event.AndroidProjectsLoadFailEvent event) {
        dismissProgressDialog();
        showError();
    }

}
