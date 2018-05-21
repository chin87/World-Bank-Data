package com.chinmay.worldbankdata.ui.dashboard;

import com.chinmay.worldbankdata.base.activity.BaseView;
import com.chinmay.worldbankdata.base.presenter.BasePresenter;
import com.chinmay.worldbankdata.pojo.Datacatalog;

import java.util.ArrayList;

/**
 * Created by chinmay.deshpande on 10/11/17.
 */

public class WorldBankDataContract {

        public interface View extends BaseView {

            void showDataCatlog(ArrayList<Datacatalog> datacatalogArrayList);

            void showMessage(String message);

            void showProgressBar();

            void removeProgressBar();
        }

        public interface Presenter extends BasePresenter<View> {
            void loadDataCatlog();

            void viewDestroy();

            void viewPause();

            void viewResume(View view);
        }

    }
