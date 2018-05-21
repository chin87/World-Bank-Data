package com.chinmay.worldbankdata.ui.dashboard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chinmay.worldbankdata.R;
import com.chinmay.worldbankdata.base.activity.AbstractBaseActivityImpl;
import com.chinmay.worldbankdata.databinding.ActivityMainBinding;
import com.chinmay.worldbankdata.pojo.Datacatalog;

import java.util.ArrayList;

public class MainActivity extends
		AbstractBaseActivityImpl<WorldBankDataContract.View, WorldBankDataContract.Presenter>
		implements NewsAdapter.IClick, WorldBankDataContract.View, IRunonUi<Datacatalog>, IShowToast<String>{
	private ActivityMainBinding activityMainHackerNewsBinding;
	private NewsAdapter newsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activityMainHackerNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		activityMainHackerNewsBinding.button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				getPresenter().loadDataCatlog();
			}
		});

	}

	@Override
	protected void onPause() {
		super.onPause();
		getPresenter().viewPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getPresenter().viewResume(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		getPresenter().viewDestroy();
	}

	@Override
	public void clickedForUrl(String url) {
		Intent intent = new Intent(getBaseContext(), WebViewActivity.class);
		intent.putExtra(WebViewActivity.KEY_URL, url);
		startActivity(intent);
	}

	@Override
	public void showDataCatlog(ArrayList<Datacatalog> datacatalogArrayList) {
		runOnUiThread(new UIRunnable<>(datacatalogArrayList, this));
	}

	@Override
	public void showMessage(String message) {
		runOnUiThread(new UIMessageRunnable<>(message, this));
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

	@Override
	public void runOnUi(ArrayList<Datacatalog> list) {
		setNewsAdapter(list);
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

	@Override
	protected WorldBankDataContract.Presenter createPresenter() {
		return new DataCatalogPresenter(this);
	}

	@Override
	public void toastMessage(String message) {
		Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
	}
}
