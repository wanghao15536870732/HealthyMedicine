package com.example.zhongahiyi.healthy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBManager {
    private String DB_NAME = "med.db";
    private Context mContext;
    private String packName = "com.example.zhongahiyi.healthy";

    /*选择题的集合*/
    public DBManager(Context mContext) {
        this.mContext = mContext;
    }

//    public SQLiteDatabase getDatabase (String packName) {
//        String dbPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/databases/" + DB_NAME;
//        if (!new File(dbPath).exists()) {
//            try {
//                boolean flag = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/databases/").mkdirs();
//                boolean newFile = new File(dbPath).createNewFile();
//                try {
//                    FileOutputStream out = new FileOutputStream(dbPath);
//                    InputStream in = mContext.getAssets().open("med.db");
//                    byte[] buffer = new byte[1024];
//                    int readBytes = 0;
//                    while ((readBytes = in.read(buffer)) != -1)
//                        out.write(buffer, 0, readBytes);
//                    in.close();
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
//    }
//}

    public SQLiteDatabase getDatabase() {
        String dbPath = "/data/data/" + packName
                + "/databases/" + DB_NAME;
        if (!new File(dbPath).exists()) {
            try {
                File file2 = new File("/data/data/com.example.zhongahiyi.healthy/databases");
                file2.mkdirs();
                Process p = Runtime.getRuntime().exec("chmod 777 " + "/data/data/com.example.zhongahiyi.healthy/databases");
                int status = p.waitFor();
                if (status == 0) {
                    //chmod succeed
                    Log.e("TAG", "Succed");
                } else {
                    //chmod failed
                    Log.e("TAG", "Fail");
                }
                FileOutputStream out = new FileOutputStream(dbPath);
                try {
                    InputStream in = mContext.getAssets().open("med.db");
                    byte[] buffer = new byte[1024];
                    int readBytes = 0;
                    while ((readBytes = in.read(buffer)) != -1)
                        out.write(buffer, 0, readBytes);
                    in.close();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }
}
