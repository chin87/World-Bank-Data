package com.chinmay.worldbankdata.pojo;

/**
 * Created by chinmaydeshpande on 30/10/17.
 */
public abstract class MessageEvent {
    public int status;
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
}
