package com.bwei.dabian.model;


import com.bwei.dabian.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void postRequest(String dataUrl, Map<String, String> params, Class clazz, MyCallBack callBack);

    void getRequest(String dataUrl,Class clazz,MyCallBack callBack);

    void deleteRequest(String dataUrl,Class clazz,MyCallBack callBack);

    void putRequest(String dataUrl, Map<String, String> params, Class clazz, MyCallBack callBack);
}
