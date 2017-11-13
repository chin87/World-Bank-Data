package com.chinmay.worldbankdata.UI;

import com.chinmay.worldbankdata.WorldBankDataContract;

import org.junit.Test;
import org.mockito.internal.matchers.Null;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by chinmay.deshpande on 13/11/17.
 */
public class DataCatalogPresenterTest {

    @Test
    public void testProgress(){
        WorldBankDataContract.View view = mock(WorldBankDataContract.View.class);
        DataCatalogPresenter dataCatalogPresenter = new DataCatalogPresenter(view);
        dataCatalogPresenter.loadDataCatlog();
        verify(view).showProgressBar();
        //verify(view).removeProgressBar();
    }

    @Test//(expected = NullPointerException.class)
    public void testDataResponse(){
        WorldBankDataContract.View view = mock(WorldBankDataContract.View.class);
        DataCatalogPresenter dataCatalogPresenter = new DataCatalogPresenter(view);
        dataCatalogPresenter.loadDataCatlog();
        //verify(view).showProgressBar();
        //doThrow().when(view).removeProgressBar();
    }
}