package com.bwei.dabian.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.bwei.dabian.MyAppclicatioin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitManager<T> {

    //http://172.17.8.100/small/user/v1/register
    public static final String REGRITER_URL="http://mobile.bwstudent.com/small/";

    public static RetrofitManager retrofitManager;


    public static synchronized RetrofitManager getInstance(){
        if(retrofitManager==null){
            retrofitManager=new RetrofitManager();
        }
        return retrofitManager;
    }

    private BaseApis mBaseApis;

    public RetrofitManager(){
        OkHttpClient builder = new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        SharedPreferences preferences = MyAppclicatioin.getApplication().getSharedPreferences("Header", Context.MODE_PRIVATE);
                        String userId = preferences.getString("userId", "");
                        String sessionId = preferences.getString("sessionId", "");
                        Request.Builder builder1 = request.newBuilder();
                        builder1.method(request.method(),request.body());

                        if(!TextUtils.isEmpty(userId)&&!TextUtils.isEmpty(sessionId)){
                            builder1.addHeader("userId",userId);
                            builder1.addHeader("sessionId",sessionId);
                        }

                        Request build = builder1.build();

                        return chain.proceed(build);
                    }
                })
                .build();

                Retrofit builder1 = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(REGRITER_URL)
                .client(builder)
                .build();

        mBaseApis = builder1.create(BaseApis.class);
    }



    public Map<String, RequestBody> generateRequestBody(Map<String,String> requestDataMap){

        HashMap<String, RequestBody> requestBodyHashMap = new HashMap<>();

        for (String key:requestDataMap.keySet()){
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
            requestBodyHashMap.put(key,requestBody);
        }
        return requestBodyHashMap;
    }

    public RetrofitManager get(String url,HttpListener listener){
        mBaseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
        return retrofitManager;
    }
    public RetrofitManager delete(String url,HttpListener listener){
        mBaseApis.delete(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
        return retrofitManager;
    }


    private Observer getObserver(final HttpListener listener) {
        Observer observer=new Observer<ResponseBody>(){

            //观察者
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if(listener!=null){
                    listener.onFail(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String data = responseBody.string();
                    if(listener!=null){
                        listener.onSuccess(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if(listener!=null){
                        listener.onFail(e.getMessage());
                    }
                }
            }
        };
        return observer;
    }

    public RetrofitManager post(String dataUrl,Map<String,String> map,HttpListener listener){
        if(map==null){
            map=new HashMap<>();
        }
        mBaseApis.post(dataUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
        return retrofitManager;
    }

    public RetrofitManager postFormBoby(String dataUrl,Map<String,RequestBody> map,HttpListener listener){
        if(map==null){
            map=new HashMap<>();
        }
        mBaseApis.postFormBody(dataUrl,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(listener));
        return retrofitManager;
    }

    public interface HttpListener {
        void onSuccess(String data);
        void onFail(String error);
    }



}
