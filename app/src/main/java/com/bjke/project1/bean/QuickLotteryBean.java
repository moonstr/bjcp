package com.bjke.project1.bean;

import java.util.ArrayList;

/**
 * Created by liyou on 2018/4/11.
 */

public class QuickLotteryBean {

    public String param;
    public String msg;
    public ArrayList<QuickLotteryData> data;
    public class QuickLotteryData{
        public String balls;
        public String opentime;
        public String qishu;
    }
}
