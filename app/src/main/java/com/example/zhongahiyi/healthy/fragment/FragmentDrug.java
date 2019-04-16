package com.example.zhongahiyi.healthy.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
        DBManager dbManager = new DBManager(MainActivity.getContext());
        DrugItem drugItem;
        DrugItem drugItems;
//        List<DrugItem> drugItems1 = new ArrayList<>();
        DrugBean drugBean1;
        SQLiteDatabase db1 = dbManager.getDatabase();
        Cursor cursor = db1.rawQuery("select jibing from durgclass", null);
        while (cursor.moveToNext()) {
            List<DrugItem> drugItems1 = new ArrayList<>();
            drugBean1 = new DrugBean();
            String jibing = cursor.getString(0);
            drugBean1.setTitle(jibing);
            Log.e("TAG", jibing);
            Cursor cursor2 = db1.rawQuery("select durgId from durgclass where jibing = ? ", new String[]{jibing});
            while (cursor2.moveToNext()) {
                String id = cursor2.getString(0);
                String[] stringarr = id.split(",");
                for (String s : stringarr) {
                    Log.e("TAG", s);
                    Cursor cursor3 = db1.rawQuery("select 药品名称,作用类别,Image1 from med where id= ?", new String[]{s});
                    while (cursor3.moveToNext()) {
                        try {
                            String drug_name = cursor3.getString(0);
                            String drug_leibie = cursor3.getString(1);
                            String dur_pic = cursor3.getString(2);
                            if (drug_leibie.isEmpty()) {
                                drug_leibie = " ";
                            }
//                            Log.e("TAG", drug_name);
//                            Log.e("TAG", drug_leibie);
                            Log.e("TAG", dur_pic);
                            drugItem = new DrugItem(dur_pic, drug_name, drug_leibie);
                            drugItems1.add(drugItem);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        finally {
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

//        //药的种类
//        DrugItem drug1 = new DrugItem("http://ppwkqlsve.bkt.clouddn.com/drug1.jpg", "琥珀还睛丸", "补益肝肾，清热明目。用于肝肾两亏，虚火上炎。");
//        DrugItem drug2 = new DrugItem("http://ppwkqlsve.bkt.clouddn.com/drug2.jpg", "荷叶丸", "凉血止血。用于血热所致的咯血、衄血、尿血、便血、崩漏。");
//        DrugItem drug3 = new DrugItem("http://ppwkqlsve.bkt.clouddn.com/drug3.jpg", "河车大造丸", "滋阴清热，补肾益肺。用于肺肾两亏，虚劳咳嗽，骨蒸潮热，盗汗遗精，腰膝酸软。");
//        DrugItem drug4 = new DrugItem("http://ppwkqlsve.bkt.clouddn.com/drug4.jpg", "硝苯地平控释片", "1．高血压 2．冠心病 慢性稳定型心绞痛(劳累性心绞痛)");
//        DrugItem drug5 = new DrugItem("http://ppwkqlsve.bkt.clouddn.com/drug5.jpg", "阿卡波糖片", "配合饮食控制治疗糖尿病。");
//        DrugItem drug6 = new DrugItem("http://ppwkqlsve.bkt.clouddn.com/drug6.jpg", "尼莫地平片", "可预防和治疗由于动脉瘤性蛛网膜下腔出血后脑血管痉挛引起的缺血性神经损伤。");
//病的种类
//        List<DrugItem> drugItems1 = new ArrayList<>();
//        drugItems1.add(drug1);
//        drugItems1.add(drug3);
//        drugItems1.add(drug4);
//        drugItems1.add(drug2);
//        List<DrugItem> drugItems2 = new ArrayList<>();
//        drugItems2.add(drug5);
//        drugItems2.add(drug3);
//        drugItems2.add(drug2);
//        drugItems2.add(drug2);
//        List<DrugItem> drugItems3 = new ArrayList<>();
//        drugItems3.add(drug4);
//        drugItems3.add(drug5);
//        drugItems3.add(drug3);
//        drugItems3.add(drug1);
//
//        DrugBean d1 = new DrugBean();
//        d1.setTitle("糖尿病");
//        d1.setmList(drugItems1);
//        DrugBean d2 = new DrugBean();
//        d2.setTitle("高血压");
//        d2.setmList(drugItems2);
//        DrugBean d3 = new DrugBean();
//        d3.setTitle("高血脂");
//        d3.setmList(drugItems3);
//        drugBeanList.add(d1);
//        drugBeanList.add(d2);
//        drugBeanList.add(d3);
//        drugBeanList.add(d1);
//        drugBeanList.add(d2);
//        drugBeanList.add(d3);
//        drugBeanList.add(d1);
//        drugBeanList.add(d2);
//        drugBeanList.add(d3);
//        drugBeanList.add(d1);
//        drugBeanList.add(d2);
//        drugBeanList.add(d3);
//        drugBeanList.add(d1);
//        drugBeanList.add(d2);
//        drugBeanList.add(d3);
//        drugBeanList.add(d1);
//        drugBeanList.add(d2);
//        drugBeanList.add(d3);
//        drugBeanList.add(d1);
//        drugBeanList.add(d2);
//        drugBeanList.add(d3);