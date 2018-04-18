package com.bjke.project1.bean;

import java.util.ArrayList;

/**
 * Created by liyou on 2018/4/10.
 */

public class SSCBean {
  public String msg;
  public String param;
  public ArrayList<SSCData> data;
  public class SSCData{
    public String win;
    public String gameid;
    public String head_icon;
    public String username;
  }
}

