package com.rambo.iw.dbcommon;

import android.database.sqlite.SQLiteDatabase;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class TransactionImpl implements Transaction {
    private SQLiteDatabase sqLiteDatabase;

    public TransactionImpl(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void beginTransaction() {
        this.sqLiteDatabase.beginTransaction();
    }

    @Override
    public void commit() {
        this.sqLiteDatabase.setTransactionSuccessful();
    }

    @Override
    public void endTransaction() {
        this.sqLiteDatabase.endTransaction();
    }
}
