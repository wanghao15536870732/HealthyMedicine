package com.example.zhongahiyi.healthy.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.adapter.MyAdapter;
import com.example.zhongahiyi.healthy.view.bean.main.Icon;

import java.util.ArrayList;

public class FragmentMain extends Fragment {
    private Context mContext;
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

        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.drawable.ic_camera_, "拍照设置"));
        mData.add(new Icon(R.drawable.ic_search, "搜索设置"));
        mData.add(new Icon(R.drawable.ic_feedback, "手动设置"));
        mData.add(new Icon(R.drawable.ic_history, "历史查询"));
        mAdapter = new MyAdapter<Icon>(mData, R.layout.main_item_grid) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
            }
        };

        grid_photo.setAdapter(mAdapter);

        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}