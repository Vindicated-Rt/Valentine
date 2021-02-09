package com.mystery.valentine;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.mystery.valentine.DesignView.ScrollNumber;
import com.mystery.valentine.Util.TimeUtil;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("NewApi")
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

public class AboutActivity extends AppCompatActivity {

    private TextView getAlongTimeTv;
    private Animation alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha_goodbye);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_about);
        getAlongTimeTv = findViewById(R.id.get_along_time_tv);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ScrollNumber.startAnimation(getAlongTimeTv, TimeUtil.getOffsetDay(),"天",1500);
        alpha.setInterpolator(new LinearInterpolator());
        findViewById(R.id.embrace_iv).startAnimation(alpha);
    }

    public void back(View view) {
        Intent toAbout = new Intent(AboutActivity.this,HomeActivity.class);
        startActivity(toAbout, ActivityOptions.makeSceneTransitionAnimation(this, view, "aboutShare").toBundle());
        finish();
    }

    public void chat_to_me(View view) {
        String url="mqqwpa://im/chat?chat_type=wpa&uin=1078472040";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
