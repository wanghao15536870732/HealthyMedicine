package com.example.zhongahiyi.healthy.view.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.bean.drug.DrugBean;

import java.util.List;

public class  LeftAdapter extends BaseQuickAdapter<DrugBean> {
    private int selectPos = 0;
    public LeftAdapter( List<DrugBean> data) {
        super( R.layout.item_main_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugBean bean) {
        if(selectPos == helper.getAdapterPosition()){
            helper.setVisible(R.id.item_main_left_bg,true);
            helper.convertView.setBackgroundColor( Color.parseColor("#FFFFFF"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#40a5f3"));
        }else{
            helper.convertView.setBackgroundColor(Color.parseColor("#f7f7f7"));
            helper.setTextColor(R.id.item_main_left_type, Color.parseColor("#333333"));
            helper.setVisible(R.id.item_main_left_bg,false);
        }
        helper.setText(R.id.item_main_left_type,bean.getTitle());
    }


    public int getSelectPos() {
        return selectPos;
    }

    public void setSelectPos(int selectPos) {
        this.selectPos = selectPos;
    }
}
