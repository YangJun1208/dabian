package com.bwei.dabian.mine.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import com.bwei.dabian.MyAppclicatioin;
import com.bwei.dabian.R;
import com.bwei.dabian.activity.FootActivity;
import com.bwei.dabian.activity.MoneyActivity;
import com.bwei.dabian.activity.MyFileActivity;
import com.bwei.dabian.eventbus.Message;
import com.bwei.dabian.fragment.BaseFragment;
import com.bwei.dabian.persenter.IPersenterImpl;
import com.bwei.dabian.view.IView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Lenovo
 */
public class MyFregment extends BaseFragment implements IView {
    @BindView(R.id.body_ziliao)
    TextView body_ziliao;
    @BindView(R.id.text_touxiang)
    TextView textView_name;
    private IPersenterImpl iPersenter;
    @BindView(R.id.body_text_money)
    TextView textView_money;


    @Override
    protected void initData() {
        body_ziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyFileActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences user = MyAppclicatioin.getApplication().getSharedPreferences("User", Context.MODE_PRIVATE);
        String name = user.getString("name", "");
        textView_name.setText(name);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setUpdateName(Message message){
        if(message.getFlag().equals("update_name")){
            String object = (String) message.getObject();
            textView_name.setText(object);
        }
    }

    @OnClick({R.id.body_text_foot,R.id.body_text_money})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.body_text_money:
                new Intent(getContext(),MoneyActivity.class);
                break;
            case R.id.body_text_foot:
                Intent intent = new Intent(getContext(), FootActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }
    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
        iPersenter=new IPersenterImpl(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.myfregment;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onSuccess(Object data) {
        /*if(data instanceof UpdateNameBean){
            UpdateNameBean updateNameBean= (UpdateNameBean) data;
           if(updateNameBean.getStatus().equals("0000")){
               Toast.makeText(getContext(), updateNameBean.getMessage(), Toast.LENGTH_SHORT).show();
           }
        }*/
    }
}
