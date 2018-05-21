package com.chinmay.worldbankdata.ui.dashboard;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chinmay.worldbankdata.BR;
import com.chinmay.worldbankdata.R;
import com.chinmay.worldbankdata.pojo.Datacatalog;

import java.util.ArrayList;


/**
 * Created by chinmaydeshpande on 30/10/17.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsListBindingHolder> {

	private Context mContext;
	private ArrayList<Datacatalog> mStoryList;
	private IClick iClick;

	public NewsAdapter(Context context, ArrayList<Datacatalog> storyList, IClick iClick) {
		mContext = context;
		mStoryList = storyList;
		this.iClick = iClick;
	}

	@Override
	public NewsListBindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent,
				false);
		return new NewsListBindingHolder(view);
	}

	@Override
	public void onBindViewHolder(NewsListBindingHolder holder, int position) {
		final Datacatalog story = mStoryList.get(position);
		holder.getBinding().setVariable(BR.story, story);
		holder.getBinding().setVariable(BR.callback, this);
		holder.getBinding().executePendingBindings();
	}

	@Override
	public int getItemCount() {
		return mStoryList.size();
	}

	public class NewsListBindingHolder extends RecyclerView.ViewHolder {
		private ViewDataBinding binding;
		private Datacatalog storyResponse;

		public NewsListBindingHolder(View rowView) {
			super(rowView);
			binding = DataBindingUtil.bind(rowView);
			rowView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					itemCLicked(getStoryResponse());
				}
			});
		}

		public ViewDataBinding getBinding() {
			return binding;
		}

		private Datacatalog getStoryResponse() {
			return storyResponse;
		}

		private void setStoryResponse(Datacatalog storyResponse) {
			this.storyResponse = storyResponse;
		}
	}

	public interface IClick {
		public void clickedForUrl(String url);
	}

	public void update(int position, Datacatalog storyResponse) {
		mStoryList.set(position, storyResponse);
		notifyItemChanged(position);
	}

	public void itemCLicked(Datacatalog storyResponse) {
		Log.i("", "" + storyResponse.getUrl());
		if (iClick != null) {
			iClick.clickedForUrl(storyResponse.getUrl());
		}
	}
}
