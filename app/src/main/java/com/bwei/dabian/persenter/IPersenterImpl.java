package com.bwei.dabian.persenter;



import com.bwei.dabian.callback.MyCallBack;
import com.bwei.dabian.model.IMdelImpl;
import com.bwei.dabian.view.IView;

import java.util.Map;

public class IPersenterImpl implements IPersenter {

    private IView iView;
    private IMdelImpl iMdel;

    public IPersenterImpl(IView iView){
        this.iView=iView;
        iMdel=new IMdelImpl();
    }

    @Override
    public void postRequest(String dataUrl, Map<String, String> params, Class clazz) {
        iMdel.postRequest(dataUrl, params, clazz, new MyCallBack() {
            @Override
            public void CallBack(Object data) {
                iView.onSuccess(data);
            }
        });
    }

    @Override
    public void getRequest(String dataUrl, Class clazz) {
        iMdel.getRequest(dataUrl, clazz, new MyCallBack() {
            @Override
            public void CallBack(Object data) {
                iView.onSuccess(data);
            }
        });
    }

    @Override
    public void deleteRequest(String dataUrl, Class clazz) {

    }


    public void deatch(){
        iMdel=null;
        iView=null;
    }
}
