package com.rambo.iw.common;

import android.os.Environment;

import java.io.File;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class DataPathComm {
    // 根目录
    public final static String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "iw" + File.separator;

    // 数据库目录
    public final static String DATABASE_PATH = ROOT_PATH + "data" + File.separator;

    static {
        File databasePath = new File(DATABASE_PATH);
        if (!databasePath.exists() || !databasePath.isDirectory()) {
            databasePath.mkdirs();
        }
    }
}
