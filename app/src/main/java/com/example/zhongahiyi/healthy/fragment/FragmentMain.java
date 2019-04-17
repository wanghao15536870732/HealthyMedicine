package com.example.zhongahiyi.healthy.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.activity.AccurateActivity;
import com.example.zhongahiyi.healthy.activity.CollectActivity;
import com.example.zhongahiyi.healthy.activity.HistoryActivity;
import com.example.zhongahiyi.healthy.activity.MainActivity;
import com.example.zhongahiyi.healthy.activity.RemindActivity;
import com.example.zhongahiyi.healthy.activity.SearchActivity;
import com.example.zhongahiyi.healthy.adapter.MyAdapter;
import com.example.zhongahiyi.healthy.bean.main.Icon;

import java.util.ArrayList;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class FragmentMain extends Fragment {
    private GridView grid_photo;
    private BaseAdapter mAdapter = null;
    private ArrayList<Icon> mData = null;

    public static FragmentMain newInstance(String text){
        FragmentMain fragmentCommon=new FragmentMain();
        Bundle bundle = new Bundle();
        bundle.putString("text",text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_main,container,false);

        grid_photo = (GridView) view.findViewById(R.id.grid_photo);
        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.color.two,R.drawable.ic_search, "搜索设置"));
        mData.add(new Icon(R.color.one,R.drawable.ic_feedback, "手动设置"));
        mData.add(new Icon(R.color.five,R.drawable.ic_collect, "我的收藏"));
        mData.add(new Icon(R.color.three,R.drawable.ic_history, "生病历史"));
        mData.add(new Icon(R.color.six,R.drawable.ic_remind, "服药提醒"));
        mData.add(new Icon(R.color.four,R.drawable.ic_scan_code, "药单扫描"));
        mAdapter = new MyAdapter<Icon>(mData, R.layout.main_item_grid) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
                holder.setbackgroudcolor(R.id.background_set,obj.getIcolor());
            }
        };
        grid_photo.setAdapter(mAdapter);
        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(getContext(), SearchActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), CollectActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), HistoryActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(getContext(), RemindActivity.class));
                        break;
                    case 5:
                       startActivity(new Intent(getContext(), AccurateActivity.class));
                       break;
                    default:
                        Toast.makeText(getContext(), "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return view;
    }

}