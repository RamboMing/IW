package com.rambo.iw.dbcommon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.field.DataPersisterManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rambo.iw.entity.TableEntity;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public abstract class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    public final static String LOG_TAG = DatabaseHelper.class.getName();
    private String dataBaseFilePath = "";
    private Class<TableEntity>[] tables;    //所有数据表
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase writableDatabase;
    private SQLiteDatabase readableDatabase;

    /**
     * 获取数据库里所有表的实体类

     */
    public abstract Class<TableEntity>[] getAllTableEntitys();

    /**
     * 获取数据库保存的路径

     */
    public abstract String getDataBaseFilePath();

    private int newVersion;

    /**
     * 数据库操作类
     *
     * @param context 上下文
     */
    public DatabaseHelper(Context context, String dbName, int dbversion) {
        super(context, dbName, null, dbversion);
        this.dataBaseFilePath = getDataBaseFilePath();    //数据库保存路径
        this.tables = getAllTableEntitys();                //所有表实体
        newVersion = dbversion;

        //自定义日期的转换类
        DataPersisterManager.registerDataPersisters(MyDatePersister.getSingleton());

        createDatabaseIfNotExists();
    }

    /**
     * 如果数据库不存在，则新建
     */
    private void createDatabaseIfNotExists() {
        File file = new File(dataBaseFilePath);
        if (!file.exists() || !file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                Log.e(LOG_TAG, "新建数据库文件失败！");
                e.printStackTrace();
            }
            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
            getWritableDatabase().setVersion(DATABASE_VERSION);
            if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
                sqLiteDatabase.close();
            }
            onCreate(getWritableDatabase(), getConnectionSource());
            getWritableDatabase().setVersion(newVersion);
        } else {
            //如果文件已经存在，则不进行操作
            updateEvent(getWritableDatabase(), connectionSource, getWritableDatabase().getVersion(), newVersion);
            getWritableDatabase().setVersion(newVersion);
            return;
        }


    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        if (writableDatabase == null || !writableDatabase.isOpen()) {
            writableDatabase = SQLiteDatabase.openDatabase(dataBaseFilePath, null, SQLiteDatabase.OPEN_READWRITE);
        }
        return writableDatabase;
        //return SQLiteDatabase.openDatabase(dataBaseFilePath, null,SQLiteDatabase.OPEN_READWRITE);
    }


    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
//		if (readableDatabase==null||!writableDatabase.isOpen()) {
//			readableDatabase = SQLiteDatabase.openDatabase(dataBaseFilePath, null,SQLiteDatabase.OPEN_READONLY);
//		}
//		return readableDatabase;
        return SQLiteDatabase.openDatabase(dataBaseFilePath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        createTables();
    }

    /**
     * 创建数据库表

     */
    private void createTables() {
        if (tables == null || tables.length < 1) {
            Log.w(LOG_TAG, "没有任何数据表被创建，请实现TableEntity[] getAllTableEntitys()方法！");
        }
        try {
            Log.i(LOG_TAG, "onCreate");
            //建表
            if (tables != null && tables.length > 0) {
                for (int i = 0; i < tables.length; i++) {
                    Class<TableEntity> table = tables[i];
                    if (table != null) {
                        TableUtils.createTable(connectionSource, table);
                        Log.d(LOG_TAG, "创建数据表：" + table);
                    }
                }
            }
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 删除所有的表
     */
    private void dropTables() {
        // 建表
        if (tables != null && tables.length > 0) {
            for (int i = 0; i < tables.length; i++) {
                Class<TableEntity> table = tables[i];
                if (table != null) {
                    try {
                        TableUtils.dropTable(connectionSource, table, true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    Log.d(LOG_TAG, "创建数据表：" + table);
                }
            }
        }
    }

    /**
     * 删除所有的表
     */
    private void dropTables(Class[] classes) {
        // 建表
        if (tables != null && tables.length > 0) {
            for (int i = 0; i < tables.length; i++) {
                Class<TableEntity> table = tables[i];
                boolean isHas = isHas(classes, table);
                if (!isHas) {
                    if (table != null) {
                        try {
                            TableUtils.dropTable(connectionSource, table, true);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {
            //TableUtils.dropTable(connectionSource, ClickGroup.class, true);
            //onCreate(sqliteDatabase, connectionSource);
            updateEvent(sqliteDatabase, connectionSource, oldVer, newVer);
        } catch (Exception e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new " + newVer, e);
        }
    }

    /**
     * 更新动作
     */
    public abstract void updateEvent(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer);


    private boolean isHas(Class[] classes, Class clazz) {
        if ((classes == null || classes.length == 0) || clazz == null) {
            return false;
        }
        for (Class clz : classes) {
            if (clz == clazz)
                return true;
        }
        return false;
    }

    /**
     * 重置数据库
     */
    public boolean resetDataBase() {
        try {
            dropTables();
            createTables();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 重置数据库,排除某个表
     */
    public boolean resetDataBase(Class[] clazzs) {
        try {
            if (clazzs == null || clazzs.length == 0) {
                resetDataBase();
            } else {
                dropTables(clazzs);
                createTables();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

