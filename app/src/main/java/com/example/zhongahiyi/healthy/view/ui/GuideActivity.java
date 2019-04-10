package com.example.zhongahiyi.healthy.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.activity.LoginActivity;
import com.example.zhongahiyi.healthy.view.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private List<View> mList = new ArrayList<>();
    private View view1, view2, view3;
    private ImageView point1, point2, point3;

    //跳过
    private ImageView iv_back;

    private Button btn_start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initview();
    }

    //初始化
    private void initview() {
        point1 = (ImageView) findViewById(R.id.point1);
        point2 = (ImageView) findViewById(R.id.point2);
        point3 = (ImageView) findViewById(R.id.point3);
        iv_back = (ImageView) findViewById(R.id.iv_jump);


        iv_back.setOnClickListener(this);

        //设置默认小圆点
        setPoint(true, false, false);

        mViewPager = (ViewPager) findViewById(R.id.mViewpager);
        //监听滑动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            //切换
            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        setPoint(true, false, false);
                        break;
                    case 1:
                        setPoint(false, true, false);
                        break;
                    case 2:
                        setPoint(false, false, true);
                        iv_back.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //相当于初始化view
        view1 = View.inflate(this, R.layout.page_item_one, null);
        view2 = View.inflate(this, R.layout.page_item_two, null);
        view3 = View.inflate(this, R.layout.page_item_three, null);

        //view里面的控件
        view3.findViewById(R.id.btn_start).setOnClickListener(this);

        mList.add(view1);
        mList.add(view2);
        mList.add(view3);
        //设置viewpager适配器
        mViewPager.setAdapter(new GuideAdapter());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
            case R.id.iv_jump:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ((ViewPager) container).addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ((ViewPager) container).removeView(mList.get(position));
        }
    }


    //设置小圆点选中效果
    public void setPoint(boolean isCheck1, boolean isCheck2, boolean isCheck3) {
        if (isCheck1) {
            point1.setImageResource(R.drawable.point_on);
        } else {
            point1.setImageResource(R.drawable.point_off);
        }
        if (isCheck2) {
            point2.setImageResource(R.drawable.point_on);
        } else {
            point2.setImageResource(R.drawable.point_off);
        }
        if (isCheck3) {
            point3.setImageResource(R.drawable.point_on);
        } else {
            point3.setImageResource(R.drawable.point_off);
        }
    }
}
