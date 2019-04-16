package com.example.zhongahiyi.healthy.activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.db.DBManager;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SynthesizerListener;

import org.michaelbel.bottomsheet.BottomSheet;

public class DrugDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "drugs_title";
    public static final String EXTRA_IMG = "drugs_img";
    private TextView drug_content1, drug_content2, drug_content3;
    private FloatingActionButton floatingActionButton, changevoicebuttton;
    private String VOICE = null;

    private String[] items = new String[]{
            "小燕 女声 青年 中英文",
            "小宇 男声 青年 中英文",
            "亨利 男声 青年 英文",
            "玛丽 女声 青年 英文",
            "小梅 女声 青年 中英文粤语",
            "小莉 女声 青年 中英文台湾普通话",
            "小蓉 女声 青年 汉语四川话",
            "小芸 女声 青年 汉语东北话",
            "小强 男声 青年 汉语湖南话",
            "小莹 女声 青年 汉语陕西话",
            "楠楠 女声 童年 汉语普通话",
            "老孙 男声 老年 汉语普通话",
            "小新 男声 童年 汉语普通话",
            "取消声音切换",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//设置透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//设置透明导航栏
        }
        Intent intent = getIntent();
        final String newsTitle = intent.getStringExtra(EXTRA_NAME);
        final String drugsImg = intent.getStringExtra(EXTRA_IMG);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.news_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(newsTitle);
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Glide.with(DrugDetailActivity.this).load(drugsImg).into(imageView);
        drug_content1 = (TextView) findViewById(R.id.drug_content1);
        drug_content2 = (TextView) findViewById(R.id.drug_content2);
        drug_content3 = (TextView) findViewById(R.id.drug_content3);
        initContent(newsTitle);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.speech_outer);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpeechSynthesizer(drug_content1.getText().toString() + drug_content2.getText().toString() + drug_content3.getText().toString());
            }
        });
        changevoicebuttton = (FloatingActionButton) findViewById(R.id.change_voice);
        changevoicebuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheet.Builder builder = new BottomSheet.Builder(DrugDetailActivity.this);
                builder.setTitle("切换声音");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                VOICE = "xiaoyan";
                                Toast.makeText(DrugDetailActivity.this, "已选择 小燕 女声 青年 中英文", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                VOICE = "xiaoyu";
                                Toast.makeText(DrugDetailActivity.this, "已选择 小宇 男声 青年 中英文", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                VOICE = "henry";
                                Toast.makeText(DrugDetailActivity.this, "已选择 亨利 男声 青年 英文", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                VOICE = "vimary";
                                Toast.makeText(DrugDetailActivity.this, "已选择 玛丽 女声 青年 英文", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                VOICE = "xiaomei";
                                Toast.makeText(DrugDetailActivity.this, "已选择 小梅 女声 青年 中英文粤语", Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                VOICE = "vixl";
                                Toast.makeText(DrugDetailActivity.this, "已选择 小莉 女声 青年 中英文台湾普通话", Toast.LENGTH_SHORT).show();
                                break;
                            case 6:
                                VOICE = "xiaorong";
                                Toast.makeText(DrugDetailActivity.this, "已选择 小蓉 女声 青年 汉语四川话", Toast.LENGTH_SHORT).show();
                                break;
                            case 7:
                                VOICE = "xiaokun";
                                Toast.makeText(DrugDetailActivity.this, "已选择 小芸 女声 青年 汉语东北话", Toast.LENGTH_SHORT).show();
                                break;
                            case 8:
                                VOICE = "xiaoqiang";
                                Toast.makeText(DrugDetailActivity.this, "已选择 小强 男声 青年 汉语湖南话", Toast.LENGTH_SHORT).show();
                                break;
                            case 9:
                                VOICE = "vixying";
                                Toast.makeText(DrugDetailActivity.this, "已选择 小莹 女声 青年 汉语陕西话", Toast.LENGTH_SHORT).show();
                                break;
                            case 10:
                                VOICE = "nannan";
                                Toast.makeText(DrugDetailActivity.this, "已选择 楠楠 女声 童年 汉语普通话", Toast.LENGTH_SHORT).show();
                                break;
                            case 11:
                                VOICE = "vils";
                                Toast.makeText(DrugDetailActivity.this, "已选择 老孙 男声 老年 汉语普通话", Toast.LENGTH_SHORT).show();
                                break;
                            case 12:
                                VOICE = "xiaoxin";
                                Toast.makeText(DrugDetailActivity.this, "已选择 小新 男声 童年 汉语普通话", Toast.LENGTH_SHORT).show();
                                break;
                            case 13:
                                Toast.makeText(DrugDetailActivity.this, "已取消声音切换", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                }).show();

            }
        });

    }


    @SuppressLint("SetTextI18n")
    private void initContent(String name) {
        DBManager dbManager = new DBManager(MainActivity.getContext());
        SQLiteDatabase db = dbManager.getDatabase();
        Cursor cursor = db.rawQuery("select 说明书标题,药品类型,药品名称,药品汉语拼音,药品商品名称,药品英文名称" +
                ",成份,性状, 功能主治,用法用量,禁忌,不良反应,注意事项,药物相互作用,药理作用,贮藏,包装,有效期,执行标准,批准文号," +
                "说明书修订日期,生产企业,备注 from med where 药品名称 = ?", new String[]{name});
        while (cursor.moveToNext()) {
            String res1=cursor.getString(0);
            String res2=cursor.getString(1);
            String res3=cursor.getString(2);
            String res4=cursor.getString(3);
            String res5=cursor.getString(4);
            String res6=cursor.getString(5);
            String res7=cursor.getString(6);
            String res8=cursor.getString(7);
            String res9=cursor.getString(8);
            String res10=cursor.getString(9);
            String res11=cursor.getString(10);
            String res12=cursor.getString(11);
            String res13=cursor.getString(12);
            String res14=cursor.getString(13);
            String res15=cursor.getString(14);
            String res16=cursor.getString(15);
            String res17=cursor.getString(16);
            String res18=cursor.getString(17);
            String res19=cursor.getString(18);
            String res20=cursor.getString(19);
            String res21=cursor.getString(20);
            String res22=cursor.getString(21);
            String res23=cursor.getString(22);
            drug_content1.setText("【说明书标题】"+res1+"\n"+"【药品类型】"+res2+"\n"+"【药品名称】"
                    +res3+"\n"+"【药品汉语拼音】"+res4+"\n"+"【药品商品名称】"+res5+"\n"+"【药品英文名称】"+res6+"\n");
            String result="【说明书标题】"+res1+"\n"+"【药品类型】"+res2+"\n"+"【药品名称】"
                    +res3+"\n"+"【药品汉语拼音】"+res4+"\n"+"【药品商品名称】"+res5+"\n"+"【药品英文名称】"+res6+"\n";
//            Log.e("TAG",result );
            drug_content2.setText("【成分】"+res7+"\n"+"【性状】"+res8+"\n"+"【功能主治】"
                    +res9+"\n"+"【用法用量】"+res10+"\n"+"【禁忌】"+res11+"\n"+"【不良反应】"+res12+"\n"
            +"【注意事项】"+res13+"\n"+"【药物相互作用】"+res14+"\n"+"【药理作用】"+res15+"\n");
            drug_content3.setText("【贮藏】"+res16+"\n"+"【包装】"+res17+"\n"+"【有效期】"
                    +res18+"\n"+"【执行标准】"+res19+"\n"+"【批准文号】"+res20+"\n"+"【说明书修订日期】"+res21+"\n"
                    +"【生产企业】"+res22+"\n"+"【备注】"+res23+"\n");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /*-------------------------------语音合成---------)-----------------*/
    public void SpeechSynthesizer(String text) {
        //1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer(DrugDetailActivity.this, null);

        /**
         2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
         *
         */
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        if (VOICE != null) {
            mTts.setParameter(SpeechConstant.VOICE_NAME, VOICE);//设置发音人
        } else {
            mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        }

        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速
        //设置合成音调
        mTts.setParameter(SpeechConstant.PITCH, "50");
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
//        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
//        boolean isSuccess = mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts2.wav");
//        Toast.makeText(MainActivity.this, "语音合成 保存音频到本地：\n" + isSuccess, Toast.LENGTH_LONG).show();
        //3.开始合成
        int code = mTts.startSpeaking(text, mSynListener);

        if (code != ErrorCode.SUCCESS) {
            if (code == ErrorCode.ERROR_COMPONENT_NOT_INSTALLED) {
                //上面的语音配置对象为初始化时：
                Toast.makeText(DrugDetailActivity.this, "语音组件未安装", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(DrugDetailActivity.this, "语音合成失败,错误码: " + code, Toast.LENGTH_LONG).show();
            }
        }
    }

    //合成监听器
    private SynthesizerListener mSynListener = new SynthesizerListener() {
        //会话结束回调接口，没有错误时，error为null
        public void onCompleted(SpeechError error) {

        }

        //缓冲进度回调
        //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        //开始播放
        public void onSpeakBegin() {
        }

        //暂停播放
        public void onSpeakPaused() {
        }

        //播放进度回调
        //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
        }

        //恢复播放回调接口
        public void onSpeakResumed() {
        }

        //会话事件回调接口
        public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {
        }
    };


    @Override
    protected void onStop() {
        super.onStop();
        if (SpeechSynthesizer.getSynthesizer() != null && SpeechSynthesizer.getSynthesizer().isSpeaking()) {
            SpeechSynthesizer.getSynthesizer().stopSpeaking();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (SpeechSynthesizer.getSynthesizer() != null) {
            SpeechSynthesizer.getSynthesizer().destroy();
        }
    }
}
