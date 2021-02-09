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
import android.widget.TextView;

import com.mystery.valentine.Adapter.EmojiAdapter;
import com.mystery.valentine.DesignView.ConfirmDialog;
import com.mystery.valentine.DesignView.TypeTextView;
import com.mystery.valentine.HomeActivity;
import com.mystery.valentine.R;
import com.mystery.valentine.Util.SharedPreferencesUtil;

import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

@SuppressLint("ClickableViewAccessibility,NonConstantResourceId")
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class Chapter1_3 extends AppCompatActivity implements View.OnTouchListener {
    private static final String TAG = "TAG-DeBug";

    private static final String[] narrationStr = {
            "就算不在同一个城市", "我们的关系也在快速发展", "我决定,这个假期,向她表白",
    };

    private Animation alpha, translate;
    private EmojiAdapter emojiAdapter;
    private AnimationDrawable plane_anim;
    private TextView coverTv;
    private TypeTextView chapter13NarrationTv;
    private int count = 0;
    private int strCount = 0;
    private RecyclerView emojiChatList;
    private ImageView emoji1WaitIv;
    private ImageView emoji2WaitIv;
    private ImageView emoji3WaitIv;
    private ConstraintLayout nextView;
    private ConstraintLayout chapter13Last;
    private TypeTextView lastTv;
    private final int[] addEmoji = {0, 0, 0};
    private ImageView mIv;
    private ImageView loveUn;
    private ImageView love1;
    private ImageView love2;
    private ImageView love3;
    private ImageView love4;
    private ImageView love5;
    private ImageView love6;
    private ImageView iIv;
    private ImageView loveIv;
    private ImageView youIv;
    private int downX, downY;
    private int screenHeight;
    private int screenWidth;

    private boolean Done1, Done2, Done3, Done4, Done5, Done6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chapter_1_3);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        plane_anim.start();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            openDialog();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    /*初始化视图*/
    private void init() {
        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_anim);
        translate = AnimationUtils.loadAnimation(this, R.anim.translate_anim);
        translate.setInterpolator(new BounceInterpolator());
        translate.setFillAfter(true);
        plane_anim = (AnimationDrawable) findViewById(R.id.plane_iv).getBackground();
        coverTv = findViewById(R.id.chapter1_3_cover_tv);
        emoji1WaitIv = findViewById(R.id.emoji_1_wait_iv);
        emoji2WaitIv = findViewById(R.id.emoji_2_wait_iv);
        emoji3WaitIv = findViewById(R.id.emoji_3_wait_iv);
        emojiChatList = findViewById(R.id.emoji_chat_list);
        chapter13NarrationTv = findViewById(R.id.chapter1_3_narration_tv);
        nextView = findViewById(R.id.chapter1_3_next);
        chapter13Last = findViewById(R.id.chapter1_3_last);
        lastTv = findViewById(R.id.last_tv);
        mIv = findViewById(R.id.m_iv);
        loveUn = findViewById(R.id.love_un);
        love1 = findViewById(R.id.love_1);
        love2 = findViewById(R.id.love_2);
        love3 = findViewById(R.id.love_3);
        love4 = findViewById(R.id.love_4);
        love5 = findViewById(R.id.love_5);
        love6 = findViewById(R.id.love_6);
        love1.setOnTouchListener(this);
        love2.setOnTouchListener(this);
        love3.setOnTouchListener(this);
        love4.setOnTouchListener(this);
        love5.setOnTouchListener(this);
        love6.setOnTouchListener(this);
        iIv = findViewById(R.id.i_iv);
        loveIv = findViewById(R.id.love_iv);
        youIv = findViewById(R.id.you_iv);
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        emojiChatList.setLayoutManager(layoutManager);
        emojiAdapter = new EmojiAdapter();
        emojiChatList.setAdapter(emojiAdapter);
        emojiChatList.setItemAnimator(new SlideInLeftAnimator());
    }

    /*关闭封面监听事件*/
    public void closeCover(View view) {
        alpha.setInterpolator(new LinearInterpolator());
        view.setOnClickListener(null);
        coverTv.startAnimation(alpha);
        coverTv.setVisibility(View.GONE);
    }

    public void emoji_1_click(View view) {
        count++;
        switch (count) {
            case 1:
                addEmoji[0] = R.mipmap.emoji_s_1;
                emoji1WaitIv.setImageResource(R.mipmap.emoji_1);
                emoji1WaitIv.startAnimation(translate);
                break;
            case 2:
                addEmoji[1] = R.mipmap.emoji_s_1;
                emoji2WaitIv.setImageResource(R.mipmap.emoji_1);
                emoji2WaitIv.startAnimation(translate);
                break;
            case 3:
                addEmoji[2] = R.mipmap.emoji_s_1;
                emoji3WaitIv.setImageResource(R.mipmap.emoji_1);
                emoji3WaitIv.startAnimation(translate);
                break;
            default:
                break;
        }
    }

    public void emoji_2_click(View view) {
        count++;
        switch (count) {
            case 1:
                addEmoji[0] = R.mipmap.emoji_s_2;
                emoji1WaitIv.setImageResource(R.mipmap.emoji_2);
                emoji2WaitIv.startAnimation(translate);
                break;
            case 2:
                addEmoji[1] = R.mipmap.emoji_s_2;
                emoji2WaitIv.setImageResource(R.mipmap.emoji_2);
                emoji2WaitIv.startAnimation(translate);
                break;
            case 3:
                addEmoji[2] = R.mipmap.emoji_s_2;
                emoji3WaitIv.setImageResource(R.mipmap.emoji_2);
                emoji3WaitIv.startAnimation(translate);
                break;
            default:
                break;
        }
    }

    public void emoji_3_click(View view) {
        count++;
        if (count > 3) {
            count = 3;
        }
        switch (count) {
            case 1:
                addEmoji[0] = R.mipmap.emoji_s_3;
                emoji1WaitIv.setImageResource(R.mipmap.emoji_3);
                emoji1WaitIv.startAnimation(translate);
                break;
            case 2:
                addEmoji[1] = R.mipmap.emoji_s_3;
                emoji2WaitIv.setImageResource(R.mipmap.emoji_3);
                emoji2WaitIv.startAnimation(translate);
                break;
            case 3:
                addEmoji[2] = R.mipmap.emoji_s_3;
                emoji3WaitIv.setImageResource(R.mipmap.emoji_3);
                emoji3WaitIv.startAnimation(translate);
                break;
            default:
                break;
        }
    }

    public void emoji_4_click(View view) {
        count++;
        switch (count) {
            case 1:
                addEmoji[0] = R.mipmap.emoji_s_4;
                emoji1WaitIv.setImageResource(R.mipmap.emoji_4);
                emoji1WaitIv.startAnimation(translate);
                break;
            case 2:
                addEmoji[1] = R.mipmap.emoji_s_4;
                emoji2WaitIv.setImageResource(R.mipmap.emoji_4);
                emoji2WaitIv.startAnimation(translate);
                break;
            case 3:
                addEmoji[2] = R.mipmap.emoji_s_4;
                emoji3WaitIv.setImageResource(R.mipmap.emoji_4);
                emoji3WaitIv.startAnimation(translate);
                break;
            default:
                break;
        }
    }

    public void sendEmoji(View view) {
        emoji1WaitIv.setImageResource(0);
        emoji2WaitIv.setImageResource(0);
        emoji3WaitIv.setImageResource(0);
        Log.i(TAG, "sendEmoji: " + count);
        switch (count) {
            case 1:
                addEmoji[1] = 0;
            case 2:
                addEmoji[2] = 0;
            case 3:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                emojiChatList.setItemAnimator(new SlideInRightAnimator());
                                emojiAdapter.addView(true, addEmoji);
                                emojiChatList.scrollToPosition(emojiAdapter.getItemCount() - 1);
                                chapter13NarrationTv.animateText(narrationStr[strCount++]);
                                nextView();
                            }
                        });
                        try {
                            Thread.sleep(1000);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    emojiChatList.setItemAnimator(new SlideInLeftAnimator());
                                    emojiAdapter.addView(false, addEmoji);
                                    emojiChatList.scrollToPosition(emojiAdapter.getItemCount() - 1);
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            default:
                break;
        }
        count = 0;
    }

    /*下一阶段开启*/
    private void nextView() {
        if (strCount >= 3) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
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
            }).start();
        }
    }

    /*拼图完成*/
    public void sendLove(View view) {
        if (Done4 && Done5 && Done6) {
            iIv.setVisibility(View.VISIBLE);
            love4.setImageResource(0);
            love5.setImageResource(0);
            love6.setImageResource(0);
            love2.setVisibility(View.VISIBLE);
            love3.setVisibility(View.VISIBLE);
            love2.startAnimation(translate);
            love3.startAnimation(translate);
            mIv.setImageResource(R.mipmap.love_talk_2);
            if (Done2 && Done3) {
                loveIv.setVisibility(View.VISIBLE);
                love2.setImageResource(0);
                love3.setImageResource(0);
                love1.setVisibility(View.VISIBLE);
                love1.startAnimation(translate);
                mIv.setImageResource(R.mipmap.love_talk_3);
                if (Done1) {
                    love1.setImageResource(0);
                    youIv.setVisibility(View.VISIBLE);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(3000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        chapter13Last.setVisibility(View.VISIBLE);
                                        alpha = new AlphaAnimation(0, 1);
                                        alpha.setFillAfter(true);//动画结束后保持状态
                                        alpha.setDuration(3000);//动画持续时间
                                        chapter13Last.startAnimation(alpha);
                                        lastTv.animateText("我们开始艰难却幸福的异地恋");
                                    }
                                });
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            }
        }
    }

    /*检查拼图*/
    private void checkAnim(final View view) {
        int toTop = 0, toLeft = 0;
        switch (view.getId()) {
            case R.id.love_1:
            case R.id.love_3:
            case R.id.love_4:
                toTop = loveUn.getTop();
                toLeft = loveUn.getLeft();
                break;
            case R.id.love_2:
                toTop = loveUn.getTop();
                int length1 = view.getWidth() / 5;
                toLeft = loveUn.getLeft() + length1 * 4;
                break;
            case R.id.love_5:
                toTop = loveUn.getTop();
                int length2 = view.getWidth() / 5;
                toLeft = loveUn.getLeft() + length2 * 3;
                break;
            case R.id.love_6:
                toTop = loveUn.getTop();
                int length3 = view.getWidth() / 6;
                toLeft = loveUn.getLeft() + length3 * 9;
                break;
            default:
                break;
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
                case R.id.love_1:
                    Done1 = true;
                    love1.setOnTouchListener(null);
                    break;
                case R.id.love_2:
                    Done2 = true;
                    love2.setOnTouchListener(null);
                    break;
                case R.id.love_3:
                    Done3 = true;
                    love3.setOnTouchListener(null);
                    break;
                case R.id.love_4:
                    Done4 = true;
                    love4.setOnTouchListener(null);
                    break;
                case R.id.love_5:
                    Done5 = true;
                    love5.setOnTouchListener(null);
                    break;
                case R.id.love_6:
                    Done6 = true;
                    love6.setOnTouchListener(null);
                    break;
            }
        }
    }

    /*完成chapter1_3,打开chapter2_1*/
    public void openChapter2_1(View view) {
        SharedPreferencesUtil.chapterDone(this,2);
        Bundle backBundle = ActivityOptions
                .makeSceneTransitionAnimation(this, view, "toChapter2_1Share").toBundle();
        Intent toNextChapter = new Intent(Chapter1_3.this, Chapter2_1.class);
        startActivity(toNextChapter, backBundle);
        finish();
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
                    startActivity(new Intent(Chapter1_3.this, HomeActivity.class));
                    finish();
                }
            }
        });
        confirmDialog.show();
        confirmDialog.setInfo_str(this.getResources().getString(R.string.backInfo));
    }
}
