package com.baibeiyun.bbyiot.module.home.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.baibeiyun.bbyiot.utils.DensityUtils;

/**
 * 类文件：自定义drawable
 * 作者：zhuyong on 2018/5/2 11:15
 * 邮箱：99305919@qq.com
 * 希望每天叫醒你的不是闹钟而是梦想
 */
public class RectangleDrawable extends Drawable {

    private Paint mPaintText;
    private Paint framePaint;
    private String mText;
    private Context mContext;

    /**
     * 矩形的宽
     */
    private float mRectangWidth;
    // 矩形的高
    private float mRectangHight;

    /**
     * 文字到圆边距,改变此值等于修改圆的padding值
     */
    private float mCircleRadiusMagin = 10;
    /**
     * 扩展角度(1-180°)，即菱形的内角,此值若大于180度则效果不会显示
     */
    private int mExtendAngle = 20;
    /**
     * 上面or下面
     */
    private boolean mIsUp = true;

    private int mBackGroundColor = Color.parseColor("#99000000");
    private int mTextColor = Color.parseColor("#333333");

    public RectangleDrawable(Context mContext) {
        this.mContext = mContext;
        init();
    }

    /**
     * 设置上还是下
     *
     * @param mIsUp
     */
    public void setmIsUp(boolean mIsUp) {
        this.mIsUp = mIsUp;
    }

    /**
     * 设置背景颜色
     *
     * @param mBackGroundColor
     */
    public void setmBackGroundColor(int mBackGroundColor) {
        framePaint.setColor(mBackGroundColor);
    }

    /**
     * 设置绘制文字
     *
     * @param mText
     */
    public void setmText(String mText) {
        this.mText = mText;
    }

    private void init() {
        framePaint = new Paint();
        framePaint.setAntiAlias(true);
        framePaint.setColor(mBackGroundColor);
        // Paint.Style.FILL :填充内部
        framePaint.setStyle(Paint.Style.FILL);
        framePaint.setStrokeWidth(0);

        mPaintText = new Paint();
        mPaintText.setColor(mTextColor);
        mPaintText.setTextSize(DensityUtils.sp2px(mContext, 10));
        mPaintText.setAntiAlias(true);

    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        startDraw(canvas);
    }

    private void startDraw(Canvas canvas) {
        //获取文字宽度
        float textWidth = mPaintText.measureText(mText, 0, mText.length());
        mRectangWidth = textWidth + mCircleRadiusMagin * 2;
        mRectangHight = DensityUtils.dip2px(mContext, 30);
        /**
         * 我们限制一下最小值
         */
        if (mRectangWidth < DensityUtils.dip2px(mContext, 12)) {
            mRectangWidth = DensityUtils.dip2px(mContext, 12);
        }

        /**
         * 圆的半径有了，接下来确定圆心坐标
         * 根据mExtendAngle和勾股定理计算出菱形对角线一半的长度
         */
        float mDiamondVertical = (float) (Math.cos(Math.PI * (mExtendAngle / 2) / 180) * DensityUtils.dip2px(mContext, 12));
        float mDiamondHorizontal = (float) (Math.sin(Math.PI * (mExtendAngle / 2) / 180) * DensityUtils.dip2px(mContext, 12));
        /**
         * 菱形上(下)点坐标即圆心坐标是(0,±mDiamondVertical*2)
         * 菱形左点坐标：(-mDiamondHorizontal,±mDiamondVertical)
         * 菱形右点坐标：(-mDiamondHorizontal,±mDiamondVertical)
         */
        Path path = new Path();
        path.moveTo(-mDiamondHorizontal, mIsUp ? -mDiamondVertical : mDiamondVertical);
        path.lineTo(0, 0);
        path.lineTo(mDiamondHorizontal, mIsUp ? -mDiamondVertical : mDiamondVertical);

        RectF rectF = null;
        if (mIsUp) {
            //float left, float top, float right, float bottom
            rectF = new RectF(-mRectangWidth / 2, -mDiamondVertical
                    , mRectangWidth / 2, -mRectangHight);

            //画弧
            //path.addArc(rectF, -(90 - mExtendAngle / 2), 360 - mExtendAngle);
            //画矩形
            path.addRect(rectF, Path.Direction.CCW);
        } else {
            rectF = new RectF(-mRectangWidth / 2, mDiamondVertical
                    , mRectangWidth / 2, mRectangHight);
            //画弧
            //path.addArc(rectF, -(90 - mExtendAngle / 2), 360 - mExtendAngle);
            path.addRect(rectF, Path.Direction.CCW);
        }
        canvas.drawPath(path, framePaint);

        /**
         * 计算文字的坐标点
         */
        float dx = -textWidth / 2;
        Paint.FontMetricsInt fontMetricsInt = mPaintText.getFontMetricsInt();
        float dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        float baseLine = mIsUp ? (-(mDiamondVertical + mRectangHight) / 2 + dy) : ((mDiamondVertical + mRectangHight) / 2 + dy);
        canvas.drawText(mText, dx, baseLine, mPaintText);
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @SuppressLint("WrongConstant")
    @Override
    public int getOpacity() {
        return 0;
    }
}
