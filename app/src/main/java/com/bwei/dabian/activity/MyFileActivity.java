package com.bwei.dabian.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.dabian.Apis;
import com.bwei.dabian.MyAppclicatioin;
import com.bwei.dabian.R;
import com.bwei.dabian.bean.UpdateNameBean;
import com.bwei.dabian.bean.UpdatePwdBean;
import com.bwei.dabian.eventbus.Message;
import com.bwei.dabian.persenter.IPersenterImpl;
import com.bwei.dabian.view.IView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFileActivity extends BaseActivity implements IView {

    @BindView(R.id.files_name)
    TextView textView_name;
    @BindView(R.id.files_pwd)
    TextView textView_pwd;

    private IPersenterImpl iPersenter;
    private String name;
    private String name1;
    private String pwd;


    @Override
    protected void initData() {
        iPersenter=new IPersenterImpl(this);
        SharedPreferences user = MyAppclicatioin.getApplication().getSharedPreferences("User", Context.MODE_PRIVATE);
        String name = user.getString("name", "");
        pwd = user.getString("pwd", "");
        textView_pwd.setText(pwd);
        textView_name.setText(name);
    }

    @OnClick({R.id.files_name,R.id.files_pwd})
    public void setOnClick(View v){
        switch (v.getId()){
            case R.id.files_pwd:
                View view1 = LayoutInflater.from(this).inflate(R.layout.files_pwd,null);
                final EditText old_pwd = (EditText) view1.findViewById(R.id.fiels_old_pwd);
                old_pwd.setText(pwd);
                final EditText new_pwd = (EditText) view1.findViewById(R.id.fiels_new_pwd);
                AlertDialog dialog1=new AlertDialog.Builder(this)
                        .setMessage("确定要修改密码吗?")
                        .setNegativeButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name1 = new_pwd.getText().toString();
                                Map<String,String> params=new HashMap<>();
                                params.put("oldPwd",pwd);
                                params.put("newPwd",name1);
                                iPersenter.putRequest(Apis.TYPE_UPDATE_PWD,params,UpdatePwdBean.class);

                            }
                        })
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog1, int which) {
                                dialog1.dismiss();
                            }
                        })
                        .setView(view1)
                        .show();
                break;
            case R.id.files_name:
                View view = LayoutInflater.from(this).inflate(R.layout.files_name,null);
                final EditText account = (EditText) view.findViewById(R.id.fiels_edit);
                AlertDialog dialog=new AlertDialog.Builder(this)
                        .setMessage("确定要修改名字吗?")
                        .setNegativeButton("修改", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                name = account.getText().toString();
                                Map<String,String> params=new HashMap<>();
                                params.put("nickName",name);
                                iPersenter.putRequest(Apis.TYPE_UPDATE_NAME,params,UpdatePwdBean.class);
                                EventBus.getDefault().post(new Message(name,"update_name"));
                            }
                        })
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setView(view)
                        .show();
                break;
                default:break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.my_files;
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof UpdateNameBean){
            UpdateNameBean updateNameBean= (UpdateNameBean) data;
            if(updateNameBean.getStatus().equals("0000")){
                Toast.makeText(this, updateNameBean.getMessage(), Toast.LENGTH_SHORT).show();
                textView_name.setText(name);
            }
        }else if(data instanceof UpdatePwdBean){
            UpdatePwdBean updatePwdBean= (UpdatePwdBean) data;
            if(updatePwdBean.getStatus().equals("0000")){
                Toast.makeText(this, updatePwdBean.getMessage(), Toast.LENGTH_SHORT).show();
                textView_pwd.setText(name1);
                Intent intent = new Intent(MyFileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
