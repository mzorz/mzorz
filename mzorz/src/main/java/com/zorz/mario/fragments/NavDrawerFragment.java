package com.zorz.mario.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.viewcomponents.FontButton;
import com.zorz.mario.R;
import com.zorz.mario.app.ui.AboutAppActivity;
import com.zorz.mario.app.ui.AboutMeActivity;
import com.zorz.mario.app.ui.BaseActivity;
import com.zorz.mario.app.ui.CoverLetterActivity;
import com.zorz.mario.app.ui.PreviousAndroidWorkActivity;
import com.zorz.mario.app.ui.PreviousOtherWorkActivity;
import com.zorz.mario.app.ui.WantToWorkOnActivity;
import com.zorz.mario.app.ui.components.CircleTransformation;
import com.zorz.mario.app.ui.components.NavDrawerButton;

public class NavDrawerFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.navdrawer, container, false);

        try {
            ImageView iv = (ImageView) view.findViewById(R.id.profile_image);
            Picasso.with(getActivity())
                    .load(R.drawable.cvmz_pic)
                    .transform(new CircleTransformation(
                            60, //radius
                            2, //margin
                            0,//border
                            1)) //border stroke
                    .placeholder(R.drawable.cvmz_pic)
                    .error(R.drawable.cvmz_pic)
                    .into(iv);
        } catch (Exception ex){
            ex.printStackTrace();
        }


        final NavDrawerButton btnCoverLetter = (NavDrawerButton) view.findViewById(R.id.btnCoverLetter);
        btnCoverLetter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                // change the active item on the list so the user can see the item changed
                //setSelectedNavDrawerItem(itemId);
                ((BaseActivity)getActivity()).goToAppSection(R.id.btnCoverLetter);

            }
        });

        final NavDrawerButton btnAndroidProjects = (NavDrawerButton) view.findViewById(R.id.btnAndroidProjects);
        btnAndroidProjects.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BaseActivity)getActivity()).goToAppSection(R.id.btnAndroidProjects);
            }
        });

        final NavDrawerButton btnOtherProjects = (NavDrawerButton) view.findViewById(R.id.btnOtherProjects);
        btnOtherProjects.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BaseActivity)getActivity()).goToAppSection(R.id.btnOtherProjects);
            }
        });

        final NavDrawerButton btnWantToWorkOn = (NavDrawerButton) view.findViewById(R.id.btnWantToWorkOn);
        btnWantToWorkOn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BaseActivity)getActivity()).goToAppSection(R.id.btnWantToWorkOn);
            }
        });

        final NavDrawerButton btnAboutMe = (NavDrawerButton) view.findViewById(R.id.btnAboutMe);
        btnAboutMe.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BaseActivity)getActivity()).goToAppSection(R.id.btnAboutMe);
                btnAboutMe.formatNavDrawerItem(true);
            }
        });

        final NavDrawerButton btnAboutApp = (NavDrawerButton) view.findViewById(R.id.btnAboutApp);
        btnAboutApp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BaseActivity)getActivity()).goToAppSection(R.id.btnAboutApp);
            }
        });

 		return view;
	}


}
