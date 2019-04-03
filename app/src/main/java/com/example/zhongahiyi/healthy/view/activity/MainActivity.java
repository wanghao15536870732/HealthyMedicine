package com.example.zhongahiyi.healthy.view.activity;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.fragment.FragmentDrug;
import com.example.zhongahiyi.healthy.view.fragment.FragmentInfo;
import com.example.zhongahiyi.healthy.view.fragment.FragmentMain;
import com.example.zhongahiyi.healthy.view.fragment.FragmentNews;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TabView tabView;
    private FlowingDrawer mDrawer;
    private ImageView mAvator,ic_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        tabView = (TabView) findViewById(R.id.tabView);
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mAvator = (ImageView) findViewById( R.id.avator );
        ic_back = (ImageView) findViewById( R.id.back_menu );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initTabView();
        initDrawer();
        mAvator.setOnClickListener( this);
        ic_back.setOnClickListener( this );
    }

    private void initDrawer(){

        mDrawer.setTouchMode( ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });

    }

    private void initTabView(){
        List<TabViewChild> tabViewChildList=new ArrayList<>();
        TabViewChild tabViewChildMain = new TabViewChild(R.drawable.tab_main,R.drawable.tab_main1,"首页",
                FragmentMain.newInstance("首页"));
        TabViewChild tabViewChildDrug = new TabViewChild(R.drawable.tab_drug,R.drawable.tab_drug1, "分类",
                FragmentDrug.newInstance("分类"));
        TabViewChild tabViewChildNews = new TabViewChild(R.drawable.tab_news,R.drawable.tab_news1,"资讯",
                FragmentNews.newInstance("资讯"));
        TabViewChild tabViewChildInfo = new TabViewChild(R.drawable.tab_info,R.drawable.tab_info1,"我的",
                FragmentInfo.newInstance("我的"));
        tabViewChildList.add(tabViewChildMain);
        tabViewChildList.add(tabViewChildDrug);
        tabViewChildList.add(tabViewChildNews);
        tabViewChildList.add(tabViewChildInfo);
        //end add data
        tabView.setTabViewDefaultPosition(2);
        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int  position, ImageView currentImageIcon, TextView currentTextView) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.avator:
                mDrawer.openMenu();
                break;
            case R.id.back_menu:
                mDrawer.closeMenu();
                break;
            default:
                break;
        }
    }
}
