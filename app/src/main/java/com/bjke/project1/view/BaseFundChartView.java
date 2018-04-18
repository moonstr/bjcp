package com.bjke.project1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by liyou on 2018/4/10.
 */

public class BaseFundChartView extends View {

    Paint linePaint;
    Paint textPaint;
    Paint xyChartPaint;
    Paint chartLinePaint;

    public BaseFundChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseFundChartView(Context context) {
        this(context, null);
    }

    public BaseFundChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    PathEffect effect;
    Path path;

    private void init() {
        linePaint = new Paint();
        textPaint = new Paint();
        xyChartPaint = new Paint();
        chartLinePaint = new Paint();

        //设置绘制模式为-虚线作为背景线。
        effect = new DashPathEffect(new float[] { 6, 6, 6, 6, 6}, 2);
        //背景虚线路径.
        path = new Path();
        //只是绘制的XY轴
        linePaint.setStyle(Paint.Style.STROKE);
//        linePaint.setStrokeWidth((float) 0.7);
        linePaint.setStrokeWidth((float) 1.0);             //设置线宽

        linePaint.setColor(Color.BLACK);
        linePaint.setAntiAlias(true);// 锯齿不显示

        //XY刻度上的字
        textPaint.setStyle(Paint.Style.FILL);// 设置非填充
        textPaint.setStrokeWidth(1);// 笔宽5像素
        textPaint.setColor(Color.BLACK);// 设置为蓝笔
        textPaint.setAntiAlias(true);// 锯齿不显示
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(15);

        //绘制XY轴上的字：Y开关状态、X时间
        xyChartPaint.setStyle(Paint.Style.FILL);
        xyChartPaint.setStrokeWidth(1);
        xyChartPaint.setColor(Color.BLUE);
        xyChartPaint.setAntiAlias(true);
        xyChartPaint.setTextAlign(Paint.Align.CENTER);
        xyChartPaint.setTextSize(18);
        //绘制的折线
        chartLinePaint.setStyle(Paint.Style.FILL);
        chartLinePaint.setStrokeWidth(3);
        chartLinePaint.setColor(Color.RED);//(1)黄色
        chartLinePaint.setAntiAlias(true);



    }
    float gridX,gridY,xSpace = 0,ySpace = 0,spaceYT = 0,yStart=0;
    String[] dateX = null;
    float[] dateY = null;

    List<float[]> data = null;

    public void setData(List<float[]> data) {
        this.data = data;
        invalidate();
    }

    public void setDateX(String[] dateX) {
        this.dateX = dateX;
    }

    public void setDateY(float[] dateY) {
        this.dateY = dateY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //基准点。
        gridX = 40;
        gridY = getHeight() - 50;
        //XY间隔。
        if(dateX!=null&&dateX.length>0){
            xSpace = (getWidth() - gridX)/dateX.length;
        }

        if(dateY!=null&&dateY.length>0){
            ySpace = (gridY - 70)/dateY.length;
            yStart = dateY[0];
            if(dateY.length>2){
                spaceYT = Math.abs(dateY[1]-dateY[0]);
            }
        }

//        UIUtils.log("rewqfdesa",gridY,"fdsafdsa");

        //画Y轴(带箭头)。
        canvas.drawLine(gridX, gridY - 20 - 10, gridX, 30 + 10, linePaint);
        canvas.drawLine(gridX, 30 + 10, gridX - 6, 30 + 14 + 10, linePaint);//Y轴箭头。
        canvas.drawLine(gridX, 30 + 10, gridX + 6, 30 + 14 + 10, linePaint);

        //画Y轴名字。
        //由于是竖直显示的，先以原点顺时针旋转90度后为新的坐标系
        //canvas.rotate(-90);
        //当xyChartPaint的setTextAlign（）设置为center时第二、三个参数代表这四个字中点所在的xy坐标

        //canvas.drawText("开关状态", -((float) (getHeight() - 60) - 15 - 5 - 1 / ((float) 1.6 * 1) * (getHeight() - 60) / 2), gridX - 15, xyChartPaint);
        //绘制Y轴坐标


        //canvas.rotate(90); //改变了坐标系还要再改过来
        float y = 0;
        //画X轴。
        y = gridY - 20;
        canvas.drawLine(gridX, y - 10, getWidth(), y - 10, linePaint);//X轴.
        canvas.drawLine(getWidth(), y - 10, getWidth() - 14, y - 6 - 10, linePaint);//X轴箭头。
        canvas.drawLine(getWidth(), y - 10, getWidth() - 14, y + 6 - 10, linePaint);

        //绘制X刻度坐标。
        float x = 0;
        if(dateX!=null){
            for (int n = 0; n < dateX.length; n++) {
                //取X刻度坐标.
                x = gridX + (n) * xSpace;//在原点(0,0)处也画刻度（不画的话就是n+1）,向右移动一个跨度。
                //画X轴具体刻度值。
                if (dateX[n] != null) {
                    //canvas.drawLine(x, gridY - 30, x, gridY - 18, linePaint);//短X刻度。
                    canvas.drawText(dateX[n], x, gridY + 5, textPaint);//X具体刻度值。
                }
            }
        }

        float my = 0;

        if(dateY!=null){

            for(int n=0;n<dateY.length;n++){
                //取Y刻度坐标.
                my = gridY-30 - (n)*ySpace;
//                UIUtils.log(my,"fdsafss",ySpace);
                //画y轴具体刻度值。
                canvas.drawText(String.valueOf(dateY[n]),gridX-15,my,textPaint);

                if(my != gridY-30){
                    linePaint.setPathEffect(effect);//设法虚线间隔样式。
                    //画除X轴之外的------背景虚线一条-------
                    path.moveTo(gridX, my);//背景【虚线起点】。
                    path.lineTo(getWidth(), my);//背景【虚线终点】。
                    canvas.drawPath(path, linePaint);
                }

            }
        }

        if(data!=null&&data.size()>0){
            float lastPointX = 0; //前一个点
            float lastPointY = 0;
            float currentPointX = 0;//当前点
            float currentPointY = 0;
            for(int n=0;n<data.size();n++){
                float da[] = data.get(n);
                for(int m=0;m<da.length;m++){
                    currentPointX = m * xSpace + gridX;
                    currentPointY = gridY-30 - ((da[m]-yStart)*(ySpace/spaceYT));
                    if(m>0){
                        canvas.drawLine(lastPointX, lastPointY, currentPointX, currentPointY, chartLinePaint);
                    }
                    lastPointX = currentPointX;
                    lastPointY = currentPointY;
                }

            }
        }
    }
}
