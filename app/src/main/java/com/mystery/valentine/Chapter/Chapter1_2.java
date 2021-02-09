package com.mystery.valentine.Chapter;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mystery.valentine.Adapter.ChatListAdapter;
import com.mystery.valentine.DesignView.ConfirmDialog;
import com.mystery.valentine.DesignView.TypeTextView;
import com.mystery.valentine.HomeActivity;
import com.mystery.valentine.R;
import com.mystery.valentine.Util.SharedPreferencesUtil;

import java.util.Objects;
import java.util.Random;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
@SuppressLint({"ClickableViewAccessibility","NonConstantResourceId"})

public class Chapter1_2 extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "TAG-DeBug";

    private static final String[] narrationStr = {
            "经朋友加到了她的QQ", "我们的聊着美食,近况。。。", "时间过的很快。。。",
            "到了说晚安的时候了", "晚安"
    };

    private Animation alpha, translate;
    private RecyclerView chatList;
    private ChatListAdapter chatListAdapter;
    private TypeTextView chapter1_2NarrationTv;
    private TextView coverTv;
    private ImageView jigsawUnIv;
    private ImageView jigsawRIv;
    private ImageView jigsawMIv;
    private ImageView jigsawLIv;
    private ScrollView nextView;
    private AnimationDrawable train_anim;
    private int screenHeight;
    private int screenWidth;
    private int downX, downY;
    private final int[] jigsawViews = {
            R.mipmap.jigsaw_1, R.mipmap.jigsaw_2,
            R.mipmap.jigsaw_3, R.mipmap.jigsaw_4,
            R.mipmap.jigsaw_5, R.mipmap.jigsaw_6,
            R.mipmap.jigsaw_7, R.mipmap.jigsaw_8,};
    private final int[][] jigsawsR = {{4, 1, 6}, {2, 3, 0}, {4, 5, 0}, {2, 7, 6}};
    private final int[][] jigsawsC = {{0, 1, 2}, {0, 2, 1}, {1, 2, 0}, {1, 0, 2}, {2, 0, 1}, {2, 1, 0}};
    private int checkL, checkM, checkR;
    private boolean LDone, RDone, MDone;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chapter_1_2);
        init();
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

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) motionEvent.getRawX();
                downY = (int) motionEvent.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int newX = (int) motionEvent.getRawX();
                int newY = (int) motionEvent.getRawY();
                int dx = newX - downX;
                int dy = newY - downY;
                int top = view.getTop();
                int bottom = view.getBottom();
                int left = view.getLeft();
                int right = view.getRight();
                int newTop = top + dy;
                int newBottom = bottom + dy;
                int newLeft = left + dx;
                int newRight = right + dx;
                if ((newLeft < 0) || (newRight < 0)
                        || (newRight > screenWidth)
                        || (newBottom > screenHeight)) {
                    break;
                }
                view.layout(newLeft, newTop, newRight, newBottom);
                downX = (int) motionEvent.getRawX();
                downY = (int) motionEvent.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                checkAnim(view);
                break;
        }
        return true;
    }

    /*初始化视图*/
    private void init() {
        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        translate = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        coverTv = findViewById(R.id.chapter1_2_cover_tv);
        chatList = findViewById(R.id.emoji_chat_list);
        chapter1_2NarrationTv = findViewById(R.id.chapter1_2_narration_tv);
        jigsawUnIv = findViewById(R.id.jigsaw_un_iv);
        jigsawRIv = findViewById(R.id.jigsaw_r_iv);
        jigsawMIv = findViewById(R.id.jigsaw_m_iv);
        jigsawLIv = findViewById(R.id.jigsaw_l_iv);
        nextView = findViewById(R.id.chapter1_2_next);
        train_anim = (AnimationDrawable) findViewById(R.id.train_iv).getBackground();
        jigsawRIv.setOnTouchListener(this);
        jigsawMIv.setOnTouchListener(this);
        jigsawLIv.setOnTouchListener(this);
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        chatList.setLayoutManager(layoutManager);
        chatListAdapter = new ChatListAdapter();
        chatList.setAdapter(chatListAdapter);
        chatList.setItemAnimator(new SlideInLeftAnimator());
        setNewChat();
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
                    startActivity(new Intent(Chapter1_2.this, HomeActivity.class));
                    finish();
                }
            }
        });
        confirmDialog.show();
        confirmDialog.setInfo_str(this.getResources().getString(R.string.backInfo));
    }

    /*关闭封面监听事件*/
    public void closeCover(View view) {
        alpha.setInterpolator(new LinearInterpolator());
        view.setOnClickListener(null);
        coverTv.startAnimation(alpha);
        coverTv.setVisibility(View.GONE);
    }

    /*检查拼图对应关系是否正确*/
    private void checkAnim(final View view) {
        int jigsawTo = 0;
        int toTop = 0, toLeft = 0;
        int length = view.getWidth() / 7;
        switch (view.getId()) {
            case R.id.jigsaw_l_iv:
                jigsawTo = checkTo(checkL);
                break;
            case R.id.jigsaw_m_iv:
                jigsawTo = checkTo(checkM);
                break;
            case R.id.jigsaw_r_iv:
                jigsawTo = checkTo(checkR);
                break;
            default:
                break;
        }
        if (jigsawTo == 0) {
            //目的地为左
            toTop = jigsawUnIv.getTop();
            toLeft = jigsawUnIv.getLeft();
        } else if (jigsawTo == 1) {
            //目的地为中
            toTop = jigsawUnIv.getTop();
            toLeft = jigsawUnIv.getLeft() + length * 4;
        } else if (jigsawTo == 2) {
            //目的地为右
            toTop = jigsawUnIv.getTop();
            toLeft = jigsawUnIv.getLeft() + length * 10;
        }
        int dy = toTop - view.getTop();
        int dx = toLeft - view.getLeft();
        if (Math.abs(dy) < 100 && Math.abs(dx) < 100) {
            final TranslateAnimation jigsawAnimation = new TranslateAnimation(0, dx, 0, dy);
            jigsawAnimation.setDuration(618);
            jigsawAnimation.setFillAfter(true);
            jigsawAnimation.setInterpolator(new BounceInterpolator());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    view.startAnimation(jigsawAnimation);
                }
            });
            switch (view.getId()) {
                case R.id.jigsaw_l_iv:
                    LDone = true;
                    jigsawLIv.setOnTouchListener(null);
                    break;
                case R.id.jigsaw_m_iv:
                    MDone = true;
                    jigsawMIv.setOnTouchListener(null);
                    break;
                case R.id.jigsaw_r_iv:
                    RDone = true;
                    jigsawRIv.setOnTouchListener(null);
                    break;
            }
        }
    }

    /*判断方块去向*/
    private int checkTo(int check) {
        if (check == 2 || check == 4) {
            return 0;
        } else if (check == 0 || check == 6) {
            return 2;
        } else {
            return 1;
        }
    }

    /*刷新拼图*/
    private void setNewChat() {
        jigsawRIv.setVisibility(View.INVISIBLE);
        jigsawMIv.setVisibility(View.INVISIBLE);
        jigsawLIv.setVisibility(View.INVISIBLE);
        int jigsawRandomR = new Random().nextInt(4);
        int jigsawRandomC = new Random().nextInt(6);
        checkL = jigsawsR[jigsawRandomR][jigsawsC[jigsawRandomC][0]];
        checkM = jigsawsR[jigsawRandomR][jigsawsC[jigsawRandomC][1]];
        checkR = jigsawsR[jigsawRandomR][jigsawsC[jigsawRandomC][2]];
        jigsawLIv.setImageResource(jigsawViews[checkL]);
        jigsawMIv.setImageResource(jigsawViews[checkM]);
        jigsawRIv.setImageResource(jigsawViews[checkR]);
        jigsawRIv.setVisibility(View.VISIBLE);
        jigsawMIv.setVisibility(View.VISIBLE);
        jigsawLIv.setVisibility(View.VISIBLE);
        translate.setInterpolator(new BounceInterpolator());
        translate.setFillAfter(true);
        jigsawRIv.startAnimation(translate);
        jigsawLIv.startAnimation(translate);
        jigsawMIv.startAnimation(translate);
    }

    /*刷新点击事件*/
    private void setOnTouch() {
        jigsawRIv.setOnTouchListener(this);
        jigsawMIv.setOnTouchListener(this);
        jigsawLIv.setOnTouchListener(this);
    }

    /*发送信息,检查拼图*/
    public void sendChat(View view) {
        if (LDone && RDone && MDone) {
            Log.i(TAG, "sendChat: " + count);
            if (count == 5) {
                toNext.start();
            } else {
                chapter1_2NarrationTv.animateText(narrationStr[count++]);
            }
            LDone = false;
            RDone = false;
            MDone = false;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            chatList.setItemAnimator(new SlideInRightAnimator());
                            chatListAdapter.addData(true);
                            chatList.scrollToPosition(chatListAdapter.getItemCount() - 1);
                            setNewChat();
                        }
                    });
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                chatList.setItemAnimator(new SlideInLeftAnimator());
                                chatListAdapter.addData(false);
                                chatList.scrollToPosition(chatListAdapter.getItemCount() - 1);
                                setOnTouch();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /*完成chapter1_2,打开chapter1_3*/
    public void openChapter1_3(View view) {
        SharedPreferencesUtil.chapterDone(this, 1);
        Bundle backBundle = ActivityOptions
                .makeSceneTransitionAnimation(this, view, "toChapter1_3Share").toBundle();
        Intent toNextChapter = new Intent(Chapter1_2.this, Chapter1_3.class);
        startActivity(toNextChapter, backBundle);
        finish();
    }

    /*下一小节线程*/
    private final Thread toNext = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nextView.setVisibility(View.VISIBLE);
                        alpha = new AlphaAnimation(0, 1);
                        alpha.setFillAfter(true);//动画结束后保持状态
                        alpha.setDuration(2000);//动画持续时间
                        nextView.startAnimation(alpha);
                        train_anim.start();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}
