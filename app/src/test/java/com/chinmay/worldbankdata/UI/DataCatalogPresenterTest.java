package com.chinmay.worldbankdata.UI;

import com.chinmay.worldbankdata.POJO.Datacatalog;
import com.chinmay.worldbankdata.WorldBankDataContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.internal.matchers.Null;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by chinmay.deshpande on 13/11/17.
 */
public class DataCatalogPresenterTest {

    DataCatalogPresenter dataCatalogPresenter;
    WorldBankDataContract.View view;

    @Before
    public void init(){
        view = mock(WorldBankDataContract.View.class);
        dataCatalogPresenter = new DataCatalogPresenter(view);
    }

    @Test
    public void testProgress(){
        dataCatalogPresenter.loadDataCatlog();
        verify(view).showProgressBar();
        //verify(view).removeProgressBar();
    }

    @Test//(expected = NullPointerException.class)
    public void testDataResponse(){
        dataCatalogPresenter.loadDataCatlog();
        //verify(view).showProgressBar();
        //doThrow().when(view).removeProgressBar();
        //verify(view,timeout(10000).atLeastOnce()).removeProgressBar();
        verify(view,timeout(10000).atLeastOnce())
                .showDataCatlog(Matchers.<ArrayList<Datacatalog>>any());
                //.showDataCatlog((<ArrayList<Datacatalog>>any(Datacatalog.class)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataResponseX(){
        //doReturn(1)
        dataCatalogPresenter.loadDataCatlog();
        doThrow(IllegalArgumentException.class)
                .when(view).removeProgressBar();
    }
}