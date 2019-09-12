package com.baibeiyun.bbyiot.view.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.utils.BaseTimer;
import com.baibeiyun.bbyiot.utils.DensityUtils;
import com.baibeiyun.bbyiot.utils.LogUtils;

/**
 * 首页环形图
 */
public class HomeCircleView extends View {

    Paint paintZc, paintGj, paintLx;

    RectF area;

    float percentumZc = 0;
    float percentumGj = 0;
    float percentumLx = 0;


    public HomeCircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaint();

    }

    public HomeCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();

    }

    public HomeCircleView(Context context) {
        super(context);
        initPaint();
    }

    /**
     * 没有动画
     */
    public void setData2(int zhengchangNum, int gaojingNum, int lixianNum){
        int sum = zhengchangNum + gaojingNum + lixianNum;

        percentumZc = (float) zhengchangNum / sum;
        percentumGj = (float) gaojingNum / sum;
        percentumLx = (float) lixianNum / sum;
        invalidate();
    }

    public void setData(int zhengchangNum, int gaojingNum, int lixianNum) {
        //总数
        int sum = zhengchangNum + gaojingNum + lixianNum;

        //percentumZc = (float) zhengchangNum / sum;
        //percentumGj = (float) gaojingNum / sum;
        //percentumLx = (float) lixianNum / sum;

        //invalidate();


        /**
         * ---------  实现动画
         */
        final float f1 = (float) zhengchangNum / sum;
        final float f2 = (float) gaojingNum / sum;
        final float f3 = (float) lixianNum / sum;

        reset();//重置归零
        BaseTimer.getInstans().startInterval(10, new BaseTimer.TimerCallBack() {
            @Override
            public void callback() {
                if (percentumZc < f1) {
                    percentumZc = percentumZc + 0.01f;
                } else if (percentumLx < f3) {
                    percentumZc = f1;
                    percentumLx = percentumLx + 0.01f;
                } else if (percentumGj < f2) {
                    percentumLx = f3;
                    percentumGj = percentumGj + 0.01f;
                } else {
                    percentumGj = f2;
                    BaseTimer.getInstans().killTimer();
                }
                invalidate();

            }
        });
    }

    private void reset() {
        percentumZc = 0;
        percentumLx = 0;
        percentumGj = 0;
    }

    void initPaint() {
        paintZc = new Paint();
        paintGj = new Paint();
        paintLx = new Paint();

        paintZc.setAlpha(0);
        paintGj.setAlpha(0);
        paintLx.setAlpha(0);

        initPaint(paintZc);
        initPaint(paintGj);
        initPaint(paintLx);

        paintZc.setColor(Color.parseColor("#3BBE5C"));
        paintGj.setColor(Color.parseColor("#EC9421"));

        paintLx.setColor(Color.parseColor("#F34A4A"));

    }

    /**
     * 共同的属性
     *
     * @param paint
     */
    private void initPaint(Paint paint) {
        //设置空心 的边框宽度
        paint.setStrokeWidth(DensityUtils.dip2px(BaseApplication.getContext(), 10.0f));

        paint.setStyle(Style.STROKE);
        paint.setAntiAlias(true);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        try {

            //整个圆外切的矩形
            area = new RectF(0 + this.getPaddingLeft(),
                    0 + this.getPaddingTop(),
                    this.getWidth() - this.getPaddingRight(),
                    this.getHeight() - this.getPaddingBottom());

//            LogUtils.w("360 * percentumZc ==  " + 360 * percentumZc);
//            LogUtils.w("360 * percentumLx ==  " + 360 * percentumLx);
//            LogUtils.w("360 * percentumGj ==  " + 360 * percentumGj);


            canvas.drawArc(area, -180, 360 * percentumZc, false, paintZc);
            canvas.drawArc(area, -180 + 360 * percentumZc, 360 * percentumLx, false, paintLx);
            canvas.drawArc(area, -180 + 360 * (percentumLx + percentumZc), 360 * percentumGj, false, paintGj);

        } catch (Exception e) {
            LogUtils.w(e.toString());
        }

    }

}

