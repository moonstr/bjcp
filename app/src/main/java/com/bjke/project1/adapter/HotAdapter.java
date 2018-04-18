package com.bjke.project1.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bjke.project1.R;
import com.bjke.project1.bean.HotBean;
import com.bumptech.glide.Glide;

/**
 * Created by liyou on 2018/3/29.
 */

public class HotAdapter extends BaseAdapter {
    HotBean bean;
    Activity activity;

    public HotAdapter(HotBean bean, Activity activity) {
        this.bean = bean;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        if (bean == null) return 0;
        if (bean.dataList == null) {
            return 0;
        } else {
            return bean.dataList.size();
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
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (contentView == null) {
            holder = new ViewHolder();
            contentView = LayoutInflater.from(activity).inflate(R.layout.item_hot, null);
            holder.mImage = contentView.findViewById(R.id.image);
            holder.mTitle = contentView.findViewById(R.id.title);
            holder.mDetail = contentView.findViewById(R.id.detail);
            holder.mDate = contentView.findViewById(R.id.date);
            contentView.setTag(holder);
        } else {
            holder = (ViewHolder) contentView.getTag();
        }
        Glide.with(activity).load(bean.dataList.get(position).logoFile).placeholder(R.mipmap.logo).into(holder.mImage);
        holder.mTitle.setText(bean.dataList.get(position).title);
        holder.mDetail.setText(bean.dataList.get(position).summary);
        holder.mDate.setText(bean.dataList.get(position).publishDate);
//        holder.mImage.setbean.dataList.get(position).i
        return contentView;
    }

    public void setData(HotBean bean){
        this.bean=bean;
        notifyDataSetChanged();
    }

    class ViewHolder {
        ImageView mImage;
        TextView mTitle;
        TextView mDetail;
        TextView mDate;
    }
}
