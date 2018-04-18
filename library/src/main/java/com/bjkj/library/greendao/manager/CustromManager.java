package com.bjkj.library.greendao.manager;

import android.content.Context;

import com.bjkj.library.greendao.BaseDao;
import com.bjkj.library.greendao.bean.CustromBean;
import com.bjkj.library.greendao.dao.CustromBeanDao;

/**
 * Created by liyou on 2018/4/4.
 */

public class CustromManager extends BaseDao<CustromBean>{
    public CustromManager(Context context) {
        super(context);
    }

    /**
     * 通过ID查询对象
     * @param id
     * @return
     */
    public CustromBean queryById(String id){
        CustromBeanDao dao = daoSession.getCustromBeanDao();
        CustromBean custromBean=dao.queryBuilder().where(CustromBeanDao.Properties.UserId.eq(id)).unique();
        return custromBean;
    }
}
