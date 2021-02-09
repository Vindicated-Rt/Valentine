package com.mystery.valentine.Chapter;

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
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.mystery.valentine.Adapter.PhonePicAdapter;
import com.mystery.valentine.DesignView.ConfirmDialog;
import com.mystery.valentine.DesignView.TypeTextView;
import com.mystery.valentine.HomeActivity;
import com.mystery.valentine.R;
import com.mystery.valentine.Util.SharedPreferencesUtil;

import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Chapter1_1 extends AppCompatActivity {

    private static final String[] narrationStr = {
            "刚回国的我参加了这场聚会", "旁边是他们的麻将声", "而我耍着手机",
            "二筒，碰，八条。。。", "突然。。。"
    };
    private SwipeFlingAdapterView phoneSv;
    private ScrollView nextView;
    private TextView coverTv;
    private TypeTextView chapter1_1Narration;
    private ImageView batteryIv;
    private int count = 0;
    private ImageView phoneLikeIv;
    private ImageView phoneDislikeIv;
    private Animation alpha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chapter_1_1);
        init();
    }

    /*初始化视图*/
    private void init() {
        nextView = findViewById(R.id.chapter1_1_next);
        coverTv = findViewById(R.id.chapter1_1_cover_tv);
        phoneSv = findViewById(R.id.phone_sv);
        batteryIv = findViewById(R.id.low_battery_iv);
        phoneLikeIv = findViewById(R.id.phone_like_iv);
        phoneDislikeIv = findViewById(R.id.phone_dislike_iv);
        chapter1_1Narration = findViewById(R.id.chapter1_1_narration_tv);
        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        final PhonePicAdapter phoneAdapter = new PhonePicAdapter();
        phoneSv.setAdapter(phoneAdapter);
        phoneSv.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                phoneAdapter.getViews().remove(0);
                phoneAdapter.notifyDataSetChanged();
                chapter1_1Narration.animateText(narrationStr[count]);
                count++;
                if (count == 5) {
                    phoneLikeIv.setVisibility(View.GONE);
                    phoneDislikeIv.setVisibility(View.GONE);
                    batteryIv.setVisibility(View.VISIBLE);
                    alpha.setInterpolator(new BounceInterpolator());
                    alpha.setRepeatCount(3);
                    alpha.setRepeatMode(Animation.REVERSE);
                    batteryIv.startAnimation(alpha);
                    toNext.start();
                }
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {
            }

            @Override
            public void onScroll(float v) {
                View view = phoneSv.getSelectedView();
                if (view != null) {
                    view.findViewById(R.id.choose_item_pass_iv).setAlpha(v < 0 ? -v : 0);
                    view.findViewById(R.id.choose_item_like_iv).setAlpha(v > 0 ? v : 0);
                } else {
                    phoneSv.getTopCardListener().leftBorder();
                    phoneAdapter.notifyDataSetChanged();
                }
            }
        });
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
                    startActivity(new Intent(Chapter1_1.this, HomeActivity.class));
                    finish();
                }
            }
        });
        confirmDialog.show();
        confirmDialog.setInfo_str(this.getResources().getString(R.string.backInfo));
    }

    /*喜欢按钮监听事件*/
    public void likeBtn(View view) {
        phoneSv.getTopCardListener().selectRight();
    }

    /*不喜欢按钮监听事件*/
    public void dislikeBtn(View view) {
        phoneSv.getTopCardListener().selectLeft();
    }

    /*封面点击关闭监听事件*/
    public void closeCover(View view) {
        alpha.setInterpolator(new LinearInterpolator());
        view.setOnClickListener(null);
        coverTv.startAnimation(alpha);
        coverTv.setVisibility(View.GONE);
    }

    /*完成chapter1_1,打开chapter1_2*/
    public void openChapter1_2(View view) {
        SharedPreferencesUtil.chapterDone(this, 0);
        Bundle backBundle = ActivityOptions
                .makeSceneTransitionAnimation(this, view, "toChapter1_2Share").toBundle();
        Intent toNextChapter = new Intent(Chapter1_1.this, Chapter1_2.class);
        startActivity(toNextChapter, backBundle);
        finish();
    }

    /*下一小节线程*/
    private final Thread toNext = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(4500);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chapter1_1Narration.setVisibility(View.GONE);
                        batteryIv.setVisibility(View.GONE);
                        phoneSv.setVisibility(View.GONE);
                        nextView.setVisibility(View.VISIBLE);
                        alpha = new AlphaAnimation(0, 1);
                        alpha.setFillAfter(true);//动画结束后保持状态
                        alpha.setDuration(2000);//动画持续时间
                        nextView.startAnimation(alpha);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}
