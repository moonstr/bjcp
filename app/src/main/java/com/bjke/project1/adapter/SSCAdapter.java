package com.bjke.project1.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.bean.SSCBean;
import com.bjke.project1.fragment.LotteryFragment;
import com.bjkj.library.utils.LogUtils;

/**
 * Created by liyou on 2018/4/10.
 */

public class SSCAdapter extends BaseAdapter{
    private SSCBean bean;
    private Activity activity;
    public SSCAdapter(Activity activity,SSCBean bean){
        this.activity=activity;
        this.bean=bean;
    }
    @Override
    public int getCount() {
        if (bean==null)return 0;

         if (bean.data==null||bean.data.size()==0){
             return 0;
         }else {
             LogUtils.i("bean.data.size():"+bean.data.size());
             return bean.data.size();
         }

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
        LogUtils.i("bean.data.size()getView:"+bean.data.size());
        if (convertView==null){
            holder = new ViewHolder();
            convertView= LayoutInflater.from(activity).inflate(R.layout.item_main,null);
            holder.name=convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.name.setText(bean.data.get(position).gameid);
        return convertView;
    }

    public void setData(SSCBean bean){
        this.bean=bean;
        LogUtils.i("bean.data.size():设置bean"+bean.data.size());
      notifyDataSetChanged();
    }

    class ViewHolder{
        TextView name;
    }
}
