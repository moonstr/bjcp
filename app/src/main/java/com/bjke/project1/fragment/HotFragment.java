package com.bjke.project1.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bjke.project1.R;
import com.bjke.project1.activity.WebActivity;
import com.bjke.project1.adapter.HotAdapter;
import com.bjke.project1.base.BaseFragment;
import com.bjke.project1.bean.HotBean;
import com.bjke.project1.modle.LotteryModle;
import com.bjkj.library.okhttp.HttpCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyou on 2018/3/29.
 */

public class HotFragment extends BaseFragment {

    private TabLayout mTab;
    private ViewPager mPager;
    HotBean hotBean;
    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView() {
        mTab = findViewById(R.id.tab);
        mPager = findViewById(R.id.viewPager);
        initViewPager();
    }

    private void initViewPager() {
        // 创建一个集合,装填Fragment
        ArrayList<HotChildFragment> fragments = new ArrayList<>();
        // 装填
        fragments.add(HotChildFragment.newInstance("http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300203&src=0000100001%7C6000003060"));
        fragments.add(HotChildFragment.newInstance("http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300205&src=0000100001%7C6000003060"));
        fragments.add(HotChildFragment.newInstance("http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300207&src=0000100001%7C6000003060"));
        fragments.add(HotChildFragment.newInstance("http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300209&src=0000100001%7C6000003060"));
        fragments.add(HotChildFragment.newInstance("http://m.zhcw.com/clienth5.do?transactionType=8021&pageNo=1&pageSize=20&busiCode=300210&src=0000100001%7C6000003060"));
        // 创建ViewPager适配器
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getFragmentManager());
        myPagerAdapter.setFragments(fragments);
        // 给ViewPager设置适配器
        mPager.setAdapter(myPagerAdapter);
        // TabLayout 指示器 (记得自己手动创建4个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        mTab.addTab(mTab.newTab());
        // 使用 TabLayout 和 ViewPager 相关联
        mTab.setupWithViewPager(mPager);
        // TabLayout指示器添加文本
        mTab.getTabAt(0).setText("收获");
        mTab.getTabAt(1).setText("派奖");
        mTab.getTabAt(2).setText("活动");
        mTab.getTabAt(3).setText("直播");
        mTab.getTabAt(4).setText("公告");
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        super.initListener();

    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<HotChildFragment> mFragmentList;

        public void setFragments(ArrayList<HotChildFragment> fragments) {
            mFragmentList = fragments;
        }

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public HotChildFragment getItem(int position) {

            HotChildFragment fragment = mFragmentList.get(position);

            return fragment;
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }
    }
}
