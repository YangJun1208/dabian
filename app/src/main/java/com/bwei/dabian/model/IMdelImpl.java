package com.bwei.dabian.model;

import com.bwei.dabian.callback.MyCallBack;
import com.bwei.dabian.network.RetrofitManager;
import com.google.gson.Gson;

import java.util.Map;

public class IMdelImpl implements IModel {

    @Override
    public void postRequest(String dataUrl, Map<String, String> params, final Class clazz, final MyCallBack callBack) {


       RetrofitManager.getInstance().post(dataUrl,params,new RetrofitManager.HttpListener() {
           @Override
           public void onSuccess(String data) {
               Object o = new Gson().fromJson(data, clazz);
               callBack.CallBack(o);
           }

           @Override
           public void onFail(String error) {
               callBack.CallBack(error);
           }
       });

    }

    @Override
    public void getRequest(String dataUrl, final Class clazz, final MyCallBack callBack) {
        RetrofitManager.getInstance().get(dataUrl,new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                callBack.CallBack(o);
            }

            @Override
            public void onFail(String error) {
                callBack.CallBack(error);
            }
        });
    }

    @Override
    public void deleteRequest(String dataUrl, final Class clazz, final MyCallBack callBack) {
        RetrofitManager.getInstance().delete(dataUrl,new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                callBack.CallBack(o);
            }

            @Override
            public void onFail(String error) {
                callBack.CallBack(error);
            }
        });
    }

    @Override
    public void putRequest(String dataUrl, Map<String, String> params, final Class clazz, final MyCallBack callBack) {
        RetrofitManager.getInstance().put(dataUrl,params,new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                callBack.CallBack(o);
            }

            @Override
            public void onFail(String error) {
                callBack.CallBack(error);
            }
        });
    }

}
