package com.example.zhongahiyi.healthy.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.activity.MainActivity;
import com.example.zhongahiyi.healthy.adapter.LeftAdapter;
import com.example.zhongahiyi.healthy.adapter.RightAdapter;
import com.example.zhongahiyi.healthy.bean.drug.DrugBean;
import com.example.zhongahiyi.healthy.bean.drug.DrugItem;
import com.example.zhongahiyi.healthy.db.DBManager;

import java.util.ArrayList;
import java.util.List;

public class FragmentDrug extends Fragment {
    private RecyclerView mLeftRvRecyclerView;
    private RecyclerView mRightRvRecyclerView;


    private List<DrugBean> drugBeanList;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private List<DrugItem> listBeanList;

    public static FragmentDrug newInstance(String text) {
        FragmentDrug fragmentCommon = new FragmentDrug();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drug, container, false);
        mLeftRvRecyclerView = (RecyclerView) view.findViewById(R.id.main_left_rv);
        mRightRvRecyclerView = (RecyclerView) view.findViewById(R.id.main_right_rv);
        initData();
        leftAdapter = new LeftAdapter(drugBeanList);
        rightAdapter = new RightAdapter(listBeanList);
        mLeftRvRecyclerView.setAdapter(leftAdapter);
        mRightRvRecyclerView.setAdapter(rightAdapter);
        mLeftRvRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRightRvRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mLeftRvRecyclerView.addOnItemTouchListener(new SimpleClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                DrugBean drugBean = drugBeanList.get(i);
                listBeanList.clear();
                listBeanList.addAll(drugBean.getmList());
                leftAdapter.setSelectPos(i);
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

            }
        });
        return view;
    }

    private void initData() {
        drugBeanList = new ArrayList<>();
        listBeanList = new ArrayList<>();
        DBManager dbManager = new DBManager(getContext());
        DrugItem drugItem;
        DrugItem drugItems;
//        List<DrugItem> drugItems1 = new ArrayList<>();
        DrugBean drugBean1;
        SQLiteDatabase db1 = dbManager.getDatabase(getContext());
        Cursor cursor = db1.rawQuery("select jibing from durgclass", null);
        while (cursor.moveToNext()) {
            List<DrugItem> drugItems1 = new ArrayList<>();
            drugBean1 = new DrugBean();
            String jibing = cursor.getString(0);
            drugBean1.setTitle(jibing);
//            Log.e("TAG", jibing);
            Cursor cursor2 = db1.rawQuery("select durgId from durgclass where jibing = ? ", new String[]{jibing});
            while (cursor2.moveToNext()) {
                String id = cursor2.getString(0);
                String[] stringarr = id.split(",");
                for (String s : stringarr) {
//                    Log.e("TAG", s);
                    Cursor cursor3 = db1.rawQuery("select 药品名称,作用类别,Image1 from med where id= ?", new String[]{s});
                    while (cursor3.moveToNext()) {
                        try {
                            String drug_name = cursor3.getString(0);
                            String drug_leibie = cursor3.getString(1);
                            String dur_pic = cursor3.getString(2);
                            if (drug_leibie.isEmpty()) {
                                drug_leibie = " ";
                            }
                            drugItem = new DrugItem(dur_pic, drug_name, drug_leibie);
                            drugItems1.add(drugItem);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            cursor3.close();
                            continue;
                        }

                    }
                }
            }
            drugBean1.setmList(drugItems1);
            drugBeanList.add(drugBean1);
        }
        db1.close();
        listBeanList.addAll(drugBeanList.get(0).getmList());
    }
}