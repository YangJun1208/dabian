package com.bwei.dabian.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwei.dabian.R;
import com.bwei.dabian.circle.fragment.CircleFragment;
import com.bwei.dabian.fragment.FristPageFragment;
import com.bwei.dabian.fragment.IndentFregment;
import com.bwei.dabian.mine.fragment.MyFregment;
import com.bwei.dabian.shopping.fragment.ShoppingFregment;
import com.bwei.dabian.utils.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.viewpage_one)
    NoScrollViewPager viewPager;

    @BindView(R.id.group)
    RadioGroup radioGroup;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio2)
    RadioButton radio2;
    @BindView(R.id.radio3)
    RadioButton radio3;
    @BindView(R.id.radio4)
    RadioButton radio4;
    @BindView(R.id.radio5)
    RadioButton radio5;

    private ArrayList<Fragment> list;
    private FristPageFragment fristPageFragment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        fristPageFragment = new FristPageFragment();
        list = new ArrayList<>();
        list.add(fristPageFragment);
        list.add(new CircleFragment());
        list.add(new ShoppingFregment());
        list.add(new IndentFregment());
        list.add(new MyFregment());

        //添加适配器
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.radio2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.radio3:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.radio4:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.radio5:
                        viewPager.setCurrentItem(4);
                        break;
                    default:
                        break;
                }
            }
        });

        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        radioGroup.check(R.id.radio1);
                        break;
                    case 1:
                        radioGroup.check(R.id.radio2);
                        break;
                    case 2:
                        radioGroup.check(R.id.radio3);
                        break;
                    case 3:
                        radioGroup.check(R.id.radio4);
                        break;
                    case 4:
                        radioGroup.check(R.id.radio5);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ){
            fristPageFragment.getBackData(true);
        }
        return false;
    }




}
