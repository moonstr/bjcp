package com.bjkj.library.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bjkj.library.R;

/**
 * Created by liyou on 2018/3/28.
 */

public class ComProgressDialog extends Dialog {
    public ComProgressDialog(Context context) {
        super(context);
    }

    public ComProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    public void setMessage(String msg){
        TextView textView=findViewById(R.id.progressTextId);
        if (textView!=null){
            textView.setText(msg);
        }
    }
    public static class Builder{
        private Context context;
        private String progressText;
        private boolean cancelable;
        private int bgDrawable = -1;
        private LinearLayout bgLayout;
        private boolean hasBg = true;
        public Builder(Context context){
            this.context = context;
        }
        public Builder setMessage(String message){
            this.progressText = message;
            return this;
        }

        public Builder setBgDrawable(int res){
            this.bgDrawable = res;
            return this;
        }

        public Builder setCancelable(boolean cancelable){
            this.cancelable = cancelable;
            return this;
        }

        public Builder setHasBg(boolean hasBg){
            this.hasBg = hasBg;
            return this;
        }

        public ComProgressDialog create(){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View layout = layoutInflater.inflate(R.layout.progress_dialog_layout,null);
            ComProgressDialog progressDialog = new ComProgressDialog(context,R.style.LoadingDialog);
            progressDialog.addContentView(layout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            progressDialog.setCancelable(cancelable);
            bgLayout = layout.findViewById(R.id.progressLayoutID);
            if(!TextUtils.isEmpty(progressText)){
                ((TextView)layout.findViewById(R.id.progressTextId)).setVisibility(View.VISIBLE);
                ((TextView)layout.findViewById(R.id.progressTextId)).setText(progressText);
            }else {
                ((TextView)layout.findViewById(R.id.progressTextId)).setVisibility(View.GONE);
            }
            if(bgDrawable != -1){
                bgLayout.setBackgroundResource(bgDrawable);
            }
            if(!hasBg){
                progressDialog.getWindow().setDimAmount(0f);//去除遮罩
            }

            progressDialog.setContentView(layout);
            return progressDialog;
        }
    }


}
