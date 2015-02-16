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
	private List<ProjectItem> news;

	/**
	 * Constructor
	 *
	 * @param c Context of activity
	 */
	public ProjectsListAdapter(Context c, List<ProjectItem> news) {
		mContext = c;
		inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.news = news;
		
	}

	public void setNewsList(List<ProjectItem> a_arrList){
		this.news = a_arrList;
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return news.size();
	}

	/*
	 * (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		return news.get(position);
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

		holder.description.setText(Html.fromHtml(item.excerpt_es));

        String strImgUrl = item.photos != null ? item.photos.thumb : null;

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
