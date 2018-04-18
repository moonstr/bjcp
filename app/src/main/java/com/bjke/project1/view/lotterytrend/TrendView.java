package com.bjke.project1.view.lotterytrend;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.bjke.project1.R;
import com.bjkj.library.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuqiang on 2014/12/29.
 * 绘制的走势图View
 */
public class TrendView extends View {
    //小球文字画笔
    private Paint mPaintText = null;
    //左侧背景
    private Paint mPaintBgLeft = null;

    //小球画笔
    private Paint mBallPaint = null;

    //网格线画笔
    private Paint mPaintLine = null;

    //小球之间的连线画笔
    private Paint mLinkLine = null;

    //红球个数:33个
    private int mRedNum = 0;

    //蓝球个数:16个
    private int mBlueNum = 16;

    //网格的水平间距
    private float mDeltaX;

    //网格垂直间距
    private float mDeltaY;

    //当前View的宽度
    private int mWidth;

    //当前View的高度
    private int mHeight;

    //存储当前蓝球,方便于小球之间画连线
    private String[] mSaveBlueNum;

    //红球和篮球分布数据集合
    private List<TrendBeanOrigin> mBallList = new ArrayList<>();

    private int mBallWH = 0;
    float rowWidth=getResources().getDimension(R.dimen.x33);
    private int delta1 = 4;
    private int delta2 = 0;
    private int delta3 = 4;

