package com.bjke.project1.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.bean.QuickLotteryBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liyou on 2018/4/12.
 */

public class QuickLotteryAdpter extends BaseAdapter{
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private QuickLotteryBean bean;
    private Activity activity;
    public QuickLotteryAdpter(Activity activity,QuickLotteryBean bean){
        this.activity = activity;
        this.bean = bean;
    }
    @Override
    public int getCount() {
        if (bean==null)return 0;

        return bean.data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(activity, R.layout.item_quicklottery,null);
            holder = new ViewHolder();
            holder.number = convertView.findViewById(R.id.number);
            holder.ball = convertView.findViewById(R.id.ball);
            holder.time = convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ball.removeAllViews();
        holder.number.setText("第 "+bean.data.get(position).qishu+" 期");
        String ball = bean.data.get(position).balls;
        String[] strs = ball.split(" ");

        for (int i=0;i<strs.length;i++){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i!=0)
                layoutParams.leftMargin=(int)activity.getResources().getDimension(R.dimen.x8);
            TextView textView = new TextView(activity);
            textView.setTextSize(12);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.circle_blue);
            textView.setText(strs[i].trim());
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(layoutParams);
            holder.ball.addView(textView);
        }
//        holder.ball.setText(bean.data.get(position).balls);
        Long time = Long.parseLong(bean.data.get(position).opentime)*1000;
        Date date = new Date();
        date.setTime(time);
        holder.time.setText(sdf.format(date));
        return convertView;
    }

    public void setData(QuickLotteryBean bean){
        this.bean = bean;
        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView number;
        LinearLayout ball;
        TextView time;
    }
}
