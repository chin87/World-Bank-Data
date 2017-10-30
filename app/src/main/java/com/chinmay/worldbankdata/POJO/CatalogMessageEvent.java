package com.chinmay.worldbankdata.POJO;

/**
 * Created by hp on 9/9/17.
 */

public class CatalogMessageEvent extends MessageEvent{
    public final Catalog response;

    public CatalogMessageEvent(int status,
							   Catalog response) {
        this.status = status;
        this.response = response;
    }

    public boolean isSuccess(){
        return status == 0;
    }

}
