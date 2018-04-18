package com.bjke.project1.bean;

import java.util.ArrayList;

/**
 * Created by liyou on 2018/4/11.
 */

public class QuickBean {
    public String msg;
    public String param;
    public ArrayList<QuickData> data;
    public class QuickData{
        public String game_id;
        public String game_name;
        public String js_tag;
        public String tag;
        public String enable;
        public String speed;
        public String lock_time;
        public String lock_end_time;
        public ArrayList<QuickNext> next;
        public ArrayList<QuickPrev> prev;
        public class QuickNext{
                public String jiezhitime;
                public String qishu;
                public String balls;
                public String end_time;
                public String server_time;
        }

        public class QuickPrev{
            public String balls;
            public String qishu;
            public String opentime;
        }

    }
}
