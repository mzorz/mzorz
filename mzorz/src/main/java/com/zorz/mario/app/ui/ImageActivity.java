package com.zorz.mario.app.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.viewcomponents.SquareImageView;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;
import com.zorz.mario.ConstantsMzorz;
import com.zorz.mario.R;
import com.zorz.mario.model.ProjectItem;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageActivity extends BaseActivity {

	private static String TAG = "Zorz";
    protected ProjectItem projectItem;
    private ViewPager viewPager;
    private PageIndicator mIndicator;
    private int currentPage = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_image);

        if (savedInstanceState != null){
            currentPage = savedInstanceState.getInt(ConstantsMzorz.MZORZ_ITEM_PAGE);
            projectItem = savedInstanceState.getParcelable(ConstantsMzorz.MZORZ_ITEM);
        } else {
            projectItem = getIntent().getParcelableExtra(ConstantsMzorz.MZORZ_ITEM);
            currentPage = getIntent().getIntExtra(ConstantsMzorz.MZORZ_ITEM_PAGE, 0);
        }

		initializeButtons();

	}

	@Override
	protected void onStart() {
		super.onStart();

	}

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelable(ConstantsMzorz.MZORZ_ITEM, projectItem);
        savedInstanceState.putInt(ConstantsMzorz.MZORZ_ITEM_PAGE, currentPage);
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

            setActionBarTitle(Html.fromHtml(strTmp + getString(R.string.app_images)));

        }

        viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new ItemDetailPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(currentPage);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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

        viewPager.setCurrentItem(currentPage);

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
            OneImageFragment fragment = OneImageFragment.newInstance(projectItem, position);

            return fragment;
        }
    }


    public static class OneImageFragment extends Fragment{

        private int imageIndex;
        private ProjectItem projectItem;
        private PhotoViewAttacher mAttacher;
        private ImageView mImageView;

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
                    R.layout.fragment_item_image_full, container, false);

            mImageView = (ImageView) rootView.findViewById(R.id.itempic);

            if (projectItem.images != null){
                Picasso.with(getActivity())
                        .load(projectItem.images != null ? projectItem.images.get(imageIndex).url : null)
                        .placeholder(R.drawable.mz_logo_splash_ic)
                        .error(R.drawable.mz_logo_splash_ic)
                        .into((ImageView)rootView.findViewById(R.id.itempic));

                if (mAttacher == null)
                    mAttacher = new PhotoViewAttacher(mImageView);
                else
                    mAttacher.update();
            }

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
