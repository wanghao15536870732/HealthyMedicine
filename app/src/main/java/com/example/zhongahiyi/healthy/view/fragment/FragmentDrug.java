package com.example.zhongahiyi.healthy.view.fragment;

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
import com.example.zhongahiyi.healthy.view.adapter.LeftAdapter;
import com.example.zhongahiyi.healthy.view.adapter.RightAdapter;
import com.example.zhongahiyi.healthy.view.bean.drug.DrugBean;
import com.example.zhongahiyi.healthy.view.bean.drug.DrugItem;
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
        bundle.putString( "text", text );
        fragmentCommon.setArguments( bundle );
        return fragmentCommon;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_drug, container, false );
        mLeftRvRecyclerView = (RecyclerView) view.findViewById( R.id.main_left_rv );
        mRightRvRecyclerView = (RecyclerView) view.findViewById( R.id.main_right_rv );
        initData();
        leftAdapter = new LeftAdapter( drugBeanList );
        rightAdapter = new RightAdapter( listBeanList );
        mLeftRvRecyclerView.setAdapter(leftAdapter);
        mRightRvRecyclerView.setAdapter(rightAdapter);
        mLeftRvRecyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        mRightRvRecyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        mLeftRvRecyclerView.addOnItemTouchListener( new SimpleClickListener() {
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
        } );
        return view;
    }

    private void initData() {
        drugBeanList = new ArrayList<>();
        listBeanList = new ArrayList<>();

        //药的种类
        DrugItem drug1 = new DrugItem(R.drawable.drug1,"琥珀还睛丸","补益肝肾，清热明目。用于肝肾两亏，虚火上炎。");
        DrugItem drug2 = new DrugItem(R.drawable.drug2,"荷叶丸","凉血止血。用于血热所致的咯血、衄血、尿血、便血、崩漏。");
        DrugItem drug3 = new DrugItem(R.drawable.drug3,"河车大造丸","滋阴清热，补肾益肺。用于肺肾两亏，虚劳咳嗽，骨蒸潮热，盗汗遗精，腰膝酸软。");
        DrugItem drug4 = new DrugItem(R.drawable.drug4,"硝苯地平控释片","1．高血压 2．冠心病 慢性稳定型心绞痛(劳累性心绞痛)");
        DrugItem drug5 = new DrugItem(R.drawable.drug5,"阿卡波糖片","配合饮食控制治疗糖尿病。");
        DrugItem drug6 = new DrugItem(R.drawable.drug6,"尼莫地平片","可预防和治疗由于动脉瘤性蛛网膜下腔出血后脑血管痉挛引起的缺血性神经损伤。");

        //病的种类
        List<DrugItem> drugItems1 = new ArrayList<>(  );
        drugItems1.add( drug1 );
        drugItems1.add( drug3 );
        drugItems1.add( drug4);
        drugItems1.add( drug2 );
        List<DrugItem> drugItems2 = new ArrayList<>(  );
        drugItems2.add( drug5 );
        drugItems2.add( drug3 );
        drugItems2.add( drug2);
        drugItems2.add( drug2 );
        List<DrugItem> drugItems3 = new ArrayList<>(  );
        drugItems3.add( drug4 );
        drugItems3.add( drug5 );
        drugItems3.add( drug3);
        drugItems3.add( drug6 );

        DrugBean d1 = new DrugBean();
        d1.setTitle( "糖尿病" );
        d1.setmList( drugItems1 );
        DrugBean d2 = new DrugBean();
        d2.setTitle( "高血压" );
        d2.setmList( drugItems2 );
        DrugBean d3 = new DrugBean();
        d3.setTitle( "高血脂" );
        d3.setmList( drugItems3);

        drugBeanList.add( d1 );
        drugBeanList.add( d2 );
        drugBeanList.add( d3 );
        listBeanList.addAll( drugBeanList.get( 0 ).getmList() );
    }
}