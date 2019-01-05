package com.bwei.dabian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.dabian.Apis;
import com.bwei.dabian.R;
import com.bwei.dabian.bean.RegisterBean;
import com.bwei.dabian.persenter.IPersenterImpl;
import com.bwei.dabian.view.IView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * @author Lenovo
 * 注册页面
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,IView {

    @BindView(R.id.text_register_phone)
    TextView register_phone;

    @BindView(R.id.register_login_yan)
    TextView register_yan;

    @BindView(R.id.register_login_pwd)
    TextView register_pwd;

    @BindView(R.id.register_eye)
    ImageView register_eye;

    private IPersenterImpl iPersenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        iPersenter=new IPersenterImpl(this);


    }

    private void loadData() {
        String phone = register_phone.getText().toString();
        String pwd = register_pwd.getText().toString();
        Map<String,String> params=new HashMap<>();
        params.put("phone",phone);
        params.put("pwd",pwd);
        iPersenter.postRequest(Apis.TYPE_REGRITER,params,RegisterBean.class);
    }


    @OnTouch(R.id.register_eye)
    public boolean loginEyeClick(View view, MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            register_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            register_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        return false;
    }


    @OnClick({R.id.register_button,R.id.register_eye,R.id.register_login})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_button:
                loadData();
                break;
            case R.id.register_login:
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                break;
                default:
                    break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof RegisterBean){
            RegisterBean registerBean= (RegisterBean) data;
            if(registerBean.getStatus().equals("1001")){
                Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.deatch();
    }
}
