package com.bjke.project1.bean;

import java.util.ArrayList;

/**
 * Created by liyou on 2018/3/29.
 */

public class HotBean {
    public ArrayList<HotData> dataList;

    public class HotData {
        public String logoFile;
        public String publishDate;
        public String summary;
        public String title;
        public String url;
    }
}
