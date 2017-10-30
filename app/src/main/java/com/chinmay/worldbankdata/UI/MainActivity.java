package com.chinmay.worldbankdata.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chinmay.worldbankdata.POJO.CatalogMessageEvent;
import com.chinmay.worldbankdata.R;
import com.chinmay.worldbankdata.communication.WebCommunicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
		WebCommunicator.getAllCatalog();
	}

	@Override
	public void onStop() {
		super.onStop();
		EventBus.getDefault().unregister(this);
	}

	@Subscribe(threadMode = ThreadMode.BACKGROUND)
	public void onMessageEvent(CatalogMessageEvent event) {
		if (event.isSuccess()) {

		} else {

		}
	}

}
