package com.bjke.project1.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liyou on 2018/4/10.
 */

public class SportBarChart extends View {

    private float textSize;//字体大小
    private int textColor;//字体颜色
    private int barColor;//bar的颜色
    private int splitLineColor;//分割线颜色
    private Paint barPaint, yLinePaint, dataPaint;
    private int w, h;
    private int splitNum = 5; //分割线条数
    private float[] lineHeight = new float[splitNum];//线条的高度
    private float xOffSet;
    private int MAX_VALUE;
    private int barNum = 96;
    private float barWidth;//bar与bar之间的宽度  【w - 左边 - 右边】

    private float barWid;// 每个bar的宽度 【barWidth / 96】

    // x轴的宽度
    private float xWidth;

    private float yScale;// bar的垂直单位刻度

    private int totalCount;

    /**
     * 构造函数
     *
     * @param context
     * @param attrs
     */
    public SportBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);

        initPaint();

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        this.w = w;
        this.h = h;

        calculate();
        initYscale();

    }

    private void calculate() {
        for(int i = 0; i< splitNum; i++){
            lineHeight[i] = h / splitNum * i;
        }

        // x轴偏移量
        barPaint.setTextSize(textSize);
//        xOffSet = ViewUtil.getTextRectWidth(barPaint, "10");

        // bar与bar之间的宽度
        barWidth = (w - 2.8f * xOffSet) / barNum;//(总宽度-左边-右边)/96
        // 每个bar的宽度
        barWid = barWidth * 0.5f;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画中间的数据
        drawCenterData(canvas);
    }


    /**
     * 画中间部分的图表数据等
     *
     * @param canvas
     */
    private void drawCenterData(Canvas canvas) {

        // 画x轴、坐标值、bar值、数据
        drawX(canvas);

        // 画平行于x轴的数据分割线
        drawXLine(canvas);

    }

    /**
     * 画平行于x轴的数据分割虚线
     *
     * @param canvas
     */
    private void drawXLine(Canvas canvas) {
        for(int i = 0; i < splitNum - 1; i++){
            Path path = new Path();
            path.moveTo(1.4f * xOffSet, lineHeight[i]);
            path.lineTo(w - 1.4f * xOffSet,lineHeight[i]);
            canvas.drawPath(path, yLinePaint);
        }
    }

    /**
     * 画X轴
     *
     * @param canvas
     */
    private void drawX(Canvas canvas) {


        // 画x轴的坐标值
        xWidth = w - 3 * xOffSet;


        // 画x轴的96个bar
        barPaint.setStrokeWidth(barWid);
        barPaint.setTextAlign(Paint.Align.CENTER);

        dataPaint.setStrokeWidth(barWid);
        dataPaint.setTextAlign(Paint.Align.CENTER);

        for (int i = 0; i < barNum; i++) {
            float startX = barWidth * i + barWid + 1.4f * xOffSet;
            float startY = lineHeight[splitNum - 1];
            float stopX = barWidth * i + barWid + 1.4f * xOffSet;
            float stopY = lineHeight[splitNum - 1] - barWid;
            canvas.drawLine(startX, startY, stopX, stopY, barPaint);
//            if (items != null && items.size() > 0 && i<items.size()) {
////                    canvas.drawRoundRect(startX, startY, stopX, startY - (int) (yScale * (float) (items.get(i).getStepCount())),15.0f,15.0f, dataPaint);
//                canvas.drawLine(startX, startY, stopX, startY - (int) (yScale * (float) (items.get(i).getStepCount())), dataPaint);
//            }
        }
    }


    private void initYscale() {
        // 垂直单位的刻度
        if (MAX_VALUE != 0) {
            yScale = (lineHeight[splitNum - 1] - lineHeight[0]) / MAX_VALUE;
        }
    }

    /**
     * 初始化数据
     * @param items
     */

}
