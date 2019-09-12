package com.baibeiyun.bbyiot.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.utils.LogUtils;

public class CommonDialog extends Dialog {

    Context mContext;
    private TextView tv_ok;
    private TextView tv_cancel, tv_title, tv_content;

    public CommonDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);//设置dialog显示居中
        //dialogWindow.setWindowAnimations();设置动画效果
        //去除白色背景
        dialogWindow.setBackgroundDrawableResource(android.R.color.transparent);

        setContentView(R.layout.dialog_common);

        // true 点击外部Dialog消失
        setCanceledOnTouchOutside(false);

        initView();
    }

    private void initView() {
        tv_ok = findViewById(R.id.dialog_common_tv_ok);
        tv_cancel = findViewById(R.id.dialog_common_tv_cancel);
        tv_title = findViewById(R.id.dialog_common_tv_title);
        tv_content = findViewById(R.id.dialog_common_tv_content);


        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLisenter.confirm();
                dismiss();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLisenter.cancel();
                dismiss();
            }
        });
    }

    public void setTxet(String title, String content, String cancel, String ok) {
        try {
            tv_title.setText(title);
            tv_content.setText(content);
            tv_ok.setText(ok);
            tv_cancel.setText(cancel);
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    public void setTxet(String title, String content) {
        try {
            tv_title.setText(title);
            tv_content.setText(content);
        } catch (Exception e) {
            LogUtils.w(e.toString());
        }
    }

    OnDialogLinsenter mLisenter;

    public abstract static class OnDialogLinsenter {
        public abstract void confirm();
        public void cancel(){}
    }

    public void setOnDialogLinsenter(OnDialogLinsenter linsenter) {
        this.mLisenter = linsenter;
    }
}
