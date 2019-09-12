package com.baibeiyun.bbyiot.module.mine.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baibeiyun.bbyiot.R;
import com.baibeiyun.bbyiot.module.mine.adapter.SelectGroupAdapter;
import com.baibeiyun.bbyiot.utils.LogUtils;

import java.util.List;

public class SelectGroupDialog extends Dialog {

    private Activity mContext;
    private ListView listView;

    private TextView tv_name;
    private ImageView iv_colse;
    private TextView tv_cofirm;

    private SelectGroupAdapter selectGroupAdapter;


    int selectPosition = 0;
    private List<String> dataList;

    public SelectGroupDialog(@NonNull Activity context) {
        super(context, R.style.LocatonDialogStyle);
        this.mContext = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_select_group);

        WindowManager windowManager = mContext.getWindowManager();
        Display display = windowManager.getDefaultDisplay();

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = (int) (display.getWidth()); //设置宽度
        params.height = (int) (display.getHeight() * 0.6); //设置高度
        getWindow().setAttributes(params);

        //// 点击对话框以外的地方，对话框不消失。按返回键对话框也不消失。
        setCancelable(false);

        initView();
        LogUtils.w("----------------initView");
        initEvent();
    }


    private void initView() {

        tv_cofirm = findViewById(R.id.item_select_group_tv_cofirm);
        iv_colse = findViewById(R.id.item_select_group_iv_colse);

        tv_name = findViewById(R.id.item_select_group_tv_name);
        listView = findViewById(R.id.item_select_group_listview);


        selectGroupAdapter = new SelectGroupAdapter(mContext);
        listView.setAdapter(selectGroupAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    selectPosition = i;
                    selectGroupAdapter.select(i);
                    tv_name.setText(dataList.get(i));
                } catch (Exception e) {
                    LogUtils.w(e.toString());
                }
            }
        });
    }

    public void setData(List<String> list) {
        dataList = list;
        selectPosition = 0;
        if (selectGroupAdapter != null) {
            selectGroupAdapter.updata(list);
            selectGroupAdapter.select(selectPosition);
        }

        if (list != null && list.size() > 0) {
            tv_name.setText(list.get(0));
        }
    }

    private void initEvent() {

        iv_colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        tv_cofirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linsenter.cofirm(selectPosition);

            }
        });


    }

    public void setOnClickLinsenter(OnClickLinsenter linsenter) {
        this.linsenter = linsenter;
    }

    public interface OnClickLinsenter {
        void cofirm(int position);

    }

    private OnClickLinsenter linsenter;

}
