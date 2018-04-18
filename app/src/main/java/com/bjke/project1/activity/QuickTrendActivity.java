package com.bjke.project1.activity;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.base.BaseActivity;
import com.bjke.project1.bean.QuickLotteryBean;
import com.bjke.project1.modle.SSCModle;
import com.bjke.project1.view.TitleView;
import com.bjke.project1.view.lotterytrend.HeadCustomGridView;
import com.bjke.project1.view.lotterytrend.HeaderHorizontalScrollView;
import com.bjke.project1.view.lotterytrend.LeftNumberCustomListView;
import com.bjke.project1.view.lotterytrend.LeftNumberSynchScrollView;
import com.bjke.project1.view.lotterytrend.ScrollChangeCallback;
import com.bjke.project1.view.lotterytrend.TrendScrollViewWidget;
import com.bjke.project1.view.lotterytrend.TrendView;
import com.bjke.project1.view.lotterytrend.TrendViewData;
import com.bjkj.library.okhttp.HttpCallBack;
import com.bjkj.library.utils.SPUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyou on 2018/4/12.
 */

public class QuickTrendActivity extends BaseActivity implements ScrollChangeCallback {
    private LinearLayout mContainer;
    private LeftNumberSynchScrollView mLeftScroll;
    private TrendScrollViewWidget mContentScroll;
    private HeaderHorizontalScrollView mHeadScroll;
    //数据区域
    private LeftNumberCustomListView mListView;
    private GridView mHeadGridView;
    private TrendView mTrendView;

    private List mList = null;
    private List mHeadData = null;
    QuickLotteryBean bean;
    @Override
    public int getLayoutId() {
        return R.layout.activity_quicktrend;
    }

    @Override
    public void initView() {
        mContainer = findViewById(R.id.container);
        mHeadScroll = (HeaderHorizontalScrollView) findViewById(R.id.trend_header_scroll);//right top
        mHeadGridView = (HeadCustomGridView) findViewById(R.id.grid_trend_header);//right top
        mLeftScroll = (LeftNumberSynchScrollView) findViewById(R.id.scroll_left);//left
        mListView = (LeftNumberCustomListView) findViewById(R.id.lv_number);//left
        mContentScroll = (TrendScrollViewWidget) findViewById(R.id.scroll_content);//right content
        mTrendView = (TrendView) findViewById(R.id.trend_view_content);
    }

    @Override
    public void initListener() {
        super.initListener();

        mLeftScroll.setScrollViewListener(this);
        //中间走势图的监听器
        mContentScroll.setScrollViewListener(this);
        //走势图顶部的监听器
        mHeadScroll.setScrollViewListener(this);
        //走势图底部的旋球滚动监听器
    }

