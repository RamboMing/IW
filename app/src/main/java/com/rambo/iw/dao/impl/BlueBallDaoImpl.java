package com.rambo.iw.dao.impl;

import android.content.Context;

import com.rambo.iw.dao.BlueBallDao;
import com.rambo.iw.dbcommon.AppDatabaseHelper;
import com.rambo.iw.dbcommon.BaseDaoImpl;
import com.rambo.iw.entity.BlueBall;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class BlueBallDaoImpl extends BaseDaoImpl<BlueBall, String> implements BlueBallDao {
    public BlueBallDaoImpl(Context context) {
        super(context, BlueBall.class, AppDatabaseHelper.getInstance(context));
    }
}
