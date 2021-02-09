package com.mystery.valentine.DesignView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mystery.valentine.R;

/**
 * Created by Vindicated-Rt
 * 2020-02-03 16:25
 */
public class ConfirmDialog extends Dialog implements View.OnClickListener{

    private String info_str;
    private Boolean backInfo = false;
    private TextView info_tv;

    public ConfirmDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.confirm_dialog);
        info_tv = findViewById(R.id.back_to_home_dialog_info_tv);
        findViewById(R.id.back_to_home_dialog_cancel_tv).setOnClickListener(this);
        findViewById(R.id.back_to_home_dialog_confirm_tv).setOnClickListener(this);
        findViewById(R.id.back_to_home_dialog_exit_iv).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_to_home_dialog_exit_iv:
                ConfirmDialog.this.cancel();
                break;
            case R.id.back_to_home_dialog_confirm_tv:
                setBackInfo(true);
                ConfirmDialog.this.cancel();
                break;
            case R.id.back_to_home_dialog_cancel_tv:
                setBackInfo(false);
                ConfirmDialog.this.cancel();
                break;
        }
    }

    private String getInfo_str() {
        return info_str;
    }

    public void setInfo_str(String info_str) {
        this.info_str = info_str;
        info_tv.setText(getInfo_str());
    }

    public Boolean getBackInfo() {
        return backInfo;
    }

    private void setBackInfo(Boolean backInfo) {
        this.backInfo = backInfo;
    }

    public TextView getInfo_tv() {
        return info_tv;
    }
}
