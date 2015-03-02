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
    private int currItem = 0;

    private WebView myWebView;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);

        if (savedInstanceState != null){
            projectItem = savedInstanceState.getParcelable(ConstantsMzorz.MZORZ_ITEM);
            currItem = savedInstanceState.getInt("currItemIndex", 0);
        }
        else
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(ConstantsMzorz.MZORZ_ITEM, projectItem);
        savedInstanceState.putInt("currItemIndex",viewPager.getCurrentItem());
        super.onSaveInstanceState(savedInstanceState);
    }


	private void initializeButtons(){

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

            myWebView = (WebView) findViewById(R.id.webview);
            myWebView.loadData(strTmp,"text/html","utf-8");

		}


        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new ItemDetailPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(projectItem.images.size()-1);
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

        viewPager.setCurrentItem(currItem);

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
//            OneImageFragment fragment = new OneImageFragment();
//            fragment.setImageIndex(position);
//            fragment.setProjectItem(projectItem);
            OneImageFragment fragment = OneImageFragment.newInstance(projectItem, position);

            return fragment;
        }
    }


    public static class OneImageFragment extends Fragment{

        private int imageIndex;
        private ProjectItem projectItem;


        static OneImageFragment newInstance(ProjectItem item, int imgIdx) {
            OneImageFragment f = new OneImageFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", imgIdx);
            args.putParcelable(ConstantsMzorz.MZORZ_ITEM, item);
            f.setArguments(args);

            return f;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            imageIndex = getArguments() != null ? getArguments().getInt("num") : 0;
            projectItem = getArguments() != null ? (ProjectItem)(getArguments().getParcelable(ConstantsMzorz.MZORZ_ITEM)) : null;

            if (savedInstanceState != null) {
                //Restore the fragment's state here
                projectItem = savedInstanceState.getParcelable(ConstantsMzorz.MZORZ_ITEM);
                imageIndex = savedInstanceState.getInt(ConstantsMzorz.MZORZ_ITEM_PAGE, 0);
            }

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ViewGroup rootView = (ViewGroup) inflater.inflate(
                    R.layout.fragment_item_image, container, false);

            if (projectItem.images != null){
                try{
                    Picasso.with(getActivity())
                            .load(projectItem.images != null ? projectItem.images.get(imageIndex).url : null)
                            .placeholder(R.drawable.placeholder_square_mariol)
                            .error(R.drawable.placeholder_square_mariol)
                            .into((ImageView)rootView.findViewById(R.id.itempic));
                } catch (Exception ex ){
                    ex.printStackTrace();
                }
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


        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            if (savedInstanceState != null) {
                //Restore the fragment's state here
                projectItem = savedInstanceState.getParcelable(ConstantsMzorz.MZORZ_ITEM);
                imageIndex = savedInstanceState.getInt(ConstantsMzorz.MZORZ_ITEM_PAGE, 0);
            }
        }
        @Override
        public void onSaveInstanceState(Bundle outState) {
            outState.putParcelable(ConstantsMzorz.MZORZ_ITEM, projectItem);
            outState.putInt(ConstantsMzorz.MZORZ_ITEM_PAGE, imageIndex);
            super.onSaveInstanceState(outState);
        }
    }

}
