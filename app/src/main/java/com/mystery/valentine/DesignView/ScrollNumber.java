package com.mystery.valentine.DesignView;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

/**
 * Created by Vindicated-Rt
 * 2020-02-11 20:08
 */
@SuppressLint("SetTextI18n")
public class ScrollNumber {
    public static void startAnimation(final TextView tvView, int Value
            , final String unit, int duration) {
        ValueAnimator animator;
        animator = ValueAnimator.ofInt(0, Value);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int curValue = (int) valueAnimator.getAnimatedValue();
                tvView.setText(curValue + unit);
            }
        });
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }
}
