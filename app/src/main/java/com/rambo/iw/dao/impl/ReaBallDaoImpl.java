package com.rambo.iw.dao.impl;

import android.content.Context;

import com.rambo.iw.dao.RedBallDao;
import com.rambo.iw.dbcommon.AppDatabaseHelper;
import com.rambo.iw.dbcommon.BaseDaoImpl;
import com.rambo.iw.entity.RedBall;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class ReaBallDaoImpl extends BaseDaoImpl<RedBall,String> implements RedBallDao {
    public ReaBallDaoImpl(Context context) {
        super(context, RedBall.class, AppDatabaseHelper.getInstance(context));
    }
}
