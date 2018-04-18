package com.bjke.project1.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.bean.QuickBean;
import com.bjke.project1.bean.SSCBean;
import com.bjkj.library.utils.LogUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liyou on 2018/4/11.
 */

public class QuickAdapter extends BaseAdapter{
    private QuickBean bean;
    private Activity activity;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public QuickAdapter(Activity activity, QuickBean bean){
        this.activity=activity;
        this.bean=bean;
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
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(activity, R.layout.item_quick,null);
            holder.name=convertView.findViewById(R.id.name);
            holder.ll=convertView.findViewById(R.id.ll);
            holder.number=convertView.findViewById(R.id.qishu);
            holder.time=convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.name.setText(bean.data.get(position).game_name);
        String balls = bean.data.get(position).prev.get(0).balls;
        holder.ll.removeAllViews();
//        holder.ball.setText(bean.data.get(position).prev.get(0).balls);
        if (TextUtils.isEmpty(balls)){
//            holder.ball.setText("正在开彩中");
            TextView textView = new TextView(activity);
            textView.setTextSize(12);
            textView.setText("正在开彩中");
            holder.ll.addView(textView);
        }else {
            String[] strs=bean.data.get(position).prev.get(0).balls.split(" ");
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
                holder.ll.addView(textView);
            }
        }
        holder.number.setText("第 "+bean.data.get(position).prev.get(0).qishu+" 期");
        long timLong=Long.parseLong(bean.data.get(position).prev.get(0).opentime)*1000;
        Date date=new Date();
        date.setTime(timLong);
        holder.time.setText(sdf.format(date));
        return convertView;
    }

    public void setData(QuickBean bean){
        this.bean=bean;
        LogUtils.i("bean.data.size():设置bean"+bean.data.size());
        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView name;
        LinearLayout ll;
        TextView number;
        TextView time;
    }
}
