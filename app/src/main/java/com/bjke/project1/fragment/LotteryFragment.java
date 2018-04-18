package com.bjke.project1.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.activity.LotteryActivity;
import com.bjke.project1.base.BaseFragment;
import com.bjke.project1.view.CircleMenuLayout;

/**
 * Created by liyou on 2018/3/29.
 */

public class LotteryFragment extends BaseFragment {
    ListView mList;
    TypeAdapter adapter;
    CircleMenuLayout mCircleMenuLayout;
//    private TextView mTitle;
    String[] strs=new String[]{"双色球","大乐透","福彩3D","排列三","排列五","七星彩"};
    int[] imags=new int[]{R.mipmap.ssq,R.mipmap.dlt,R.mipmap.fc3d,R.mipmap.pl3,R.mipmap.pl5,R.mipmap.qxc};
//int[] imags=new int[]{R.mipmap.share_friend,
//        R.mipmap.share_qq, R.mipmap.share_qzone,
//        R.mipmap.share_morepics, R.mipmap.share_wechat,
//        R.mipmap.share_weibo};
    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_lottery;
    }

    @Override
    public void initView() {
//        mTitle = findViewById(R.id.title);
        mList=findViewById(R.id.list);
        mCircleMenuLayout = (CircleMenuLayout) findViewById(R.id.id_menulayout);
    }

    @Override
    public void initData() {
        mCircleMenuLayout.setMenuItemIconsAndTexts(imags,strs);
        adapter=new TypeAdapter(strs);
        mList.setAdapter(adapter);
    }

    @Override
    public void refreshData() {
        super.refreshData();

    }

    @Override
    public void initListener() {
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mActivity, LotteryActivity.class);
                intent.putExtra("type",i);
                startActivity(intent);
            }
        });
        mCircleMenuLayout.setOnMenuItemClickListener(new CircleMenuLayout.OnMenuItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {
                Intent intent = new Intent(mActivity, LotteryActivity.class);
                intent.putExtra("type",pos);
                startActivity(intent);
            }

            @Override
            public void itemCenterClick(View view) {

            }
        });
//        mTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(mActivity, TestActivity.class));
//            }
//        });
    }

    class TypeAdapter extends BaseAdapter {
        public String[] strs;
        public TypeAdapter(String[] strs){
            this.strs=strs;
        }
        @Override
        public int getCount() {
            return strs==null?0:strs.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            if (convertView==null){
                holder = new ViewHolder();
                convertView= LayoutInflater.from(mActivity).inflate(R.layout.item_main,null);
                holder.name=convertView.findViewById(R.id.name);
                holder.icon = convertView.findViewById(R.id.imag);
                convertView.setTag(holder);
            }else {
                holder=(ViewHolder)convertView.getTag();
            }
            holder.name.setText(strs[position]);
            holder.icon.setImageResource(imags[position]);
            return convertView;
        }
    }
    class ViewHolder{
        TextView name;
        ImageView icon;
    }
}
