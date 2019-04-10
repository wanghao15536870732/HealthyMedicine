package com.example.zhongahiyi.healthy.view.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.view.fragment.FragmentDrug;
import com.example.zhongahiyi.healthy.view.fragment.FragmentInfo;
import com.example.zhongahiyi.healthy.view.fragment.FragmentMain;
import com.example.zhongahiyi.healthy.view.fragment.FragmentNews;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import org.michaelbel.bottomsheet.BottomSheet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private static final int REQUEST_CODE = 3;
    public static final int REQUEST_IMAGE = 5;
    private TabView tabView;
    private FlowingDrawer mDrawer;
    private ImageView mAvator, ic_back;
    private TextView info_name;
    private LinearLayout drugAlart, drugscan, collect, drugHistory, feedBack;
    private CircleImageView menuAvator;
    private CircleImageView userAvator;
    private Uri imagUri;
    private LinearLayout medialart;

    private int[] items = new int[]{
            R.string.take_photo,
            R.string.upload_from_phone
    };
    private int[] icons = new int[]{
            R.drawable.ic_take_photo,
            R.drawable.ic_albm
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabView = (TabView) findViewById(R.id.tabView);
        mDrawer = (FlowingDrawer) findViewById(R.id.drawerlayout);
        menuAvator = (CircleImageView) findViewById(R.id.menu_avator);
        drugAlart = findViewById(R.id.durg_alart);
        drugHistory = findViewById(R.id.drug_history);
        drugscan = findViewById(R.id.drug_scan);
        collect = findViewById(R.id.drug_collect);
        feedBack = findViewById(R.id.feedback);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        userAvator = findViewById(R.id.toolbar_user);
        info_name = findViewById(R.id.info_name);
        mAvator = (ImageView) findViewById(R.id.avator);
        ic_back = (ImageView) findViewById(R.id.back_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ZXingLibrary.initDisplayOpinion(this);
        initTabView();
        initDrawer();
        menuAvator.setOnClickListener(this);
        mAvator.setOnClickListener(this);
        ic_back.setOnClickListener(this);
        info_name.setOnClickListener(this);
        drugHistory.setOnClickListener(this);
        drugAlart.setOnClickListener(this);
        drugscan.setOnClickListener(this);
        collect.setOnClickListener(this);
        feedBack.setOnClickListener(this);
        File file = new File(getExternalCacheDir(), "ChatHead.JPEG");
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            menuAvator.setImageBitmap(bitmap);
            userAvator.setImageBitmap(bitmap);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, 4);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1) {
            if (resultCode == -1) {
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imagUri));
                    menuAvator.setImageBitmap(bitmap);
                    userAvator.setImageBitmap(bitmap);
                    saveBitmapFile(bitmap);
                    Toast.makeText(MainActivity.this, "你已成功修改头像", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        if (requestCode == 2) {
            if (resultCode == -1) {
                if (Build.VERSION.SDK_INT >= 19) {
                    handleImageOnKitKat(data);
                } else
                    handleImageBeforeKitKat(data);
            }
        }
        if (requestCode == REQUEST_CODE) {
            if (resultCode == -1) {
                if (null != data) {
                    Bundle bundle = data.getExtras();
                    if (bundle == null) {
                        return;
                    }
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                        String result = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(MainActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                        Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                ContentResolver cr = getContentResolver();
                try {
                    Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图片
                    CodeUtils.analyzeBitmap(mBitmap.toString(), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(MainActivity.this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });

                    if (mBitmap != null) {
                        mBitmap.recycle();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        Log.e("test", uri.toString());
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }


    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            menuAvator.setImageBitmap(bitmap);
            userAvator.setImageBitmap(bitmap);
            saveBitmapFile(bitmap);
            Toast.makeText(MainActivity.this, "你已成功修改头象", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "获取图片失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void bottomSheet_show() {
        BottomSheet.Builder builder = new BottomSheet.Builder(this);
        builder.setTitle("更换头像");
        builder.setItems(items, icons, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                    try {
                        if (outputImage.exists()) {
                            outputImage.delete();
                        }
                        outputImage.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (Build.VERSION.SDK_INT >= 24) {
                        imagUri = FileProvider.getUriForFile(MainActivity.this,
                                "com.example.cameraalbumtest.fileprovider", outputImage);
                    } else {
                        imagUri = Uri.fromFile(outputImage);
                    }
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imagUri);
                    startActivityForResult(intent, TAKE_PHOTO);
                } else if (which == 1) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission
                            .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    } else {
                        openAlbum();
                    }
                }
            }
        });
        builder.show();
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(MainActivity.this, "你没有权限！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    private void initDrawer() {

        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mDrawer.setOnDrawerStateChangeListener(new ElasticDrawer.OnDrawerStateChangeListener() {
            @Override
            public void onDrawerStateChange(int oldState, int newState) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED");
                }
            }

            @Override
            public void onDrawerSlide(float openRatio, int offsetPixels) {
                Log.i("MainActivity", "openRatio=" + openRatio + " ,offsetPixels=" + offsetPixels);
            }
        });

    }

    private void initTabView() {
        List<TabViewChild> tabViewChildList = new ArrayList<>();
        TabViewChild tabViewChildMain = new TabViewChild(R.drawable.tab_main, R.drawable.tab_main1, "首页",
                FragmentMain.newInstance("首页"));
        TabViewChild tabViewChildDrug = new TabViewChild(R.drawable.tab_drug, R.drawable.tab_drug1, "分类",
                FragmentDrug.newInstance("分类"));
        TabViewChild tabViewChildNews = new TabViewChild(R.drawable.tab_news, R.drawable.tab_news1, "资讯",
                FragmentNews.newInstance("资讯"));
        TabViewChild tabViewChildInfo = new TabViewChild(R.drawable.tab_info, R.drawable.tab_info1, "我的",
                FragmentInfo.newInstance("我的"));
        tabViewChildList.add(tabViewChildMain);
        tabViewChildList.add(tabViewChildDrug);
        tabViewChildList.add(tabViewChildNews);
        tabViewChildList.add(tabViewChildInfo);
        //end add data
        tabView.setTabViewDefaultPosition(0);
        tabView.setTabViewChild(tabViewChildList, getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int position, ImageView currentImageIcon, TextView currentTextView) {

            }
        });
    }

    public void saveBitmapFile(Bitmap bitmap) {
        File file = new File(getExternalCacheDir(), "ChatHead.JPEG");//将要保存图片的路径
        try {
            if (file.exists()) {
                file.delete();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void alert_edit() {
        final EditText et = new EditText(this);
        new AlertDialog.Builder(this).setTitle("更改名字:")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        info_name.setText(et.getText().toString().replaceAll("\\s*", ""));
                        Toast.makeText(getApplicationContext(), "名称修改成功！", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("取消", null).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.avator:
                mDrawer.openMenu();
                break;
            case R.id.back_menu:
                mDrawer.closeMenu();
                break;
            case R.id.menu_avator:
                bottomSheet_show();
                break;
            case R.id.info_name:
                alert_edit();
                break;
            case R.id.drug_history:

                break;
            case R.id.durg_alart:

                break;
            case R.id.drug_scan:
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.drug_collect:

                break;
            case R.id.feedback:

                break;
            default:
                break;
        }
    }
}
