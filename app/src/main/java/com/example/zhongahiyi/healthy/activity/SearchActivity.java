package com.example.zhongahiyi.healthy.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zhongahiyi.healthy.R;
import com.example.zhongahiyi.healthy.adapter.SearchAdapter;
import com.example.zhongahiyi.healthy.bean.DictationResult;
import com.example.zhongahiyi.healthy.bean.main.Search_item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private List<Search_item> search_items;
    private Context context;
    private ImageView delte_image;
    private EditText editText;
    private ImageView voice;
    //有动画效果
    private RecognizerDialog iatDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_ill);
        recyclerView = (RecyclerView) findViewById(R.id.search_recyle);
        delte_image = (ImageView) findViewById(R.id.ivDeleteText);
        editText = (EditText) findViewById(R.id.etSearch);

        initData();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    delte_image.setVisibility(View.GONE);//当文本框为空时，则叉叉消失
                    voice.setVisibility(View.VISIBLE);
                } else {
                    delte_image.setVisibility(View.VISIBLE);//当文本框不为空时，出现叉叉
                    voice.setVisibility(View.GONE);
                }

            }
        });

        delte_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);
        searchAdapter = new SearchAdapter(search_items);
        recyclerView.setAdapter(searchAdapter);
        voice = (ImageView) findViewById(R.id.voice);
        voice.setOnClickListener(this);
    }

    private void initData() {

        search_items = new ArrayList<>();
        search_items.add(new Search_item("小儿复方氨酚烷胺片",
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3326255130,820597129&fm=26&gp=0.jpg","4398浏览"));
        search_items.add(new Search_item("阿司匹林咀嚼片",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1261517869,3726326705&fm=26&gp=0.jpg","3987浏览"));
        search_items.add(new Search_item("八维钙锌片",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2200383986,3703615430&fm=26&gp=0.jpg","3456浏览"));
        search_items.add(new Search_item("奥利司他胶囊",
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3467540420,1776924285&fm=26&gp=0.jpg","3211浏览"));
        search_items.add(new Search_item("阿司匹林肠溶片",
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=490773985,300761946&fm=26&gp=0.jpg","2356浏览"));
        search_items.add(new Search_item("小儿贝诺酯散",
                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3860829679,3043575767&fm=26&gp=0.jpg","2132浏览"));
        search_items.add(new Search_item("复方门冬维甘滴眼液",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1058352777,752497935&fm=26&gp=0.jpg","1875浏览"));
        search_items.add(new Search_item("消痔软膏",
                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1154457379,2384581142&fm=26&gp=0.jpg","1555浏览"));
        search_items.add(new Search_item("熟三七片",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2933365105,2003364841&fm=26&gp=0.jpg","1465浏览"));
        search_items.add(new Search_item("脚气散",
                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=962104076,1389848912&fm=26&gp=0.jpg","1403浏览"));

        // 呼吸道感染的
//        search_items.add(new Search_item("复方公英片",
//                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1955731555,2221150228&fm=15&gp=0.jpg","1244浏览"));
//        search_items.add(new Search_item("正柴胡饮胶囊",
//                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1812499512,3978969309&fm=15&gp=0.jpg","1104浏览"));
//        search_items.add(new Search_item("一清胶囊",
//                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2708735061,3065864722&fm=26&gp=0.jpg","998浏览"));
//        search_items.add(new Search_item("清热解毒糖浆",
//                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1147390287,1806191458&fm=26&gp=0.jpg","512浏览"));
//        search_items.add(new Search_item("忍冬感冒颗粒",
//                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2597146572,4115559005&fm=26&gp=0.jpg","133浏览"));
//        search_items.add(new Search_item("小儿解表止咳口服液",
//                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=4018844448,224012191&fm=26&gp=0.jpg","101浏览"));
//        search_items.add(new Search_item("抗感解毒口服液",
//                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=704620927,1882570401&fm=26&gp=0.jpg","76浏览"));
//        search_items.add(new Search_item("抗病毒片",
//                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3721513965,2154050893&fm=26&gp=0.jpg","12浏览"));


        /*-----------------------搜索药名----------------------------*/
//        search_items.add(new Search_item("感冒止咳颗粒",
//                "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=782204125,444992073&fm=26&gp=0.jpg","1109浏览"));
//        search_items.add(new Search_item("复方野菊感冒颗粒",
//                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=784658634,1268616583&fm=26&gp=0.jpg","1022浏览"));
//        search_items.add(new Search_item("感冒药片",
//                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3962188387,1858486715&fm=26&gp=0.jpg","773浏览"));
//        search_items.add(new Search_item("感冒止咳合剂",
//                "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1244887713,3935766873&fm=26&gp=0.jpg","443浏览"));
//        search_items.add(new Search_item("桑菊感冒合剂",
//                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1220957493,2044511380&fm=26&gp=0.jpg","328浏览"));
//        search_items.add(new Search_item("复方桑菊感冒片",
//                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=162090875,542244493&fm=26&gp=0.jpg","267浏览"));
//        search_items.add(new Search_item("感冒退烧片",
//                "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1305687884,399786319&fm=26&gp=0.jpg","229浏览"));
//        search_items.add(new Search_item("感冒舒颗粒",
//                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=516190467,684535442&fm=26&gp=0.jpg","67浏览"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.voice:
                voice_text();
                break;
            default:
                break;
        }
    }

    /*-------------------------------语音转文字--------------------------*/
    private void voice_text() {
        // 有交互动画的语音识别器
        iatDialog = new RecognizerDialog(SearchActivity.this, mInitListener);

        iatDialog.setListener(new RecognizerDialogListener() {
            String resultJson = "[";//放置在外边做类的变量则报错，会造成json格式不对（？）

            @SuppressLint("SetTextI18n")
            @Override
            public void onResult(RecognizerResult recognizerResult, boolean isLast) {
                System.out.println("-----------------   onResult   -----------------");
                if (!isLast) {
                    resultJson += recognizerResult.getResultString() + ",";
                } else {
                    resultJson += recognizerResult.getResultString() + "]";
                }

                if (isLast) {
                    //解析语音识别后返回的json格式的结果
                    Gson gson = new Gson();
                    List<DictationResult> resultList = gson.fromJson(resultJson,
                            new TypeToken<List<DictationResult>>() {
                            }.getType());
                    String result = "";
                    for (int i = 0; i < resultList.size() - 1; i++) {
                        result += resultList.get(i).toString();
                    }

                    if (editText.getText() != null) {
                        editText.setText(editText.getText().toString() + result.toString());
                    }
                    int offset = editText.getLineCount() * editText.getLineHeight();
                    if (offset > (editText.getHeight() - editText.getLineHeight() - 20)) {
                        editText.scrollTo(0, offset - editText.getHeight() + editText.getLineHeight() + 20);
                    }
                }
            }

            @Override
            public void onError(SpeechError speechError) {
                //自动生成的方法存根
                speechError.getPlainDescription(true);
            }
        });
        //开始听写，需将sdk中的assets文件下的文件夹拷入项目的assets文件夹下（没有的话自己新建）
        iatDialog.show();
    }

    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d("SearchActivituy", "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(SearchActivity.this, "初始化失败，错误码：" + code, Toast.LENGTH_SHORT).show();
            }
        }
    };
}