    @Override
    public void initData() {
        super.initData();


        bean=getDataFromLocal();
        if (bean!=null){
            setData(bean);
        }
        bindHeaderData();
        SSCModle.getQuickLottery(getIntent().getStringExtra("tag"), new HttpCallBack<QuickLotteryBean>() {
            @Override
            public void success(QuickLotteryBean  quickLotteryBean) {
                setData(quickLotteryBean);
            }

            @Override
            public void fail(String errorStr) {

            }
        });
        addTextView();
    }
    private void addTextView(){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(  (int)getResources().getDimension(R.dimen.x120),
                LinearLayout.LayoutParams.MATCH_PARENT,1);

        if (bean!=null){
            mContainer.removeAllViews();
           String[]  strs = bean.data.get(1).balls.split(" ");
           if (strs.length<5){
               layoutParams.weight=1;

           }
           for (int i=0;i<strs.length;i++){
              final TextView textView = new TextView(mActivity);

               textView.setTextSize(24);
               textView.setWidth(0);
               textView.setGravity(Gravity.CENTER);
               textView.setBackground(getResources().getDrawable(R.drawable.trend_secletor));
               textView.setTextColor(getResources().getColor(R.color.main_blue));
               if (i==0){
                   textView.setBackgroundColor(getResources().getColor(R.color.main_blue));
                   textView.setTextColor(Color.WHITE);
               }
               textView.setText("第"+(i+1)+"位");
               textView.setId(i);
               textView.setLayoutParams(layoutParams);
               textView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       for (int i=0;i<mContainer.getChildCount();i++){
                           mContainer.getChildAt(i).setBackground(null);
                           ((TextView)mContainer.getChildAt(i)).setTextColor(getResources().getColor(R.color.main_blue));
                       }
                       setData(bean,textView.getId());
                       textView.setBackgroundColor(getResources().getColor(R.color.main_blue));
                       textView.setTextColor(Color.WHITE);
                   }
               });
               mContainer.addView(textView);
           }

        }
    }
    private void setData(QuickLotteryBean quickLotteryBean){
        bean = quickLotteryBean;
        mList = new ArrayList();
        List<TrendViewData> trendViewDataList = new ArrayList<>();
        for (int i=0;i<bean.data.size();i++){

            if (!TextUtils.isEmpty(bean.data.get(i).balls)){
                mList.add(bean.data.get(i).qishu);
                TrendViewData trendViewData = new TrendViewData();
                String[] strs = bean.data.get(i).balls.split(" ");
                trendViewData.setBallRight(Integer.parseInt(strs[0].trim()));
                trendViewDataList.add(trendViewData);
            }

        }
        DataAdapter adapter = new DataAdapter(R.layout.items);
        adapter.bindData(mList, false);
        mListView.setAdapter(adapter);
        mTrendView.setDataNew(trendViewDataList);
    }

    private void setData(QuickLotteryBean quickLotteryBean,int position){
        bean = quickLotteryBean;
        mList = new ArrayList();
        List<TrendViewData> trendViewDataList = new ArrayList<>();
        for (int i=0;i<bean.data.size();i++){

            if (!TextUtils.isEmpty(bean.data.get(i).balls)){
                mList.add(bean.data.get(i).qishu);
                TrendViewData trendViewData = new TrendViewData();
                String[] strs = bean.data.get(i).balls.split(" ");
                trendViewData.setBallRight(Integer.parseInt(strs[position].trim()));
                trendViewDataList.add(trendViewData);
            }

        }
        DataAdapter adapter = new DataAdapter(R.layout.items);
        adapter.bindData(mList, false);
        mListView.setAdapter(adapter);
        mTrendView.setDataNew(trendViewDataList);
    }


    /**
     * 绑定顶部数据显示:顶部数据只显示在一行;
     */
    private void bindHeaderData() {
        mHeadData = new ArrayList();
        //33个红球+16个蓝球;
        for (int i = 0; i <= 22; i++) {
            if (i < 0) {
                //红球区域
                mHeadData.add("" + i);
            } else {
                //蓝球区域
                mHeadData.add("" + i);
            }
        }

        DataAdapter adapter = new DataAdapter(R.layout.gridview_item);
        adapter.bindData(mHeadData, false);
        int deltaDp = getResources().getDimensionPixelSize(R.dimen.x33);
        //下面的代码是重新定位布局参数;让gridView数据都显示在一行;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(adapter.getCount() * deltaDp,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mHeadGridView.setLayoutParams(params);
        mHeadGridView.setColumnWidth(deltaDp);//列宽
        mHeadGridView.setStretchMode(GridView.NO_STRETCH);//伸展模式
        mHeadGridView.setNumColumns(adapter.getCount());//共有多少列
        mHeadGridView.setAdapter(adapter);
    }

    /***
     * 同步X轴位置
     * @param left
     */
    @Override
    public void changeXScroll(int left) {
        //顶部和底部容器滑动的回调;
        //此时需要同步中间走势的View的位置;
        mContentScroll.scrollTo(left, mContentScroll.getScrollY());
        //同步顶部自身的位置;
        mHeadScroll.scrollTo(left, 0);
    }

    /***
     * 同步Y轴的位置
     * @param top
     */
    @Override
    public void changeYScoll(int top) {
        //中间走势View滑动位置的改变回调;
        //同步左边期号的Y轴的位置
        mLeftScroll.scrollTo(0, top);
        //同步中间走势View的位置
        mContentScroll.scrollTo(mContentScroll.getScrollX(), top);
        //有走势图头部...
        mHeadScroll.scrollTo(mContentScroll.getScrollX(), 0);

    }

    /***
     * 数据适配器(含期号,走势图顶部数据,走势图底部数据显示)....
     */
    private class DataAdapter extends BaseAdapter {
        private List listData = null;

        private int layoutId;
        private int mLeftItemH;

        public DataAdapter(int layoutID) {
            this.layoutId = layoutID;
            mLeftItemH = getResources().getDimensionPixelSize(R.dimen.x33);
        }

        protected void bindData(List data, boolean footer) {
            this.listData = data;

        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(QuickTrendActivity.this).inflate(layoutId, parent, false);
            }
            final TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            if (layoutId == R.layout.items) {
                //最近才想起来那时候写的走势图，没有共享出来
                //14年写的，现在是16年了，两年前写的代码了，不想改了，就在这里适配下分割线了,读者可以自行“自定义”item也是比较简单的，
                //两年前我自定义了item，代码找不到了，现在就是为了上传到github上，就这样，凑合看吧
                View line1 = convertView.findViewById(R.id.v_line1);
                View line2 = convertView.findViewById(R.id.v_line2);
                ViewGroup.LayoutParams vP1 = line1.getLayoutParams();
                vP1.height = (int) (getScreenDenisty() * 0.6f / 160);
                line1.setLayoutParams(vP1);
                line2.setLayoutParams(vP1);
                int diff = mLeftItemH - vP1.height * 2;
                ViewGroup.LayoutParams contentParams = tv_content.getLayoutParams();
                contentParams.height = diff;
                tv_content.setLayoutParams(contentParams);
            } else {
                //R.layout.gridview_item
                View line3 = convertView.findViewById(R.id.v_line3);
                View line4 = convertView.findViewById(R.id.v_line4);
                ViewGroup.LayoutParams vP3 = line3.getLayoutParams();
                vP3.width = (int) (getScreenDenisty() * 0.6f / 160);
                line3.setLayoutParams(vP3);
                line4.setLayoutParams(vP3);
                int diff = mLeftItemH - vP3.width * 2;
                RelativeLayout rlContent = (RelativeLayout) convertView.findViewById(R.id.relative_layout);
                ViewGroup.LayoutParams contentParams = rlContent.getLayoutParams();
                contentParams.width = diff;
                rlContent.setLayoutParams(contentParams);
            }

            tv_content.setText(listData.get(position).toString());
            return convertView;
        }
    }

    /**
     * 获取当前屏幕的密度
     *
     * @return
     */
    private int getScreenDenisty() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return dm.densityDpi;
    }

    private QuickLotteryBean getDataFromLocal(){
        Gson gson = new Gson();
        QuickLotteryBean quickLotteryBean=  gson.fromJson((String )SPUtils.get("getQuickLottery",""),QuickLotteryBean.class);
        return quickLotteryBean;
    }
}
