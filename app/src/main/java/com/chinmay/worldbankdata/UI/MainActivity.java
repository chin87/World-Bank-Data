package com.chinmay.worldbankdata.UI;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chinmay.worldbankdata.POJO.Datacatalog;
import com.chinmay.worldbankdata.R;
import com.chinmay.worldbankdata.WorldBankDataContract;
import com.chinmay.worldbankdata.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsAdapter.IClick, WorldBankDataContract.View{
	private ActivityMainBinding activityMainHackerNewsBinding;
	private NewsAdapter newsAdapter;
	private DataCatalogPresenter dataCatalogPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dataCatalogPresenter = new DataCatalogPresenter(this);
		activityMainHackerNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		activityMainHackerNewsBinding.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				dataCatalogPresenter.loadDataCatlog();
			}
		});

	}

	@Override
	protected void onPause() {
		super.onPause();
		dataCatalogPresenter.viewPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		dataCatalogPresenter.viewResume(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dataCatalogPresenter.viewDestroy();
	}

	@Override
	public void clickedForUrl(String url) {
		Intent intent = new Intent(getBaseContext(), WebViewActivity.class);
		intent.putExtra(WebViewActivity.KEY_URL, url);
		startActivity(intent);
	}

	@Override
	public void showDataCatlog(ArrayList<Datacatalog> datacatalogArrayList) {
		runOnUiThread(new UIRunnable(datacatalogArrayList));
	}

	@Override
	public void showMessage(String message) {
		runOnUiThread(new UIMessageRunnable(message));
	}

	@Override
	public void showProgressBar() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				activityMainHackerNewsBinding.pbData.setVisibility(View.VISIBLE);
			}
		});
	}

	@Override
	public void removeProgressBar() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				activityMainHackerNewsBinding.pbData.setVisibility(View.GONE);
			}
		});
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

	private class UIMessageRunnable implements Runnable{
		private String message;
		public UIMessageRunnable(String message){
			this.message = message;
		}

		@Override
		public void run() {
			Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
		}
	}
}
