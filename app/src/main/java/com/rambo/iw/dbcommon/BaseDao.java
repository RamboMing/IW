package com.rambo.iw.dbcommon;

import java.util.List;
import java.util.Map;

public interface BaseDao<T, ID> {

    public Transaction getTransaction();


    public T queryForId(ID id);

    public List<T> queryForIds(List<ID> ids);


    public List<T> queryForAll();

    public long queryAllCount();


    public List<T> queryForEq(String fieldName, Object value);


    public List<T> queryForFieldValues(Map<String, Object> values);

    public int save(T data);

    public int saveOrUpdate(T data);

    public int update(T data);


    public int delete(T t);

    public int delete(List<T> data);


    public int deleteById(ID id);


    public int saveOrUpdate(List<T> data);

    public int update(List<T> data);

    public int save(List<T> data);


    public int deleteAll(String fieldName, Object value);

    public int deleteInAll(String fieldName, Object... value);


    public void clearObjectCache();
}
