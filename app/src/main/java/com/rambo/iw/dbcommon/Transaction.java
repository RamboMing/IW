package com.rambo.iw.dbcommon;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public interface Transaction {

    public void beginTransaction();

    public void commit();

    public void endTransaction();
}
