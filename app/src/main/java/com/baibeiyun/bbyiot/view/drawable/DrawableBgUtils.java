package com.baibeiyun.bbyiot.view.drawable;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.baibeiyun.bbyiot.utils.DensityUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

public class DrawableBgUtils {


    public static Drawable getBgDrawable(Context ctx, String color) {
        // int strokeWidth = 5; // 0dp 边框宽度
        //int roundRadius=TypedValue.applyDimension(); // 10dp 圆角半径
        // int strokeColor = Color.parseColor("#2E3135");//边框颜色
        //int fillColor = Color.parseColor("#971417");//内部填充颜色
        if (color != null && color.length() == 4) {
            color = getColor(color);
        }

        int fillColor = Color.parseColor(color);//内部填充颜色
        int topLeftRadius = DensityUtils.dip2px(ctx, 15);
        int topRightRadius = 0;
        int bottomRightRadius = 0;
        int bottomLeftRadius = topLeftRadius;

        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setGradientType(GradientDrawable.RECTANGLE);
        gd.setColor(fillColor);
        // gd.setCornerRadius(roundRadius);
        //1、2两个参数表示左上角，3、4表示右上角，5、6表示右下角，7、8表示左下角
        gd.setCornerRadii(new float[]{topLeftRadius,
                topLeftRadius, topRightRadius, topRightRadius,
                bottomRightRadius, bottomRightRadius, bottomLeftRadius,
                bottomLeftRadius});

        // gd.setStroke(strokeWidth, strokeColor);

        return gd;
    }

    private static String getColor(String color) {
        if (color == null || color.length() == 0) {

            return "#999999";
        }
        String tempColor = "";
        char[] chars = color.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            tempColor = tempColor + chars[i];
        }

        for (int i = 0; i < 3; i++) {
            tempColor = tempColor + chars[chars.length - 1];
        }
        LogUtils.w("tempColor == " + tempColor);
        return tempColor;
    }


}
