package com.baibeiyun.bbyiot.module.home.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.utils.StringUtils;

public class KaiduDialog extends Dialog {
    Context mContext;
    private EditText et_kaidu;
    private String kaiduValue;

    public KaiduDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
        //dialogWindow.setWindowAnimations();设置动画效果
        //去除白色背景
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);

        setContentView(R.layout.dialog_kaidu);

        // true 点击外部Dialog消失
        setCanceledOnTouchOutside(false);

        initView();
    }

    private void initView() {
        et_kaidu = findViewById(R.id.dialog_kaidu_et_kaidu);

        findViewById(R.id.dialog_kaidu_tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kaiduValue = et_kaidu.getText().toString().trim();
                if(StringUtils.isEmpty(kaiduValue)){
                    kaiduValue = "0";
                }
                mLisenter.queding(kaiduValue);
            }
        });

        findViewById(R.id.dialog_kaidu_tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    OnLisenter mLisenter;

    public interface OnLisenter {
        void queding(String value);
    }

    public void setOnLisenter(OnLisenter lisenter) {
        mLisenter = lisenter;
    }
}
