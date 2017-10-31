package com.chinmay.worldbankdata.POJO;

/**
 * Created by chinmaydeshpande on 30/10/17.
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
