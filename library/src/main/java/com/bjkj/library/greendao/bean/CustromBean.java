package com.bjkj.library.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liyou on 2018/4/4.
 */
@Entity(nameInDb = "t_custrom")
public class CustromBean {
    /**
     * 用户ID
     */
    @Property(nameInDb = "user_id")
    private String userId;

    /**
     * 设备id（可选）
     */
    @Property(nameInDb = "user_name")
    private String userName;

    @Generated(hash = 925937782)
    public CustromBean(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Generated(hash = 1124837273)
    public CustromBean() {
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "CustromBean{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
