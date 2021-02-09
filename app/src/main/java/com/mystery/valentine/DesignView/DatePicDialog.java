package com.mystery.valentine.DesignView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mystery.valentine.R;


public class DatePicDialog extends Dialog implements View.OnClickListener{
    private ImageView dateDialogIv;

    public DatePicDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.date_dialog);
        dateDialogIv = findViewById(R.id.date_dialog_iv);
        findViewById(R.id.date_dialog_iv_confirm_tv).setOnClickListener(this);
        findViewById(R.id.date_dialog_iv_exit_iv).setOnClickListener(this);
    }

    public void setDatePic(int PicId){
        dateDialogIv.setBackgroundResource(PicId);
    }

    @Override
    public void onClick(View v) {
        DatePicDialog.this.cancel();
    }
}
