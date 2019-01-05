package com.bwei.dabian.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.dabian.Apis;
import com.bwei.dabian.R;
import com.bwei.dabian.bean.LoginBean;
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
 * 登录
 *
 */
public class MainActivity extends BaseActivity implements View.OnClickListener,IView {

    @BindView(R.id.login_register)
    TextView textView;
    @BindView(R.id.text_login_phone)
    EditText login_phone;
    @BindView(R.id.login_eye)
    ImageView image_eye;
    @BindView(R.id.login_login_pwd)
    EditText login_pwd;
    @BindView(R.id.checkbox_login)
    CheckBox checkBox_login;
    private IPersenterImpl iPersenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String phone;
    private String pwd;

    @Override
    protected void initData() {
        ButterKnife.bind(this);
        iPersenter = new IPersenterImpl(this);
        //记住密码
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean r_ischeck = sharedPreferences.getBoolean("r_ischeck", false);
        if (r_ischeck) {
            String name = sharedPreferences.getString("phone", null);
            String pass = sharedPreferences.getString("pwd", null);
            login_phone.setText(name);
            login_pwd.setText(pass);
            checkBox_login.setChecked(true);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    private void loadData(String phone, String pwd) {
        Map<String,String> params=new HashMap<>();
        params.put("phone",phone);
        params.put("pwd",pwd);
        iPersenter.postRequest(Apis.TYPE_LOGIN,params,LoginBean.class);
    }


    @OnTouch(R.id.login_eye)
    public boolean loginEyeClick(View view, MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            login_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            login_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        return false;
    }

    @OnClick({R.id.login_register,R.id.login_eye,R.id.login_button,R.id.checkbox_login})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_register:
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button:
                phone = login_phone.getText().toString();
                pwd = login_pwd.getText().toString();
                loadData(phone, pwd);
                editor.putString("phone", phone);
                editor.putString("pwd", pwd);
                editor.putBoolean("r_ischeck", true);
                editor.commit();
                break;
            case R.id.checkbox_login:
                if(checkBox_login.isChecked()){
                    editor.putString("phone", phone);
                    editor.putString("pwd", pwd);
                    editor.putBoolean("r_ischeck", true);
                    editor.commit();
                }else{
                    editor.clear();
                    editor.commit();
                }
                break;
                default:
                    break;
        }
    }

    @Override
    public void onSuccess(Object data) {
        if(data instanceof LoginBean){
            LoginBean loginBean= (LoginBean) data;
            if(loginBean.getStatus().equals("0000")){
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent1);
                int userId = loginBean.getResult().getUserId();
                String sessionId = loginBean.getResult().getSessionId();
                sharedPreferences = getSharedPreferences("Header", MODE_PRIVATE);
                sharedPreferences.edit().putString("userId",userId+"").putString("sessionId",sessionId).commit();

            }else if(loginBean.getStatus().equals("1001")){
                Toast.makeText(this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.deatch();
    }
}
