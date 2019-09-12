package com.baibeiyun.bbyiot.view.chart.my;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

/**
 *
 */
public class MyLineChartRenderer extends LineChartRenderer {

    float hLength = Utils.convertDpToPixel(15f);//横线长  15dp
    float vLength = Utils.convertDpToPixel(10f);//竖线长  10dp
    float rect = Utils.convertDpToPixel(8f);//矩形高低差/2
    float textX = Utils.convertDpToPixel(2f);//文本x坐标偏移量
    float textY = Utils.convertDpToPixel(3f);//文本y偏移量
    boolean isShowHLPoint = true;//是否显示最高点和最低点标识,默认显示
    int mWidth;//屏幕宽度
    float textSixe = 10f;//文字大小

    /**设置文字大小(sp值)
     * @param textSixe
     */
    public void setTextSixe(float textSixe) {
        this.textSixe = textSixe;
    }

    /**是否显示最高点和最低点标识
     * @return
     */
    public boolean isShowHLPoint() {
        return isShowHLPoint;
    }
    /**设置显示最高点和最低点标识
     * @return
     */
    public void setShowHLPoint(boolean showHLPoint) {
        isShowHLPoint = showHLPoint;
    }

    /**得到横线长(dp值)
     * @return
     */
    public float gethLength() {
        return hLength;
    }

    /**设置横线长(px值)
     * @param hLength
     */
    public void sethLength(float hLength) {
        this.hLength = Utils.convertDpToPixel(hLength);
    }
    /**得到竖线长(dp值)
     * @return
     */
    public float getvLength() {
        return vLength;
    }
    /**设置竖线长(px值)
     * @return
     */
    public void setvLength(float vLength) {
        this.vLength = Utils.convertDpToPixel(vLength);
    }

    /**设置矩形高低偏移量(px值)*/
    public void setRect(float rect) {
        this.rect = rect;
    }

    /**设置文字X轴偏移量(px值)
     * @param textX
     */
    public void setTextX(float textX) {
        this.textX = Utils.convertDpToPixel(textX);
    }

    /**设置文字Y轴偏移(px值)
     * @return
     */
    public void setTextY(float textY) {
        this.textY = Utils.convertDpToPixel(textY);
    }



