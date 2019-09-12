package com.baibeiyun.bbyiot.module.mine.view;

import android.content.Context;

import com.bigkoo.pickerview.TimePickerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 */
public class DateSelectUtil22 {


    private static TimePickerView pvTime;

    public static void showDatePopu(Context mContext, String title, final SelectListener listener) {

        //时间选择器
//        if (pvTime == null)
            pvTime = new TimePickerView(mContext, TimePickerView.Type.ALL);

        //控制时间范围
        //        Calendar calendar = Calendar.getInstance();
        //        pvTime.setRange(calendar.get(Calendar.YEAR) - 20, calendar.get(Calendar.YEAR));//要在setTime 之前才有效果哦
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);

        pvTime.setTitle(title);

        //pvTime.setTime();
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {

                listener.onDateSelect(converToString(date));
            }
        });
        pvTime.show();
    }


    public interface SelectListener {
        void onDateSelect(String dataString);
    }

    //把日期转为字符串
    public static String converToString(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }


}
