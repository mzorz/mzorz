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

import com.viewcomponents.FontButton;
import com.zorz.mario.R;
import com.zorz.mario.app.ui.BaseActivity;
import com.zorz.mario.app.ui.CoverLetterActivity;
import com.zorz.mario.app.ui.PreviousAndroidWorkActivity;
import com.zorz.mario.app.ui.PreviousOtherWorkActivity;
import com.zorz.mario.app.ui.WantToWorkOnActivity;

public class SliderMenuFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View view = inflater.inflate(R.layout.fragment_menu_configurations, container, false);

		ImageView back = (ImageView) view.findViewById(R.id.bot_back);
		back.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View v) {
 				
				getActivity().onBackPressed();
			}
		});
		

		FontButton btnCoverLetter = (FontButton) view.findViewById(R.id.btnCoverLetter);
        btnCoverLetter.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View v) {

 				hideSlidingMenu();
 				finishCurrentScreen(); 				
 				Intent i = new Intent(getActivity(), CoverLetterActivity.class);
 				getActivity().startActivity(i);
 				getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			}
		});

        FontButton btnAndroidProjects = (FontButton) view.findViewById(R.id.btnAndroidProjects);
        btnAndroidProjects.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                hideSlidingMenu();
                finishCurrentScreen();
                Intent i = new Intent(getActivity(), PreviousAndroidWorkActivity.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FontButton btnOtherProjects = (FontButton) view.findViewById(R.id.btnOtherProjects);
        btnOtherProjects.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                hideSlidingMenu();
                finishCurrentScreen();
                Intent i = new Intent(getActivity(), PreviousOtherWorkActivity.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        FontButton btnWantToWorkOn = (FontButton) view.findViewById(R.id.btnWantToWorkOn);
        btnWantToWorkOn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                hideSlidingMenu();
                finishCurrentScreen();
                Intent i = new Intent(getActivity(), WantToWorkOnActivity.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


 		return view;
	}
	

	private void hideSlidingMenu(){
		Activity act = getActivity();
		if (act != null && act instanceof BaseActivity){
			((BaseActivity)act).slidingMenu.toggle();
		}
	}
	
	private void finishCurrentScreen(){
		Activity act = getActivity();
		if ((act instanceof BaseActivity))
			((BaseActivity)act).finish();				
	}
	
	private void changeEditTextState(View view, boolean isChecked) {
		/*LinearLayout codeContainer = (LinearLayout) view.findViewById(R.id.container_code_inputs);
		for (int i = 0; i < codeContainer.getChildCount(); i++) {
		  ((EditText) codeContainer.getChildAt(i)).setEnabled(isChecked);
		}*/
	}
}
