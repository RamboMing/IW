package com.rambo.iw.dao.impl;

import android.content.Context;

import com.rambo.iw.dao.LotteryResultDao;
import com.rambo.iw.dbcommon.AppDatabaseHelper;
import com.rambo.iw.dbcommon.BaseDaoImpl;
import com.rambo.iw.entity.LotteryResult;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class LotteryResultDaoImpl extends BaseDaoImpl<LotteryResult, String> implements LotteryResultDao {
    public LotteryResultDaoImpl(Context context) {
        super(context, LotteryResult.class, AppDatabaseHelper.getInstance(context));
    }
}
