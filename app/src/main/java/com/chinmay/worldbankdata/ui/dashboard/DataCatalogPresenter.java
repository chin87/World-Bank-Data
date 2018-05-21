package com.chinmay.worldbankdata.ui.dashboard;

import com.chinmay.worldbankdata.base.presenter.AbstractBasePresenterImpl;
import com.chinmay.worldbankdata.communication.WebCommunicator;
import com.chinmay.worldbankdata.pojo.CatalogMessageEvent;
import com.chinmay.worldbankdata.pojo.Datacatalog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by chinmay.deshpande on 10/11/17.
 */

public class DataCatalogPresenter extends
        AbstractBasePresenterImpl<WorldBankDataContract.View>
        implements WorldBankDataContract.Presenter {

    private WorldBankDataContract.View dataCatlogView;

    public DataCatalogPresenter(WorldBankDataContract.View view){
        super(view);
        dataCatlogView = view;
        onStart();
    }

    @Override
    public void loadDataCatlog() {
        if(dataCatlogView != null ) {
            dataCatlogView.showProgressBar();
        }
        WebCommunicator.getAllCatalog();
    }

    @Override
    public void viewDestroy() {
        onStop();
    }

    @Override
    public void viewPause() {
        dataCatlogView = null;
    }

    @Override
    public void viewResume(WorldBankDataContract.View view) {
        dataCatlogView = view;
    }

    private void onStart() {
        EventBus.getDefault().register(this);
    }

    private void onStop() {
        EventBus.getDefault().unregister(this);
    }

    // This method will be called when a MessageEvent is posted (in the UI thread for Toast)
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(CatalogMessageEvent event) {
        if (event.isSuccess()) {
            parseNewsData(new ArrayList<Datacatalog>(
                    Arrays.asList(event.response.getDatacatalog())
            ));
        } else {
            if(dataCatlogView != null ) {
                dataCatlogView.removeProgressBar();
                dataCatlogView.showMessage("FAILED TO FETCH NEWS");
            }
        }
    }

    private void parseNewsData(ArrayList<Datacatalog> response){
        if(response == null || response.size() == 0){
            return;
        }
        if(dataCatlogView != null ) {
            dataCatlogView.removeProgressBar();
            dataCatlogView.showDataCatlog(response);
        }
    }
}
