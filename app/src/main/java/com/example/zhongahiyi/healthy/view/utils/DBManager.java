package com.example.zhongahiyi.healthy.view.utils;

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

    public SQLiteDatabase getDatabase(Context context) throws IOException {
        File file = new File("/data/data/com.example.zhongahiyi.healthy/database/med.db");
        SQLiteDatabase sqLiteDatabase;

        if (file.exists()){
            return SQLiteDatabase.openOrCreateDatabase(file, null);
        }else {
            File dir = new File("/data/data/com.example.zhongahiyi.healthy/database/med.db");
            dir.mkdir();
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("med.db");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];//按字节存入
            int count = 0;
            while ((count = inputStream.read(buffer)) > 0){
                fileOutputStream.write(buffer, 0, count);
            }
            //关闭输入输出流
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
        }

        return getDatabase(context);//存储完成之后从新调用这个函数返回数据库文件
    }

    //                String ingredient = cursor.getString( cursor.getColumnIndex( "成份" ) );
//                String behavior = cursor.getString( cursor.getColumnIndex( "性状" ) );
//                String function = cursor.getString( cursor.getColumnIndex( "作用类别" ) );
//                String majorfunction = cursor.getString( cursor.getColumnIndex( "适应症/功能主治" ) );
//                String standard = cursor.getString( cursor.getColumnIndex( "规格" ) );
//                String taboo = cursor.getString( cursor.getColumnIndex( "禁忌" ) );
//                String attention = cursor.getString( cursor.getColumnIndex( "注意事项" ));
//                String reaction = cursor.getString( cursor.getColumnIndex( "不良反应" ));
//                String reaction1 = cursor.getString( cursor.getColumnIndex( "药物相互作用" ));
//                String reaction2 = cursor.getString( cursor.getColumnIndex( "有效期" ));
//                String reaction3 = cursor.getString( cursor.getColumnIndex( "生产企业" ));
}