    public MyLineChartRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler, int width) {
        super(chart, animator, viewPortHandler);
        mWidth = width;
    }

    /**
     * 取线上最大值
     *
     * @param lists
     * @return
     */
    private float[] getMaxFloat(List<Entry> lists) {
        float[] maxEntry = new float[2];
        for (int i = 0; i < lists.size() - 1; i++) {
            if (i == 0) {
                maxEntry[0] = lists.get(i).getX();
                maxEntry[1] = lists.get(i).getY();
            }
            if (maxEntry[1] < lists.get(i + 1).getY()) {
                maxEntry[0] = lists.get(i + 1).getX();
                maxEntry[1] = lists.get(i + 1).getY();
            }

        }
        return maxEntry;
    }
    /**
     * 取线上最小值
     *
     * @param lists
     * @return
     */
    private float[] getMinFloat(List<Entry> lists) {
        float[] mixEntry = new float[2];
        for (int i = 0; i < lists.size() - 1; i++) {
            if (i == 0) {
                mixEntry[0] = lists.get(i).getX();
                mixEntry[1] = lists.get(i).getY();
            }
            if (mixEntry[1] > lists.get(i + 1).getY()) {
                mixEntry[0] = lists.get(i + 1).getX();
                mixEntry[1] = lists.get(i + 1).getY();
            }

        }
        return mixEntry;
    }

    @Override
    public void drawValues(Canvas c) {
        super.drawValues(c);
        if (isShowHLPoint) {
            LineDataSet dataSetByIndex = (LineDataSet) mChart.getLineData().getDataSetByIndex(0);
            Transformer trans = mChart.getTransformer(dataSetByIndex.getAxisDependency());
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿画笔
            paint.setTextSize(Utils.convertDpToPixel(textSixe));//设置字体大小
            //画高点标记
            drawHighPoint(dataSetByIndex,trans,paint,c);
            //画低点标记
            drawLowPoint(dataSetByIndex,trans,paint,c);
        }
    }

    /**
     * 画低点标记
     */
    private void drawLowPoint(LineDataSet dataSetByIndex, Transformer trans, Paint paint, Canvas c) {
        float[] minFloat = getMinFloat(dataSetByIndex.getValues());
        // 得到低点的屏幕位置
        MPPointD minPoint = trans.getPixelForValues(minFloat[0],minFloat[1]);
        float lowX = (float)minPoint.x;
        float lowY = (float)minPoint.y;
        paint.setColor(Color.parseColor("#1ab546"));
        float rectLength = Utils.convertDpToPixel((minFloat[1] + "").length() * Utils.convertDpToPixel(4.0f)); // 矩形框长
        //画横竖线
        c.drawLine(lowX,lowY,lowX,lowY + vLength,paint);
        if (lowX > mWidth - mWidth / 3) {//标识朝左
            c.drawLine(lowX,lowY + vLength,lowX - hLength,lowY + vLength,paint);
            //画矩形
            c.drawRect(new Rect((int)(lowX - hLength - rectLength),(int) (lowY + vLength - rect),(int)(lowX - hLength),(int)(lowY + vLength + rect)),paint);
            //写数字
            paint.setColor(Color.WHITE);
            c.drawText(minFloat[1] + "",lowX - rectLength - hLength + textX,lowY + vLength + textY,paint);
        }else {//标识朝右
            c.drawLine(lowX,lowY + vLength,lowX + hLength,lowY + vLength,paint);
            c.drawRect(new Rect((int)(lowX + hLength),(int) (lowY + vLength - rect),(int)(lowX + hLength +  rectLength),(int)(lowY + vLength + rect)),paint);
            paint.setColor(Color.WHITE);
            c.drawText(minFloat[1] + "",lowX + hLength + textX,lowY + vLength + textY,paint);
        }
    }
    /**
     * 画高点标记
     */
    private void drawHighPoint(LineDataSet dataSetByIndex, Transformer trans, Paint paint, Canvas c) {
        float[] maxFloat = getMaxFloat(dataSetByIndex.getValues());
        // 得到高点的屏幕位置
        MPPointD maxPoint = trans.getPixelForValues(maxFloat[0],maxFloat[1]);
        float highX = (float)maxPoint.x;
        float highY = (float)maxPoint.y;
        float rectLength = Utils.convertDpToPixel((maxFloat[1] + "").length() * Utils.convertDpToPixel(4.0f)); // 矩形框长
        paint.setColor(Color.parseColor("#f4523c"));
        //画横竖线
        c.drawLine(highX,highY,highX,highY - vLength,paint);
        if (highX > mWidth - mWidth / 3) {//超过3分之2则左边显示
            c.drawLine(highX,highY - vLength,highX - hLength,highY - vLength,paint);
            //画矩形
            c.drawRect(new Rect((int)(highX - hLength - rectLength),(int) (highY - vLength - rect),(int)(highX - hLength),(int)(highY - vLength + rect)),paint);
            //写数字
            paint.setColor(Color.WHITE);
            c.drawText(maxFloat[1] + "",highX - hLength - rectLength + textX,highY - vLength + textY,paint);
        }else {
            c.drawLine(highX,highY - vLength,highX + hLength,highY - vLength,paint);
            c.drawRect(new Rect((int)(highX + hLength),(int) (highY - vLength - rect),(int)(highX + rectLength + hLength),(int)(highY - vLength + rect)),paint);
            paint.setColor(Color.WHITE);
            c.drawText(maxFloat[1] + "",highX + hLength + textX,highY - vLength + textY,paint);
        }
    }


}
