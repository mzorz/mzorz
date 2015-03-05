package com.zorz.mario.app.ui;

import android.content.DialogInterface;
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
import com.zorz.mario.app.ui.adapter.ProjectsListAdapter;
import com.zorz.mario.api.Event;
import com.zorz.mario.api.Service;
import com.zorz.mario.model.ProjectItem;
import com.zorz.mario.model.favorites.FavoriteHandler;

import java.util.ArrayList;

public class PreviousOtherWorkActivity extends BasePreviousWorkActivity {

    private static String TAG = "Zorz";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarTitle(getString(R.string.mn_other));
        setActionBarIcon(R.drawable.ic_ab_drawer);
        setDrawerSelectedOption(R.id.btnOtherProjects);
        projectType = "other";
    }

    @Override
    protected void onStart() {
        super.onStart();

        Application.getEventBus().register(this);
        if (projectsCache == null)
            Service.getInstance().getOtherProjects();
    }

    @Override
    protected void onStop() {
        Application.getEventBus().unregister(this);
        super.onStop();
    }

    public void onEvent(Event.OtherProjectsLoadStartEvent event) {
        showProgressDialog(getString(R.string.server_login), false);
    }

    public void onEvent(Event.OtherProjectsLoadCompleteEvent event) {
        dismissProgressDialog();
        if (event.object == null || event.object.projects == null || event.object.projects.size() == 0)
            //Toast.makeText(this, R.string.error_no_items_found, Toast.LENGTH_SHORT).show();
            showError(this, getResources().getString(R.string.error_no_items_found), false, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        else{
            FavoriteHandler.updateServerListWithLocalFavlistInfo(this, event.object);
            projectsCache = event.object;
            projsAdapter.setProjectsList(projectsCache.projects);
            projsAdapter.notifyDataSetChanged();
        }
    }

    public void onEvent(Event.OtherProjectsLoadFailEvent event) {
        dismissProgressDialog();
        showError();
    }

}
