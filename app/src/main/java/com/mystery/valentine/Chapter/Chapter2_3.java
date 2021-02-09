package com.mystery.valentine.Chapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.mystery.valentine.AboutActivity;
import com.mystery.valentine.DesignView.ConfirmDialog;
import com.mystery.valentine.HomeActivity;
import com.mystery.valentine.R;
import com.mystery.valentine.Util.SharedPreferencesUtil;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Chapter2_3 extends AppCompatActivity {
    private Animation alpha;
    private TextView coverTv, keepTv;
    private ImageView walk_sheIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chapter_2_3);
        initView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            openDialog();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /*初始视图*/
    private void initView() {
        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        coverTv = findViewById(R.id.chapter2_3_cover_tv);
        walk_sheIv = findViewById(R.id.walk_she_iv);
        keepTv = findViewById(R.id.keep_tv);
        keepTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout(v);
            }
        });
    }

    /*返回弹窗*/
    private void openDialog() {
        final ConfirmDialog confirmDialog = new ConfirmDialog(this);
        Objects.requireNonNull(confirmDialog
                .getWindow())
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        confirmDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (confirmDialog.getBackInfo()) {
                    startActivity(new Intent(Chapter2_3.this, HomeActivity.class));
                    finish();
                }
            }
        });
        confirmDialog.show();
        confirmDialog.setInfo_str(this.getResources().getString(R.string.backInfo));
    }

    /*封面点击关闭监听事件*/
    public void closeCover(View view) {
        view.setOnClickListener(null);
        alpha.setInterpolator(new LinearInterpolator());
        coverTv.startAnimation(alpha);
        coverTv.setVisibility(View.GONE);
        ((AnimationDrawable) walk_sheIv.getBackground()).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            keepTv.setVisibility(View.VISIBLE);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*完成chapter2_3,打开about*/
    public void openAbout(View view) {
        SharedPreferencesUtil.chapterDone(this, 5);
        Bundle backBundle = ActivityOptions
                .makeSceneTransitionAnimation(this, view, "toAbout").toBundle();
        Intent toNextChapter = new Intent(Chapter2_3.this, AboutActivity.class);
        startActivity(toNextChapter, backBundle);
        finish();
    }
}