    public TrendView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        mBallWH = getResources().getDimensionPixelSize(R.dimen.x16);
        initSource();
//        initData();
    }

    public void setData(List<TrendBeanOrigin> ballList) {
        mBallList = ballList;
        invalidate();
    }

    public void setDataNew(List<TrendViewData> trendViewDataList) {
        List<TrendBeanOrigin> bl = new ArrayList<>();
        for (TrendViewData bean : trendViewDataList) {
            TrendBeanOrigin trendBeanOrigin = new TrendBeanOrigin();
            StringBuilder sb = new StringBuilder();
            for (Integer in : bean.getBallLeft()) {
                sb.append(in).append(",");
            }
            sb.append(bean.getBallRight());
            trendBeanOrigin.setWinNumber(sb.toString());
            bl.add(trendBeanOrigin);
        }
        mBallList = bl;
        invalidate();
    }

    /***
     * 初始化资源
     */
    private void initSource() {
        rowWidth=getResources().getDimension(R.dimen.x33);
        int dpValue = getScreenDenisty();
        //网格线画笔
        mPaintLine = new Paint();
        mPaintLine.setColor(Color.GRAY);
        mPaintLine.setAntiAlias(true);
        mPaintLine.setStrokeWidth(dpValue * 0.6f / 160);

        //小球上面的文字画笔
        mPaintText = new Paint();
        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextSize((dpValue * 12 / 160));
        mPaintText.setAntiAlias(true);
        mPaintText.setStrokeWidth(2f);

        //左侧1-6的背景
        mPaintBgLeft = new Paint();
        mPaintBgLeft.setColor(Color.parseColor("#cdcdcd"));
        mPaintBgLeft.setAntiAlias(true);

        //小球之间连线画笔
        mLinkLine = new Paint();
        mLinkLine.setColor(getResources().getColor(R.color.main_yellow));
        mLinkLine.setAntiAlias(true);
        mLinkLine.setStrokeWidth(dpValue * 0.6f / 160);

        //小球画笔
        mBallPaint = new Paint();
        mBallPaint.setColor(getResources().getColor(R.color.main_blue));
        mBallPaint.setTextSize((dpValue * 12 / 160));
        mBallPaint.setAntiAlias(true);
        mBallPaint.setStrokeWidth(2f);
        //设置单个网格的水平和垂直间距
        this.mDeltaY = rowWidth;

        Log.i("delta", "deltay:" + mDeltaY);//高度;50

        this.mDeltaX = this.mDeltaY;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置测量View的大小:宽度和高度
        setMeasuredDimension((int) ((mRedNum + mBlueNum) * mDeltaX), (int)rowWidth*20);
        //取得测量之后当前View的宽度
        this.mWidth = getMeasuredWidth();
        //取得测量之后当前View的高度
        this.mHeight = getMeasuredHeight();
        //重新绘制,不重绘,不会生效;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBallList != null && mBallList.size() > 0) {
            drawXYLine(canvas);
            drawRedBall(canvas);
            drawBlueBall(canvas);
            drawLinkLine(canvas);
        }
    }

    /***
     * 绘制红球在网格中的分布图
     * @param canvas 画布
     */
    private void drawRedBall(Canvas canvas) {
//        mBallPaint.setARGB(0, 255, 255, 255);
//        mBallPaint.setColor(Color.RED);
        //保存每期的开奖号码;蓝球
        mSaveBlueNum = new String[mBallList.size()];
        //最外面控制期号
        for (int i = 0; i < mBallList.size(); i++) {
            //左侧1-6的背景
//            Rect srcBg = new Rect();
//            for (int j = 0; j < 6; j++) {
//                int number = j + 1;
//                float[] xy = this.translateBallXY(i, number - 1);
//                srcBg.left = (int) (xy[0] + getLineWidth());
//                srcBg.top = (int) (xy[1] + getLineWidth());
//                srcBg.bottom = (int) (srcBg.top + mDeltaY - getLineWidth());
//                srcBg.right = (int) (srcBg.left + mDeltaX - getLineWidth());
////                canvas.drawRect(srcBg, mPaintBgLeft);
//            }

            TrendBeanOrigin trendBeanOrigin = mBallList.get(i);
            String winNum = trendBeanOrigin.getWinNumber();
            //拆分中奖号码:红球和蓝球;
            String[] numArr = winNum.split("[,+]");
            //test:04,05,08,11,21,27,08,01
            //源图矩形位置left,top,right,bottom
            Rect src = new Rect();
            //目标位置left,top,right,bottom
            Rect dst = new Rect();
            //最里面控制（绘制）每期所在行,红球和蓝球分布位置
            for (int j = 0; j < numArr.length; j++) {
                int number = Integer.parseInt(numArr[j]);
                float[] xy = this.translateBallXY(i, number - 1);
                src.left = 0;
                src.top = 0;
                src.bottom = mBallWH;
                src.right = mBallWH;

                dst.left = (int) (xy[0] + this.mDeltaX * 0.05f);
                dst.top = (int) (xy[1] );
                dst.bottom = (int) (dst.top + mDeltaY * 0.8f);
                dst.right = (int) (dst.left + mDeltaX * 0.85f);
                RectF rf = new RectF(dst.left, dst.top, dst.right, dst.bottom);
                if (j < numArr.length - 1) {
                    //这个是画圆
//                    canvas.drawOval(rf, mBallPaint);
//                    canvas.drawCircle(dst.left+rowWidth/2, dst.top+rowWidth/2, DensityUtils.dp2px(getContext(),4),mBallPaint);
                    //如果有合适的小球图片,就用下面的方法...上面的去掉
                    //这个是画图片
//                    canvas.drawBitmap(mRedBitmapBall,src,dst,mBallPaint);

                    //显示的数字小于10
                    Log.i("绘制文字红球：",""+ String.valueOf(number));
                    if (number < 10) {
                        //居中显示:占一个字符
                        canvas.drawText(" " + String.valueOf(number), dst.left + src.right / 2, dst.top + this.mDeltaY * 0.5f, mPaintText);
                    } else {
                        canvas.drawText(String.valueOf(number), dst.left + src.right / 2, dst.top + this.mDeltaY * 0.5f, mPaintText);
                    }
                } else if (j == numArr.length - 1) {
                    //存储篮球号码...
                    mSaveBlueNum[i] = numArr[j];
                }
            }
        }
    }

    /***
     * 绘制蓝球
     * @param canvas
     */
    private void drawBlueBall(Canvas canvas) {
//        mBallPaint.setARGB(0, 255, 255, 255);
        Rect src = new Rect();
        Rect dst = new Rect();
        //最外面控制期号
        for (int i = 0; i < mBallList.size(); i++) {
            int value = Integer.parseInt(mSaveBlueNum[i]);
            float[] xy = this.translateBallXY(i, value  - delta2);
            //重新定位蓝球在x轴的位置;在红球后面;
            xy[0] = xy[0] + mRedNum * mDeltaX;
            src.left = 0;
            src.top = 0;
            src.bottom = mBallWH;
            src.right = mBallWH;

            dst.left = (int) (xy[0] + this.mDeltaX * 0.05f);
            dst.top = (int) (xy[1] + this.mDeltaY * 0.1f);
            dst.bottom = (int) (dst.top + mDeltaY * 0.8f);
            dst.right = (int) (dst.left + mDeltaX * 0.85f);

//            RectF rf = new RectF(dst.left, dst.top, dst.right, dst.bottom);
            //画圆
//            canvas.drawOval(rf, mBallPaint);

            //如果有合适的小球图片,就用下面的方法...上面的去掉
            //在对应的位置画球球
            // canvas.drawBitmap(mBlueBitmapBall, src, dst, this.mBallPaint);
            Log.i("绘制文字篮球：",""+ String.valueOf(value));
            canvas.drawCircle(dst.left+rowWidth/2-DensityUtils.dp2px(getContext(),2), dst.top+rowWidth/2-DensityUtils.dp2px(getContext(),4), DensityUtils.dp2px(getContext(),6),mBallPaint);
//            canvas.drawCircle(lastXy[0] + mDeltaX * 0.5f, lastXy[1] + mDeltaY * 0.5f, DensityUtils.dp2px(getContext(),6),mBallPaint);
            if (value < 10) {
                //居中显示:占一个字符
                canvas.drawText(" " + String.valueOf(value), dst.left + src.right / 2, dst.top + this.mDeltaY * 0.5f, mPaintText);
            } else {
                canvas.drawText(String.valueOf(value), dst.left + src.right / 2, dst.top + this.mDeltaY * 0.5f, mPaintText);
            }
//            canvas.drawCircle(dst.left+rowWidth/2, dst.top+rowWidth/2, DensityUtils.dp2px(getContext(),4),mBallPaint);

        }
    }

    /**
     * 蓝球之间的连线
     *
     * @param canvas
     */
    private void drawLinkLine(Canvas canvas) {
        //先获取第一个球的x轴和y轴位置
        float[] lastXy = this.translateBallXY(0, Integer.parseInt(mSaveBlueNum[0]) - delta2);
        //重新定位:当前X轴的位置还要加上第33号红球的位置...
        lastXy[0] = lastXy[0] + mRedNum * mDeltaX;
        for (int i = 0; i < mSaveBlueNum.length; i++) {
            int value = Integer.parseInt(mSaveBlueNum[i]);
            float[] xy = this.translateBallXY(i, value  );
            //重新定位...同上;
            xy[0] = xy[0] + mRedNum * mDeltaX;
            //画两个球的中心点的连线...
            canvas.drawLine(lastXy[0] + mDeltaX * 0.5f, lastXy[1] + mDeltaY * 0.5f, xy[0] + mDeltaX * 0.5f, xy[1] + mDeltaY * 0.5f, mLinkLine);
//            canvas.drawCircle(lastXy[0] + mDeltaX * 0.5f, lastXy[1] + mDeltaY * 0.5f, DensityUtils.dp2px(getContext(),6),mBallPaint);

            //赋值....
            lastXy[0] = xy[0];
            lastXy[1] = xy[1];
        }
    }

    /***
     * 绘制X轴和Y轴的网格线
     * @param canvas 画布
     */
    private void drawXYLine(Canvas canvas) {
        float rowWidth=getResources().getDimension(R.dimen.x33);
        //期号数:X轴;含顶部和底部的边角线;
        for (int i = 0; i <= 20; i++) {
            canvas.drawLine(0, rowWidth * i, rowWidth*20, rowWidth * i, mPaintLine);
            canvas.drawLine(rowWidth * i, 0, rowWidth * i, rowWidth*20, mPaintLine);

        }
//        //33个红球+16个篮球:Y轴;含最左边的边角和最右边的边角线;
//        for (int i = 0; i <= (mBallList.size()); i++) {
//            canvas.drawLine(rowWidth * i, 0, rowWidth * i, rowWidth*mBallList.size(), mPaintLine);
//        }
    }

    /**
     * 返回小球坐标
     *
     * @param rowIndex   行索引X轴;
     * @param valueIndex 当前中奖号码数字:相当于在哪个位置处:Y轴的索引.
     * @return
     */
    private float[] translateBallXY(int rowIndex, int valueIndex) {
        float[] xy = new float[2];
        xy[0] = this.mDeltaX * valueIndex;
        xy[1] = this.mDeltaY * rowIndex;
        return xy;
    }

    /**
     * 获取当前屏幕的密度
     *
     * @return
     */
    public int getScreenDenisty() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.densityDpi;
    }

    private float getLineWidth() {
        return getScreenDenisty() * 0.6f / 160;
    }
}