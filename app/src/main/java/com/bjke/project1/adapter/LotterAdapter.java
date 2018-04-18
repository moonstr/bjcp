package com.bjke.project1.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.bean.LotteryBean;
import com.bjke.project1.utils.LogUtils;

/**
 * Created by liyou on 2018/3/28.
 */

public class LotterAdapter extends BaseAdapter{
    LotteryBean bean;
    Activity activity;
    public LotterAdapter(Activity activity,LotteryBean bean){
        this.activity=activity;
        this.bean=bean;

    }
    @Override
    public int getCount() {
        if (bean==null)return 0;
        if (bean.data==null){
            return 0;
        }else {
            return bean.data.size();
        }
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
            convertView= LayoutInflater.from(activity).inflate(R.layout.item_lottery,null);
            holder.title=convertView.findViewById(R.id.title);
            holder.ll=convertView.findViewById(R.id.ll);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        if (bean!=null){
            if (bean.data!=null){
                holder.title.setText("第 "+bean.data.get(position).expect+" 期"+"  "+bean.data.get(position).opentime);
                String data=bean.data.get(position).opencode;
                String[] codes=data.split("\\+");
                holder.ll.removeAllViews();
                for (int i=0;i<codes.length;i++){

                    String str=codes[i];
                    String[] strs=str.split(",");
                    for (int j =0;j<strs.length;j++){
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
//                        if (holder.ll.getChildCount()!=0){
                            layoutParams.leftMargin=(int)activity.getResources().getDimension(R.dimen.x16);
//                        }
                        TextView textView = new TextView(activity);
                        textView.setTextSize(12);
                        textView.setTextColor(Color.WHITE);
                        if (i!=0){
                            textView.setBackgroundResource(R.drawable.circle_red);
                        }else {
                            textView.setBackgroundResource(R.drawable.circle_blue);
                        }

                        textView.setText(strs[j]);
                        textView.setGravity(Gravity.CENTER);
                        textView.setLayoutParams(layoutParams);
                        holder.ll.addView(textView);
                    }
                }
                LogUtils.i(codes.length+"");
            }
        }

        return convertView;
    }

    public void setData(LotteryBean bean){
        this.bean=bean;
        notifyDataSetChanged();
    }
    class ViewHolder{
        TextView title;
        LinearLayout ll;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;
        TextView textView7;
    }

}
