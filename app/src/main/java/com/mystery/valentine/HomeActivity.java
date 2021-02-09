package com.mystery.valentine;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.mystery.valentine.Chapter.Chapter1_1;
import com.mystery.valentine.DesignView.ConfirmDialog;
import com.mystery.valentine.Util.SharedPreferencesUtil;

import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint({"NewApi", "CommitPrefEdits"})
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

public class HomeActivity extends AppCompatActivity {

    private AnimationDrawable cover_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home);
        init();
    }

    /*视图初始化*/
    private void init() {
        cover_frame = (AnimationDrawable) findViewById(R.id.cover_iv).getBackground();
    }

    @Override
    protected void onStart() {
        super.onStart();
        cover_frame.start();
    }

    /*跳转开始页面*/
    public void startGame(View view) {
        openDialog(view);
    }

    /*跳转章节页面*/
    public void openChapter(View view) {
        Intent toAbout = new Intent(HomeActivity.this, ChapterActivity.class);
        startActivity(toAbout, ActivityOptions.makeSceneTransitionAnimation(this, view, "chapterShare").toBundle());
    }

    /*跳转关于界面*/
    public void aboutUs(View view) {
        Intent toAbout = new Intent(HomeActivity.this, AboutActivity.class);
        startActivity(toAbout, ActivityOptions.makeSceneTransitionAnimation(this, view, "aboutShare").toBundle());
    }

    /*清除存档dialog*/
    private void openDialog(View view) {
        final ConfirmDialog confirmDialog = new ConfirmDialog(this);
        Objects.requireNonNull(confirmDialog
                .getWindow())
                .setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        confirmDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                if (confirmDialog.getBackInfo()) {
                    startActivity(new Intent(HomeActivity.this, Chapter1_1.class));
                    SharedPreferencesUtil.clearData(HomeActivity.this);
                }
            }
        });
        confirmDialog.show();
        confirmDialog.setInfo_str(this.getResources().getString(R.string.openNew));
    }
}
