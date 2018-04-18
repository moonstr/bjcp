package com.bjke.project1.bean;

import java.util.ArrayList;

/**
 * Created by liyou on 2018/3/28.
 */

public class LotteryBean {
    public String rows;
    public String code;
    public String info;
    public ArrayList<LotteryData> data;
    public class LotteryData{
        public String expect;
        public String opencode;
        public String opentime;
        public String opentimestamp;
    }
}
