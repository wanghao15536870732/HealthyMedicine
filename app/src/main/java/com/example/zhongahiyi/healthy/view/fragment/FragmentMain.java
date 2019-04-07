package com.example.zhongahiyi.healthy.view.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.utils.DBManager;

import java.io.IOException;

public class FragmentMain extends Fragment {
    TextView textView;
    private DBManager mDBManager;
    private SQLiteDatabase mSQLiteDatabase;

    public static FragmentMain newInstance(String text){
        FragmentMain fragmentCommon=new FragmentMain();
        Bundle bundle = new Bundle();
        bundle.putString("text",text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }
    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate( R.layout.fragment_info,container,false);
        textView= (TextView) view.findViewById(R.id.textView);
        textView.setText(getArguments().getString("text"));
        mDBManager = new DBManager(container.getContext());
        String[] columns = new String[]{
                "id",
                "药品名称",
                "成份",
                "性状",
                "作用类别",
                "适应症/功能主治",
                "规格",
                "禁忌",
                "注意事项",
                "不良反应",
                "药物相互作用",
                "有效期",
                "生产企业",
        };
        try{
            mSQLiteDatabase = mDBManager.getDatabase( getContext());
            Cursor cursor = mSQLiteDatabase.query( "med",columns,null,null,null,null,null);
            if(cursor.moveToFirst()){
                do{
                    String id = cursor.getString( cursor.getColumnIndex( "id" ) );
                    String name = cursor.getString( cursor.getColumnIndex( "药品名称" ) );
                    Log.e( "DbManager", name);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return view;
    }
}