package com.bjke.project1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.bjke.project1.R;
import com.bjkj.library.utils.DensityUtils;

/**
 * Created by liyou on 2018/4/10.
 */

public class LineView extends View{


    private Paint mXYPaint;
    private Paint mPointPaint;
    private Paint mTextPaint;
    int w;
    int h;
    int height;
    int width;
    int maxValue=240;
    int splitNum=4;
    float yScal;
    int[] datas=new int[]{180,120,60,0};
    float[] spliteLineHeight = new float[splitNum];
    int paddingLeft=60;
    int paddingRight=15;
    int paddingTop=15;
    int paddingBottom=15;
    public LineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }
    private void initPaint(){
        mXYPaint= new Paint();
        mXYPaint.setStrokeWidth(1);
        mXYPaint.setStyle(Paint.Style.STROKE);
        mXYPaint.setColor(getResources().getColor(R.color.grey));

        mPointPaint = new Paint();
        mPointPaint.setColor(getResources().getColor(R.color.red));

        mTextPaint = new TextPaint();
        mTextPaint.setColor(getResources().getColor(R.color.grey));
        mTextPaint.setStrokeWidth(2);
        mTextPaint.setTextSize(DensityUtils.sp2px(getContext(),12));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w=w;
        this.h=h;
        height = h-paddingBottom-paddingTop;
        width=w-paddingRight-paddingLeft;
        calculateSplitLine();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initYScal();
        drawXY(canvas);

        drawMultX(canvas);

        drawDatas(canvas);
    }
    private void initDatas(int maxValue,int[] datas){
        this.maxValue = maxValue;
        this.datas = datas;
        calculateSplitLine();
    }
    //绘制XY轴
    private void drawXY(Canvas canvas){
        //绘制X轴
        canvas.drawLine(0+paddingLeft,h-paddingBottom,w-paddingRight,h-paddingBottom,mXYPaint);
        //绘制Y轴
        canvas.drawLine(0+paddingLeft,h-paddingBottom,0+paddingLeft,0+paddingTop,mXYPaint);

    }
    //绘制平行于XY轴的线
    private void drawMultX(Canvas canvas){
        for (int i=0;i<splitNum;i++){
            canvas.drawLine(0+paddingLeft,spliteLineHeight[i],width+paddingLeft,spliteLineHeight[i],mXYPaint);
            canvas.drawText(spliteLineHeight[i]/height*maxValue+"",0+paddingLeft,spliteLineHeight[i],mTextPaint);
        }
    }

    private void drawDatas(Canvas canvas){
        for (int i=0;i<datas.length;i++){

            canvas.drawCircle(i*((float)(width))/datas.length+paddingLeft,((float)datas[i])*yScal, DensityUtils.dp2px(getContext(),4),mPointPaint);

        }
    }

    private void initYScal(){
        yScal=(spliteLineHeight[splitNum-1]-spliteLineHeight[0])/maxValue;
    }

    private void calculateSplitLine(){
        for (int i=0;i<splitNum;i++){
            spliteLineHeight[i]=i/((float)splitNum)*(height);
        }
    }
}
