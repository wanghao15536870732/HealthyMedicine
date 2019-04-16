package com.example.zhongahiyi.healthy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.activity.SettintActivity;
import com.example.zhongahiyi.healthy.bean.info.Info_item;

import java.util.List;

import static android.app.PendingIntent.getActivity;

public class Info_Recyle_Adapter extends RecyclerView.Adapter<Info_Recyle_Holder> {

    private List<Info_item> info_items;
    private Context context;

    private int ok = 1;

    public Info_Recyle_Adapter(List<Info_item> info_items, Context context) {
        this.info_items = info_items;
        this.context = context;
    }

    @NonNull
    @Override
    public Info_Recyle_Holder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_items, viewGroup, false);
        Info_Recyle_Holder info_recyle_holder = new Info_Recyle_Holder(view);
        info_recyle_holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewGroup.getContext(), SettintActivity.class);
//                Bundle bundle = new Bundle(  );
//                bundle.putString( DrugDetailActivity.EXTRA_NAME, holder.title.toString());
                viewGroup.getContext().startActivity( intent );
            }
        } );
        return info_recyle_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Info_Recyle_Holder info_recyle_holder, int i) {

        info_recyle_holder.bindView(info_items.get(i));
        info_recyle_holder.imageView.findViewById(R.id.pitch_m).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ok == 1) {
                    info_recyle_holder.imageView.setImageResource(R.drawable.ic_pitch_on);
                    ok = ok * (-1);
                }
                else{
                    info_recyle_holder.imageView.setImageResource(R.drawable.ic_pitch_off);
                    ok = ok * (-1);
                }
            }
        });
//        info_recyle_holder.repair_mess.findViewById(R.id.respair_mes).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               startActivity(new Intent());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return info_items.size();
    }
}