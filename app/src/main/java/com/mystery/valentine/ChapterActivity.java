package com.mystery.valentine;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.mystery.valentine.Chapter.Chapter1_1;
import com.mystery.valentine.Chapter.Chapter1_2;
import com.mystery.valentine.Chapter.Chapter1_3;
import com.mystery.valentine.Chapter.Chapter2_1;
import com.mystery.valentine.Chapter.Chapter2_2;
import com.mystery.valentine.Chapter.Chapter2_3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint({"NewApi", "InflateParams"})
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

public class ChapterActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chapter);
        init();
        checkChapterDone();
    }

    private void init() {
        sp = getSharedPreferences("chapter", MODE_PRIVATE);
        findViewById(R.id.chapter1_1_tv).setOnClickListener(this);
        findViewById(R.id.chapter1_2_tv).setOnClickListener(this);
        findViewById(R.id.chapter1_3_tv).setOnClickListener(this);
        findViewById(R.id.chapter2_1_tv).setOnClickListener(this);
        findViewById(R.id.chapter2_2_tv).setOnClickListener(this);
        findViewById(R.id.chapter2_3_tv).setOnClickListener(this);
    }

    /*检查章节完成度*/
    private void checkChapterDone() {
        if (sp.getBoolean("chapter1_1", false)) {
            findViewById(R.id.chapter1_1_iv).setBackgroundResource(R.mipmap.btn_background);
        }
        if (sp.getBoolean("chapter1_2", false)) {
            findViewById(R.id.chapter1_2_iv).setBackgroundResource(R.mipmap.btn_background);
        }
        if (sp.getBoolean("chapter1_3", false)) {
            findViewById(R.id.chapter1_3_iv).setBackgroundResource(R.mipmap.btn_background);
        }
        if (sp.getBoolean("chapter2_1", false)) {
            findViewById(R.id.chapter2_1_iv).setBackgroundResource(R.mipmap.btn_background);
        }
        if (sp.getBoolean("chapter2_2", false)) {
            findViewById(R.id.chapter2_2_iv).setBackgroundResource(R.mipmap.btn_background);
        }
        if (sp.getBoolean("chapter2_3", false)) {
            findViewById(R.id.chapter2_3_iv).setBackgroundResource(R.mipmap.btn_background);
        }
    }

    public void back(View view) {
        Intent toAbout = new Intent(ChapterActivity.this, HomeActivity.class);
        startActivity(toAbout, ActivityOptions.makeSceneTransitionAnimation(this, view, "chapterShare").toBundle());
        finish();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chapter1_1_tv:
                if (sp.getBoolean("chapter1_1", false)) {
                    startActivity(new Intent(ChapterActivity.this, Chapter1_1.class));
                    finish();
                }
                break;
            case R.id.chapter1_2_tv:
                if (sp.getBoolean("chapter1_2", false)) {
                    startActivity(new Intent(ChapterActivity.this, Chapter1_2.class));
                    finish();
                }
                break;
            case R.id.chapter1_3_tv:
                if (sp.getBoolean("chapter1_3", false)) {
                    startActivity(new Intent(ChapterActivity.this, Chapter1_3.class));
                    finish();
                }
                break;
            case R.id.chapter2_1_tv:
                if (sp.getBoolean("chapter2_1", false)) {
                    startActivity(new Intent(ChapterActivity.this, Chapter2_1.class));
                    finish();
                }
                break;
            case R.id.chapter2_2_tv:
                if (sp.getBoolean("chapter2_2", false)) {
                    startActivity(new Intent(ChapterActivity.this, Chapter2_2.class));
                    finish();
                }
                break;
            case R.id.chapter2_3_tv:
                if (sp.getBoolean("chapter2_3", false)) {
                    startActivity(new Intent(ChapterActivity.this, Chapter2_3.class));
                    finish();
                }
                break;
            default:
                break;
        }
    }
}
