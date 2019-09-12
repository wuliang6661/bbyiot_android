package com.baibeiyun.bbyiot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.baibeiyun.bbyiot.application.BaseApplication;
import com.baibeiyun.bbyiot.utils.DensityUtils;

/**
 * 环形进度条
 */
public class CircleView extends View {

    Paint paint, paintbg, textpaint;
    RectF area;
    int value = 0;
    LinearGradient shader;

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();

    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public CircleView(Context context) {
        super(context);
        init();

    }

    public int getProgress() {
        return value;
    }

    public void setProgress(int value) {
        this.value = value;
        invalidate();
    }

    public void init() {
        paint = new Paint();
        paint.setAlpha(0);
        //paint.setStrokeWidth(50f);
        //设置空心的边框宽度
        paint.setStrokeWidth(DensityUtils
                .dip2px(BaseApplication.getContext(), 5.0f));

        //画笔的颜色 ---> 走过的进度  #00AAF3
        paint.setColor(Color.parseColor("#FF5965"));
        //当画笔样式为STROKE或FILL_AND_STROKE时，设置笔刷的粗细度
        paint.setStyle(Style.STROKE);
        paint.setAntiAlias(true);


        paintbg = new Paint();
        paintbg.setAlpha(0);

        //设置空心的边框宽度
        paintbg.setStrokeWidth(DensityUtils
                .dip2px(BaseApplication.getContext(), 5.0f));
        //  画笔的颜色 ---> 背景  #A0B5C2
        paintbg.setColor(Color.parseColor("#A0B5C2"));
        paintbg.setStyle(Style.STROKE);
        paintbg.setAntiAlias(true);

        //textpaint = new Paint();
        //textpaint.setTextSize(50f);
        //textpaint.setColor(Color.WHITE);        

        /*
        shader =new LinearGradient(0, 0, 400, 0, new int[] {    
                Color.BLUE, Color.WHITE}, null,    
               Shader.TileMode.CLAMP);
        paint.setShader(shader);
        */
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //canvas.drawColor(Color.GRAY);

//    	paintbg.setStrokeWidth(this.getWidth() / 75);
//        paint.setStrokeWidth(this.getWidth() / 75);

        area = new RectF(0 + this.getPaddingLeft(), 0 + this.getPaddingTop(), this.getWidth() - this.getPaddingRight(), this.getHeight() - this.getPaddingBottom());

        canvas.drawArc(area, -90, 360, false, paintbg);
        canvas.drawArc(area, -90, 360 * value / 100, false, paint);
        //canvas.drawText(value+"%", 270, 290, textpaint);
    }

}

