package com.rambo.iw.dbcommon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.support.ConnectionSource;
import com.rambo.iw.common.DataPathComm;
import com.rambo.iw.entity.BlueBall;
import com.rambo.iw.entity.LotteryResult;
import com.rambo.iw.entity.RedBall;
import com.rambo.iw.entity.TableEntity;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class AppDatabaseHelper extends DatabaseHelper {

    private static AppDatabaseHelper helper;
    private static String DATA_BASE_NAME = "data.db";
    //	private static int APP_VERSION = 1;
    private static int APP_VERSION = 2;

    /**
     * @param context
     */
    private AppDatabaseHelper(Context context) {
        super(context, DATA_BASE_NAME, APP_VERSION);
    }

    public static AppDatabaseHelper getInstance(Context context) {
        if (helper == null || !helper.isOpen()) {
            helper = new AppDatabaseHelper(context);
        }
        return helper;

    }

    @Override
    public Class<TableEntity>[] getAllTableEntitys() {
        Class[] tables = {
                LotteryResult.class,
                RedBall.class,
                BlueBall.class
                //TODO
        };
        return tables;
    }

    @Override
    public String getDataBaseFilePath() {
        //TODO 此处填写数据SQLite存储在SD卡路径（系统会自动生成数据库）
        String path = DataPathComm.DATABASE_PATH;
        path = path + "data.db";
        return path;
    }

    @Override
    public void updateEvent(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        if (newVer > oldVer) {//数据库升级
            switch (newVer) {
                case 0:

                    break;
                case 1:

                    break;
                case 2:
                    try {
//                        sqliteDatabase.execSQL("ALTER TABLE sys_attachment ADD COLUMN  remark VARCHAR(100)");
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();

                    }
                    try {
//                        sqliteDatabase.execSQL("ALTER TABLE table_item ADD COLUMN  detail_no VARCHAR(20)");
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();

                    }
                    try {
//                        TableUtils.createTableIfNotExists(connectionSource, Attachment.class);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();

                    }
                    try {
//                        TableUtils.createTableIfNotExists(connectionSource, TableItem.class);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }
        } else {//数据库降级

        }
    }
}
