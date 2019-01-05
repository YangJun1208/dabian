package com.bwei.dabian.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.dabian.R;
import com.bwei.dabian.activity.MyFileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Lenovo
 */
public class MyFregment extends BaseFragment {
    @BindView(R.id.body_ziliao)
    TextView body_ziliao;


    @Override
    protected void initData() {
        body_ziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyFileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.myfregment;
    }

}
