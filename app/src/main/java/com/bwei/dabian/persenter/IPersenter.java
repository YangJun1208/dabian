package com.bwei.dabian.persenter;

import com.bwei.dabian.callback.MyCallBack;

import java.util.Map;

public interface IPersenter {
    void postRequest(String dataUrl, Map<String, String> params, Class clazz);

    void putRequest(String dataUrl, Map<String, String> params, Class clazz);

    void getRequest(String dataUrl,Class clazz);

    void deleteRequest(String dataUrl,Class clazz);

}
