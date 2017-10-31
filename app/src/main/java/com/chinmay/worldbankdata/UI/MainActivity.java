package com.chinmay.worldbankdata.UI;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chinmay.worldbankdata.POJO.CatalogMessageEvent;
import com.chinmay.worldbankdata.POJO.Datacatalog;
import com.chinmay.worldbankdata.R;
import com.chinmay.worldbankdata.communication.WebCommunicator;
import com.chinmay.worldbankdata.databinding.ActivityMainBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements NewsAdapter.IClick{
	private ActivityMainBinding activityMainHackerNewsBinding;
	private NewsAdapter newsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activityMainHackerNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		activityMainHackerNewsBinding.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getNewsList();
			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EventBus.getDefault().unregister(this);
	}

	private void getNewsList(){
		WebCommunicator.getAllCatalog();
	}

	// This method will be called when a MessageEvent is posted (in the UI thread for Toast)
	@Subscribe(threadMode = ThreadMode.BACKGROUND)
	public void onMessageEvent(CatalogMessageEvent event) {
		if (event.isSuccess()) {
			parseNewsData(new ArrayList<Datacatalog>(
					Arrays.asList(event.response.getDatacatalog())
			));
		} else {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					failedListApi();
				}
			});
		}
	}

	private void failedListApi(){
		Toast.makeText(this, "FAILED TO FETCH NEWS", Toast.LENGTH_SHORT).show();
	}

	private void parseNewsData(ArrayList<Datacatalog> response){
		if(response == null || response.size() == 0){
			return;
		}
		runOnUiThread(new UIRunnable(response));

	}

	@Override
	public void clickedForUrl(String url) {
		Intent intent = new Intent(getBaseContext(), WebViewActivity.class);
		intent.putExtra(WebViewActivity.KEY_URL, url);
		startActivity(intent);
	}


	private class UIRunnable implements Runnable{
		private ArrayList<Datacatalog> stories;
		public UIRunnable(ArrayList<Datacatalog> stories){
			this.stories = stories;
		}

		@Override
		public void run() {
			setNewsAdapter(stories);
		}
	}

	private void setNewsAdapter( ArrayList<Datacatalog> stories ){
		newsAdapter = new NewsAdapter(this, stories, this);
		activityMainHackerNewsBinding.button.setVisibility(View.GONE);
		activityMainHackerNewsBinding.rvNewsList.setVisibility(View.VISIBLE);
		LinearLayoutManager layoutManager
				= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
		activityMainHackerNewsBinding.rvNewsList.setLayoutManager(layoutManager);
		activityMainHackerNewsBinding.rvNewsList.setAdapter(newsAdapter);
	}

}
