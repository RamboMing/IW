package com.rambo.iw.dbcommon;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: lanming
 * @date: 2016-04-08
 */
public class BaseDaoImpl<T, ID> implements BaseDao<T, ID> {
    private DatabaseHelper databaseHelper;
    private Dao<T, ID> dao;
    private Context context;
    private Class<T> clazz;


    public BaseDaoImpl(Context context, Class<T> clazz, DatabaseHelper databaseHelper) {
        this.clazz = clazz;
        this.context = context;
        this.databaseHelper = databaseHelper;
        try {
            Dao<T, ID> actDao = this.databaseHelper.getDao(clazz);
//			this.dao = (Dao<T, ID>) ProxyFactory.getInstance().newProxy(actDao,clazz);
            this.dao = actDao;
//			System.out.println(this.dao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<T, ID> getDao() {
        return this.dao;
    }

    @Override
    public T queryForId(ID id) {
        if (id == null) {
            return null;
        }
        if (dao != null) {
            try {
                return dao.queryForId(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 按id集合查找
     *
     * @param ids
     * @return
     */
    @Override
    public List<T> queryForIds(List<ID> ids) {
        if (ids == null || ids.size() <= 0) {
            return null;
        }
        if (dao != null) {
            try {
                QueryBuilder<T, String> queryBuilder = (QueryBuilder<T, String>) getDao().queryBuilder();
                Where where = queryBuilder.where().eq("disable", false);
                where.and().in("id", ids.toArray());
                queryBuilder.orderBy("modifydate", false);
                PreparedQuery<T> preparedQuery = queryBuilder.prepare();
                List<T> list = getDao().query(preparedQuery);
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    ;

    @Override
    public List<T> queryForAll() {
        if (dao != null) {
            try {
                QueryBuilder<T, String> queryBuilder = (QueryBuilder<T, String>) getDao().queryBuilder();
                // TODO 需要加上是否有这个字段的判断语句
                queryBuilder.orderBy("modifydate", false);
                PreparedQuery<T> preparedQuery = queryBuilder.prepare();
                List<T> list = getDao().query(preparedQuery);
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public long queryAllCount() {
        try {
            QueryBuilder<T, String> mBuilder = (QueryBuilder<T, String>) getDao().queryBuilder();
            mBuilder.setCountOf(true);
            mBuilder.where().eq("disable", false);
            PreparedQuery<T> mPrepare = mBuilder.prepare();
            return getDao().countOf(mPrepare);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<T> queryForEq(String fieldName, Object value) {
        if (dao != null) {
            try {
                return dao.queryForEq(fieldName, value);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<T> queryForFieldValues(Map<String, Object> values) {
        if (values == null) {
            return null;
        }
        if (dao != null) {
            try {
                return dao.queryForFieldValues(values);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int save(T data) {
        if (data == null) {
            return 0;
        }
        if (getDao() != null) {
            try {
                return getDao().create(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int saveOrUpdate(T data) {
        if (data == null) {
            return 0;
        }
        if (getDao() != null) {
            try {
                T qd = getDao().queryForSameId(data);
                if (qd != null) {
                    return update(data);
                } else {
                    return save(data);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int update(T data) {
        if (data == null) {
            return 0;
        }
        if (getDao() != null) {
            try {
                return getDao().update(data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int save(List<T> data) {
        if (data == null) {
            return 0;
        }
        if (getDao() != null) {
            try {
                for (T t : data) {
                    getDao().create(t);
                }
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int saveOrUpdate(List<T> data) {
        if (data == null) {
            return 0;
        }
        if (getDao() != null) {
            try {
                for (T t : data) {
                    getDao().createOrUpdate(t);
                }
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int update(List<T> data) {
        if (data == null) {
            return 0;
        }
        if (getDao() != null) {
            try {
                for (T t : data) {
                    getDao().update(t);
                }
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int delete(T t) {
        if (t == null) {
            return 0;
        }
        if (getDao() != null) {
            try {
                getDao().delete(t);
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int delete(List<T> data) {
        if (data == null) {
            return 0;
        }
        if (getDao() != null) {
            try {
                for (T t : data) {
                    getDao().delete(t);
                }
                return 1;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int deleteById(ID id) {
        if (id == null) {
            return 0;
        }
        if (getDao() != null) {
            try {
                T data = queryForId(id);
                if (data != null) {
                    return getDao().deleteById(id);
                } else {
                    return 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 参数为空删除所有行（清空表）
     *
     * @param fieldName
     * @param value
     * @return
     * @date 2013-12-5下午3:35:14
     * @author hx
     */
    @Override
    public int deleteAll(String fieldName, Object value) {
        DeleteBuilder<T, ID> deleteBuilder = getDao().deleteBuilder();
        try {
            if (fieldName != null && !fieldName.trim().equals("")) {
                deleteBuilder.where().eq(fieldName, value);
            }
            int count = deleteBuilder.delete();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    ;

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    @Override
    public Transaction getTransaction() {
        Transaction t = new TransactionImpl(databaseHelper.getWritableDatabase());
        return t;
    }

    @Override
    public void clearObjectCache() {
        if (dao != null) {
            try {
                dao.closeableIterator().close();
                dao.clearObjectCache();
                dao = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public int deleteInAll(String fieldName, Object... value) {
        DeleteBuilder<T, ID> deleteBuilder = getDao().deleteBuilder();
        try {
            if (fieldName != null && !fieldName.trim().equals("")) {
                deleteBuilder.where().in(fieldName, value);
            }
            //PreparedDelete<T> pd=deleteBuilder.prepare();
            int count = deleteBuilder.delete();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public Class<T> getClazz() {
        return clazz;
    }

}
