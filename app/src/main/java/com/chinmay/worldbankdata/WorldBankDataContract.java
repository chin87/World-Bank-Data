package com.chinmay.worldbankdata;

import com.chinmay.worldbankdata.POJO.Datacatalog;

import java.util.ArrayList;

/**
 * Created by chinmay.deshpande on 10/11/17.
 */

public class WorldBankDataContract {

        public interface View {

            void showDataCatlog(ArrayList<Datacatalog> datacatalogArrayList);

            void showMessage(String message);

            void showProgressBar();

            void removeProgressBar();
        }

        public interface Actions {
            void loadDataCatlog();

            void viewDestroy();

            void viewPause();

            void viewResume(View view);
        }

    }
