package com.zorz.mario.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zorz.mario.R;
import com.zorz.mario.model.ProjectItem;

import java.util.List;

public class ProjectsListAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater inflater;
	private List<ProjectItem> items;

	/**
	 * Constructor
	 *
	 * @param c Context of activity
	 */
	public ProjectsListAdapter(Context c, List<ProjectItem> news) {
		mContext = c;
		inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.items = news;
		
	}

	public void setProjectsList(List<ProjectItem> a_arrList){
		this.items = a_arrList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return items.size();
	}

	/*
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	/*
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/*
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		final View view;
		holder holder;
		if (convertView == null) {
			view = inflater.inflate(R.layout.gallery_item, null);
			holder = new holder();
			holder.image = (ImageView) view.findViewById(R.id.gallery_image);
			holder.title = (TextView)  view.findViewById(R.id.gallery_text);
			holder.date = (TextView)  view.findViewById(R.id.gallery_movie_date);
			holder.description = (TextView)  view.findViewById(R.id.gallery_movie_genre);
			view.setTag(holder);
			convertView = view;
		} else {
			holder = (holder) convertView.getTag();
		}
		
		ProjectItem item = (ProjectItem) getItem(position);

		holder.date.setText(Html.fromHtml(item.date));

        item.title = ((item.title == null) || (item.title.length() == 0)) ? mContext.getResources().getString(R.string.no_title) : item.title;
		holder.title.setText(Html.fromHtml(item.title));

        item.brief = ((item.brief == null) || (item.brief.length() == 0)) ? mContext.getResources().getString(R.string.no_excerpt) : item.brief;
		holder.description.setText(Html.fromHtml(item.brief));

        String strImgUrl = (item.images != null && item.images.get(0) != null) ? item.images.get(0).url : null;

        Picasso.with(mContext)
                .load(strImgUrl)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(holder.image);

		return convertView;
		
	}

	private class holder {
		public ImageView image;
		public TextView date;
		public TextView title;
		public TextView description;
	}
	

}
