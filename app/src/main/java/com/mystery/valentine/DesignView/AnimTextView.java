package com.mystery.valentine.DesignView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Vindicated-Rt
 * 2020-02-03 23:06
 */
@SuppressLint("AppCompatCustomView")
public abstract class AnimTextView extends TextView {

    public AnimTextView(Context context) {
        this(context, null);
    }

    public AnimTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setAnimationListener(AnimationListener listener);

    public abstract void setProgress(float progress);

    public abstract void animateText(CharSequence text);
}
