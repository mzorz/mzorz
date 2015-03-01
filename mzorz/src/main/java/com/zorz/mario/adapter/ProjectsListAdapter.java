package com.zorz.mario.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zorz.mario.R;
import com.zorz.mario.app.ui.components.CircleTransformation;
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
			holder.title = (TextView)  view.findViewById(R.id.project_title);
		    holder.date = (TextView)  view.findViewById(R.id.project_date);
            holder.description = (TextView)  view.findViewById(R.id.project_brief);
            holder.downloads = (TextView)  view.findViewById(R.id.project_downloads);
            holder.favorite = (ImageView)  view.findViewById(R.id.favorite);
			view.setTag(holder);
			convertView = view;
		} else {
			holder = (holder) convertView.getTag();
		}
		
		final ProjectItem item = (ProjectItem) getItem(position);

		holder.date.setText(Html.fromHtml(item.date));
        //holder.date.setVisibility(View.GONE);

        item.title = ((item.title == null) || (item.title.length() == 0)) ? mContext.getResources().getString(R.string.no_title) : item.title;
		holder.title.setText(Html.fromHtml("<b>" + item.title + "</b>") );

        item.brief = ((item.brief == null) || (item.brief.length() == 0)) ? mContext.getResources().getString(R.string.no_excerpt) : item.brief;
		holder.description.setText(Html.fromHtml(item.brief));

        item.downloads = ((item.downloads == null) || (item.downloads.length() == 0)) ? mContext.getResources().getString(R.string.no_info) : item.downloads;
        holder.downloads.setText(Html.fromHtml("D/L: " + item.downloads));

        String strImgUrl = (item.icon != null) ? item.icon : null;

        Picasso.with(mContext)
                .load(strImgUrl)
                .transform(new CircleTransformation(
                        40, //radius
                        2, //margin
                        0,//border
                        1)) //border stroke
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(holder.image);


        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.favorite = !item.favorite;
                //reflect favorite stats
                updateStarIcon(item, (ImageView)v);
            }
        });

        //reflect favorite status
        updateStarIcon(item, holder.favorite);

		return convertView;
		
	}

    private void updateStarIcon(ProjectItem item, ImageView v){
        if (item.favorite)
            v.setColorFilter(mContext.getResources().getColor(R.color.material_yellow_800)); // material yellow Tint
        else
            v.setColorFilter(Color.argb(0, 0, 0, 0)); // black Tint
    }

	private class holder {
        public ProjectItem item;
		public ImageView image;
		public TextView date;
		public TextView title;
		public TextView description;
        public TextView downloads;
        public ImageView favorite;

	}
	

}
