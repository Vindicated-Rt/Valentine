package com.mystery.valentine.Chapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.mystery.valentine.DesignView.ConfirmDialog;
import com.mystery.valentine.DesignView.TypeTextView;
import com.mystery.valentine.HomeActivity;
import com.mystery.valentine.R;
import com.mystery.valentine.Util.SharedPreferencesUtil;

import java.util.Objects;

import me.zhouzhuo.zzhorizontalprogressbar.ZzHorizontalProgressBar;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Chapter2_2 extends AppCompatActivity {

    private Animation alpha, phone_call_anim;
    private TextView coverTv;
    private ImageView callIv;
    private ConstraintLayout phoneChatLayout;
    private Chronometer phoneChateTime;
    private ZzHorizontalProgressBar phoneChatProgress;
    private TypeTextView phoneChatChoose1;
    private TypeTextView phoneChatChoose2;
    private int ChatCount = 1;
    private final String[] chat1Strings = {
            "当然想你啊！", "写代码哪有你重要", "你来找我吧", "你先睡吧", "你不用管我"
    };
    private final String[] chat2Strings = {
            "在忙", "代码还没写完", "我来不了", "你玩你的吧", "那就这样吧"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chapter_2_2);
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
        phone_call_anim = AnimationUtils.loadAnimation(this, R.anim.phone_call_anim);
        coverTv = findViewById(R.id.chapter2_2_cover_tv);
        callIv = findViewById(R.id.call_iv);
        phoneChatLayout = findViewById(R.id.phone_chat_layout);
        phoneChateTime = findViewById(R.id.phone_chate_time);
        phoneChatProgress = findViewById(R.id.phone_chat_progress);
        phoneChatChoose1 = findViewById(R.id.phone_chat_choose_1);
        callIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneCall();
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
                    startActivity(new Intent(Chapter2_2.this, HomeActivity.class));
                    finish();
                }
            }
        });
        confirmDialog.show();
        confirmDialog.setInfo_str(this.getResources().getString(R.string.backInfo));
    }

    /*封面点击关闭监听事件*/
    public void closeCover(View view) {
        alpha.setInterpolator(new LinearInterpolator());
        view.setOnClickListener(null);
        coverTv.startAnimation(alpha);
        coverTv.setVisibility(View.GONE);
        phoneChatLayout.startAnimation(phone_call_anim);
    }

    /*完成chapter2_2,打开chapter2_3*/
    public void openChapter2_3(View view) {
        SharedPreferencesUtil.chapterDone(this, 4);
        Bundle backBundle = ActivityOptions
                .makeSceneTransitionAnimation(this, view, "toChapter2_3Share").toBundle();
        Intent toNextChapter = new Intent(Chapter2_2.this, Chapter2_3.class);
        startActivity(toNextChapter, backBundle);
        finish();
    }

    /*接听电话*/
    private void phoneCall() {
        phoneChatLayout.clearAnimation();
        phoneChateTime.setVisibility(View.VISIBLE);
        phoneChateTime.start();
        callIv.setBackgroundResource(R.mipmap.call_off_ic);
        callIv.setOnClickListener(null);
        phoneChatChoose1.animateText(chat1Strings[0]);
        phoneChatChoose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChatStrings();
            }
        });
        phoneChatChoose2 = findViewById(R.id.phone_chat_choose_2);
        phoneChatChoose2.animateText(chat2Strings[0]);
        phoneChatChoose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChatStrings();
            }
        });
    }

    /*文本设置*/
    private void setChatStrings() {
        if (ChatCount >= 5) {
            phoneChatProgress.setProgress(100);
            phoneChatChoose1.animateText("无话可说");
            phoneChatChoose2.animateText("无话可说");
            callIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openChapter2_3(v);
                }
            });
        } else {
            phoneChatChoose1.animateText(chat1Strings[ChatCount]);
            phoneChatChoose2.animateText(chat2Strings[ChatCount]);
            phoneChatProgress.setProgress(ChatCount * 20);
            ChatCount++;
        }
    }
}