package com.mystery.valentine.Chapter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.mystery.valentine.DesignView.ConfirmDialog;
import com.mystery.valentine.DesignView.DatePicDialog;
import com.mystery.valentine.HomeActivity;
import com.mystery.valentine.R;
import com.mystery.valentine.Util.SharedPreferencesUtil;

import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Chapter2_1 extends AppCompatActivity {

    private Animation alpha;
    private TextView coverTv;
    private int showDatePicNum = 1;
    private final int[] pic = {
            R.mipmap.date_shine,R.mipmap.date_lotuspond,R.mipmap.date_beath
            ,R.mipmap.date_handprint,R.mipmap.date_firstkiss};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chapter_2_1);
        initView();
    }

    /*初始视图*/
    private void initView(){
        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        coverTv = findViewById(R.id.chapter2_1_cover_tv);
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
                    startActivity(new Intent(Chapter2_1.this, HomeActivity.class));
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
    }

    /*图标切换点击事件*/
    public void changeDate(View view){
        ImageView mapIv = findViewById(R.id.date_map);
        if (view.getId() == R.id.date_map_1_tv){
            mapIv.setImageResource(R.mipmap.map_1);
            showDatePicNum = 1;
        }else if (view.getId() == R.id.date_map_2_tv){
            mapIv.setImageResource(R.mipmap.map_2);
            showDatePicNum = 2;
        }else if (view.getId() == R.id.date_map_3_tv){
            mapIv.setImageResource(R.mipmap.map_3);
            showDatePicNum = 3;
        }else if (view.getId() == R.id.date_map_4_tv){
            mapIv.setImageResource(R.mipmap.map_4);
            showDatePicNum = 4;
        }else if (view.getId() == R.id.date_map_5_tv){
            mapIv.setImageResource(R.mipmap.map_5);
            showDatePicNum = 5;
        }
    }

    /*显示图片dialog*/
    public void showDatePicDialog(View view) {
        DatePicDialog datePicDialog = new DatePicDialog(this);
        datePicDialog.show();
        datePicDialog.setDatePic(pic[showDatePicNum-1]);
    }

    /*完成chapter2_1,打开chapter2_2*/
    public void openChapter2_2(View view) {
        SharedPreferencesUtil.chapterDone(this,3);
        Bundle backBundle = ActivityOptions
                .makeSceneTransitionAnimation(this, view, "toChapter2_2Share").toBundle();
        Intent toNextChapter = new Intent(Chapter2_1.this, Chapter2_2.class);
        startActivity(toNextChapter, backBundle);
        finish();
    }
}