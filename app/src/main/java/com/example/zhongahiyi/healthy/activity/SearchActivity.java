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
                if(s.length() == 0){
                    delte_image.setVisibility(View.GONE);//当文本框为空时，则叉叉消失
                    voice.setVisibility(View.VISIBLE);
                }
                else {
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
        GridLayoutManager layoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(layoutManager);
        searchAdapter = new SearchAdapter(search_items);
        recyclerView.setAdapter(searchAdapter);
        voice = (ImageView) findViewById(R.id.voice);
        voice.setOnClickListener(this);
    }

    private void initData() {
        search_items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            search_items.add(new Search_item("小儿复方氨酚烷胺片",
                    "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3326255130,820597129&fm=26&gp=0.jpg"));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.voice:
                voice_text();
                break;
            default:
                break;
        }
    }

    /*-------------------------------语音转文字--------------------------*/
    private void voice_text(){
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
                        editText.setText( editText.getText().toString() + result.toString() );
                    }
                    int offset = editText.getLineCount() * editText.getLineHeight();
                    if(offset > (editText.getHeight() - editText.getLineHeight() - 20)){
                        editText.scrollTo(0,offset - editText.getHeight() + editText.getLineHeight() + 20);
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
