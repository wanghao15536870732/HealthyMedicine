package com.example.zhongahiyi.healthy.view.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

public class DBManager {
    private String DB_NAME = "med.db";
    private Context mContext;

    public DBManager(Context mContext) {
        this.mContext = mContext;
    }

    public SQLiteDatabase getDatabase(Context context) {
        File file = new File("/data/data/com.example.zhongahiyi.healthy/databases/med.db");
        SQLiteDatabase sqLiteDatabase;
        if (!file.exists()){
            try {
                AssetManager assetManager = context.getAssets();
                InputStream inputStream = assetManager.open("med.db");
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024];//按字节存入
                int count = 0;
                while ((count = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, count);
                }
                //关闭输入输出流
                fileOutputStream.flush();
                fileOutputStream.close();
                inputStream.close();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(file, null);
    }
}
