package com.baibeiyun.bbyiot.module.mine.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Locale;

/**
 *
 *
 */
public class DateSelectUtil {

    /**
     * 弹出时间弹框
     */
    public static void showDatePopu(Context mContext, final SelectListener listener) {
        final Calendar dateAndTime = Calendar.getInstance(Locale.getDefault());
        DatePickerDialog dateDlg = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //year, monthOfYear+1, dayOfMonth
                listener.onDateSelect(year, monthOfYear + 1, dayOfMonth);
            }
        },
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH));
        dateDlg.show();
    }

    public interface SelectListener {
        void onDateSelect(int year, int monthOfYear, int dayOfMonth);
    }


}
