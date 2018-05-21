package com.chinmay.worldbankdata.ui.dashboard;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chinmay.worldbankdata.R;


/**
 * Created by chinmaydeshpande on 30/10/17.
 */
public class WebViewActivity extends Activity {
	public static final String KEY_URL = "KEY_URL";
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);

		String url = getIntent().getStringExtra(KEY_URL);
		WebView wv1=(WebView)findViewById(R.id.webView);
		wv1.setWebViewClient(new MyBrowser());
		wv1.getSettings().setLoadsImagesAutomatically(true);
		wv1.getSettings().setJavaScriptEnabled(true);
		wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		wv1.loadUrl(url);

	}

	private class MyBrowser extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}
