package cn.qdsc.msp.controller;

import android.content.Context;

import cn.qdsc.msp.business.BusinessListener;

/**
 * Created by lenovo on 2015/11/30.
 */
public class BaseController {

    Context mContext;
    ControllerListener mControllerListener;
    BusinessListener.BusinessType mBusinessType;

    public BaseController(ControllerListener listener) {
        mControllerListener = listener;
    }

    public BaseController(Context context,ControllerListener listener ) {
        mContext = context;
        mControllerListener = listener;
    }

    public void setControllerListener(ControllerListener listener) {
        mControllerListener = listener;
    }




}
