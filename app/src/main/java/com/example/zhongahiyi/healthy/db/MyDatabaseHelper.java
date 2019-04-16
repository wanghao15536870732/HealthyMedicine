//package com.example.zhongahiyi.healthy.db;
//
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.widget.Toast;
//
//public class MyDatabaseHelper extends SQLiteOpenHelper {
//    private Context mcontext;
//    public static final String CREAK_REMIND="create table haha(dsa)";
//    public MyDatabaseHelper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//        Toast.makeText(mcontext,"创建数据库成功！",Toast.LENGTH_SHORT).show();
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREAK_REMIND);
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//}
