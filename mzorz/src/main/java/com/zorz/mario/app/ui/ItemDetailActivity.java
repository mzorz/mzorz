package com.zorz.mario.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.viewcomponents.SquareImageView;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;
import com.zorz.mario.ConstantsMzorz;
import com.zorz.mario.R;
import com.zorz.mario.model.ProjectItem;

public class ItemDetailActivity extends BaseActivity {
	
	private static String TAG = "Zorz";
    protected ProjectItem projectItem;
    private ViewPager viewPager;
    private PageIndicator mIndicator;

    private WebView myWebView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);

        projectItem = getIntent().getParcelableExtra(ConstantsMzorz.MZORZ_ITEM);

		setActionBarTitle(projectItem.title);

		initializeButtons();

	}
	
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	 }	

	@Override
	protected void onStart() {
		super.onStart();

	}
	

	private void initializeButtons(){

//		SquareImageView menuButton = new SquareImageView(getContext());
//		menuButton.setBackgroundResource(R.drawable.ic_atras);
//		menuButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				ItemDetailActivity.this.onBackPressed();
//				overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
//
//			}
//		});
//		addLeftViewToActionBar(menuButton);
//
        toolbar.setNavigationIcon(R.drawable.ic_up);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

		if (projectItem.title != null) {
            //populate title and so on

            String strTmp = projectItem.title;
            if (strTmp.length() == 0)
                strTmp = getContext().getResources().getString(R.string.no_title);
            setActionBarTitle(Html.fromHtml(strTmp));
        }

        if (projectItem.description != null){

            String strTmp = projectItem.description;
            if (strTmp.length() == 0)
                strTmp = getContext().getResources().getString(R.string.no_title);
//			TextView txtDesc = (TextView)findViewById(R.id.item_detail);
//			txtDesc.setText(Html.fromHtml(strTmp));

            myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadData(strTmp,"text/html","utf-8");

		}


        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new ItemDetailPagerAdapter(getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {

            public void onPageScrollStateChanged(int i)
            {
            }

            public void onPageScrolled(int i, float f, int j)
            {
            }

            public void onPageSelected(int i)
            {
            }

        });

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);

		
	}

    private class ItemDetailPagerAdapter extends FragmentStatePagerAdapter {
        public ItemDetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return projectItem.images.size();
        }

        @Override
        public Fragment getItem(int position) {
            OneImageFragment fragment = new OneImageFragment();
            fragment.setImageIndex(position);
            fragment.setProjectItem(projectItem);

            return fragment;
        }
    }


    public static class OneImageFragment extends Fragment{

        private int imageIndex;
        private ProjectItem projectItem;

        public void setProjectItem(ProjectItem item){
            this.projectItem = item;
        }

        public void setImageIndex(int idx){
            this.imageIndex = idx;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.fragment_item_image, container, false);

            if (projectItem.images != null){
                Picasso.with(getActivity())
                        .load(projectItem.images != null ? projectItem.images.get(imageIndex).url : null)
                        .placeholder(R.drawable.mz_logo_splash_ic)
                        .error(R.drawable.mz_logo_splash_ic)
                        .into((ImageView)rootView.findViewById(R.id.itempic));
            }

            rootView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //here show some other screen with full blown image
                    Intent i = new Intent(getActivity(), ImageActivity.class);
                    i.putExtra(ConstantsMzorz.MZORZ_ITEM, projectItem);
                    i.putExtra(ConstantsMzorz.MZORZ_ITEM_PAGE, imageIndex);

                    startActivityForResult(i, 500);
                }
            });


            return rootView;
        }
    }

}
