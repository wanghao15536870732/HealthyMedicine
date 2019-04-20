package com.example.zhongahiyi.healthy.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.fragment.FragmentNews;
import com.example.zhongahiyi.healthy.fragment.FragmentDrug;
import com.example.zhongahiyi.healthy.fragment.FragmentInfo;
import com.example.zhongahiyi.healthy.fragment.FragmentMain;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IOnSearchClickListener, Toolbar.OnMenuItemClickListener{
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private TabView tabView;
    private FlowingDrawer mDrawer;
    private ImageView mAvator, ic_back,search;
    private CircleImageView menuAvator;
    private CircleImageView userAvator;
    private Uri imagUri;
    private SearchFragment searchFragment;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        userAvator = findViewById(R.id.toolbar_user);
        mAvator = (ImageView) findViewById(R.id.avator);
        ic_back = (ImageView) findViewById(R.id.back_menu);
        searchFragment = SearchFragment.newInstance();
        searchFragment.setOnSearchClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setOnMenuItemClickListener(this);
        initTabView();
        initDrawer();
        menuAvator.setOnClickListener(this);
        mAvator.setOnClickListener(this);
        ic_back.setOnClickListener(this);
        File file = new File(getExternalCacheDir(), "ChatHead.JPEG");
        if (file.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            menuAvator.setImageBitmap(bitmap);
            userAvator.setImageBitmap(bitmap);
        }
        requestPermissions();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
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
            default:
                break;
        }
    }

    //权限申请
    private void requestPermissions() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permission = ActivityCompat.checkSelfPermission( this,
                        Manifest.permission.RECORD_AUDIO );
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions( this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.WRITE_SETTINGS, Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.CAMERA}, 0x0010 );

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void OnSearchClick(String keyword) {
        startActivity(new Intent(MainActivity.this,SearchActivity.class));
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.item_search:
                searchFragment.showFragment(getSupportFragmentManager(), SearchFragment.TAG);
                break;
        }
        return true;
    }
}
