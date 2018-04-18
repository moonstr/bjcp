package com.bjke.project1.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjke.project1.R;

/**
 * Created by liyou on 2018/4/14.
 */

public class TitleView extends LinearLayout{
    private View rootView;
    private ImageView mBack;
    private TextView mTitle;
    String title;
     int textSize;
    boolean showLeft;
    public TitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.viewTitle);
        title =  ta.getString(R.styleable.viewTitle_mTitle);
        showLeft = ta.getBoolean(R.styleable.viewTitle_mShowLeft,true);
        textSize = ta.getInteger(R.styleable.viewTitle_mTitleSize,context.getResources().getDimensionPixelOffset(R.dimen.font_10));
        initView();
        initData();
        ta.recycle();
    }

    private void initView(){
         rootView = View.inflate(getContext(), R.layout.view_title,this);
        mBack = rootView.findViewById(R.id.back);
        mTitle = rootView.findViewById(R.id.center);
        setLeftOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }

    private void initData(){
        mBack.setVisibility(showLeft?VISIBLE:GONE);
        mTitle.setText(TextUtils.isEmpty(title)?"":title);
        mTitle.setTextSize(textSize);
    }


    private void setLeftOnClickListener(OnClickListener listener){
        mBack.setOnClickListener(listener);

    }
    public void setTitle(String title){
        mTitle.setText(title);
    }
}
