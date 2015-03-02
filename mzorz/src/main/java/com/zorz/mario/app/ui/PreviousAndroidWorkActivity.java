package com.zorz.mario.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.zorz.mario.Application;
import com.zorz.mario.ConstantsMzorz;
import com.zorz.mario.R;
import com.zorz.mario.app.ui.adapter.ProjectsListAdapter;
import com.zorz.mario.api.Event;
import com.zorz.mario.api.Service;
import com.zorz.mario.model.ComparatorDL;
import com.zorz.mario.model.ComparatorId;
import com.zorz.mario.model.ComparatorYear;
import com.zorz.mario.model.ProjectItem;
import com.zorz.mario.model.ProjectsResponse;
import com.zorz.mario.model.favorites.FavoriteHandler;

import java.util.ArrayList;
import java.util.Collections;

public class PreviousAndroidWorkActivity extends BaseActivity {
	
	private static String TAG = "Zorz";
	private ListView listProjs;
	private ProjectsListAdapter projsAdapter;
    private ProjectsResponse projectsCache;
    private boolean bShowFavoritesOnly;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listProjs = (ListView)findViewById(R.id.gallery);
		setActionBarTitle(getString(R.string.mn_android));
        setActionBarIcon(R.drawable.ic_ab_drawer);
		//DataBaseManager.initializeDB(this);
        setDrawerSelectedOption(R.id.btnAndroidProjects);


        projsAdapter = new ProjectsListAdapter(PreviousAndroidWorkActivity.this, new ArrayList<ProjectItem>());
        listProjs.setEmptyView(findViewById(android.R.id.empty));
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_projects, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_search:
//                Log.d(TAG, "SEARCH PRESSED");
//                return true;
            case R.id.action_filter:
                Log.d(TAG, "FILTER PRESSED");
                return true;

            case R.id.favorites:
                Log.d(TAG, "Favorites checked");
                bShowFavoritesOnly = !bShowFavoritesOnly;
                item.setChecked(bShowFavoritesOnly);

                FavoriteHandler.updateServerListWithLocalFavlistInfo(this, projectsCache);
                if (bShowFavoritesOnly)
                    projsAdapter.setProjectsList(FavoriteHandler.getFavorites(this, "android").projects);
                else{
                    projsAdapter.setProjectsList(projectsCache.projects != null ? projectsCache.projects : null);
                }
                projsAdapter.notifyDataSetChanged();
                return true;

            case R.id.orderbydownload:
                Log.d(TAG, "Order by download");
                if (bShowFavoritesOnly)
                    Collections.sort(projsAdapter.getProjectsList(), new ComparatorDL());
                else
                    Collections.sort(projsAdapter.getProjectsList(), new ComparatorDL());
                projsAdapter.notifyDataSetChanged();

                return true;

            case R.id.orderbyyear:
                Log.d(TAG, "Order by YEAR");
                if (bShowFavoritesOnly)
                    Collections.sort(projsAdapter.getProjectsList(), new ComparatorYear());
                else
                    Collections.sort(projsAdapter.getProjectsList(), new ComparatorYear());

                projsAdapter.notifyDataSetChanged();
                return true;

            case R.id.orderbyimportance:
                Log.d(TAG, "Order by IMPORTANCE");
                if (bShowFavoritesOnly)
                    Collections.sort(projsAdapter.getProjectsList(), new ComparatorId());
                else
                    Collections.sort(projsAdapter.getProjectsList(), new ComparatorId());
                projsAdapter.notifyDataSetChanged();

                return true;

        }

        return super.onOptionsItemSelected(item);
    }

	@Override
	protected void onStart() {
		super.onStart();

        Application.getEventBus().register(this);
        if (projectsCache == null)
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
            FavoriteHandler.updateServerListWithLocalFavlistInfo(this, event.object);
            projectsCache = event.object;
            projsAdapter.setProjectsList(projectsCache.projects);
            projsAdapter.notifyDataSetChanged();
        }
    }

    public void onEvent(Event.AndroidProjectsLoadFailEvent event) {
        dismissProgressDialog();
        showError();
    }

}
