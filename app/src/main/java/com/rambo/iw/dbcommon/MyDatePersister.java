package com.rambo.iw.dbcommon;

import com.j256.ormlite.field.FieldType;
import com.j256.ormlite.field.SqlType;
import com.j256.ormlite.field.types.BaseDateType;
import com.j256.ormlite.misc.SqlExceptionUtil;
import com.j256.ormlite.support.DatabaseResults;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @Description: 自定义的日期解析类
 * <p>
 * 框架自身默认的日期格式为 yyyy-MM-dd HH:mm:ss.SSS ，
 * 为解决从其它数据库导入数据的时候抛异常的问题，
 * 将日期默认格式修改为yyyy-MM-dd HH:mm:ss
 * @author: lanming
 * @date: 2016-04-08
 */
public class MyDatePersister extends BaseDateType {

    protected static final DateStringFormatConfig dateFormatConfig = new DateStringFormatConfig("yyyy-MM-dd HH:mm:ss");

    public static int DEFAULT_WIDTH = 50;

    private static final MyDatePersister singleTon = new MyDatePersister();

    public static DateStringFormatConfig getDateformatconfig() {
        return dateFormatConfig;
    }

    public static MyDatePersister getSingleton() {
        return singleTon;
    }

    private MyDatePersister() {
        super(SqlType.STRING, new Class<?>[0]);
    }

    /**
     * Here for others to subclass.
     */
    protected MyDatePersister(SqlType sqlType, Class<?>[] classes) {
        super(sqlType, classes);
    }

    @Override
    public Object parseDefaultString(FieldType fieldType, String defaultStr) throws SQLException {
        DateStringFormatConfig formatConfig = convertDateStringConfig(fieldType, dateFormatConfig);
        try {
            // we parse to make sure it works and then format it again
            return normalizeDateString(formatConfig, defaultStr);
        } catch (ParseException e) {
            throw SqlExceptionUtil.create("Problems with field " + fieldType + " parsing default date-string '"
                    + defaultStr + "' using '" + formatConfig + "'", e);
        }
    }

    @Override
    public Object resultToSqlArg(FieldType fieldType, DatabaseResults results, int columnPos) throws SQLException {
        return results.getString(columnPos);
    }

    @Override
    public Object sqlArgToJava(FieldType fieldType, Object sqlArg, int columnPos) throws SQLException {
        String value = (String) sqlArg;
        DateStringFormatConfig formatConfig = convertDateStringConfig(fieldType, dateFormatConfig);
        try {
            return parseDateString(formatConfig, value);
        } catch (ParseException e) {
            throw SqlExceptionUtil.create("Problems with column " + columnPos + " parsing date-string '" + value
                    + "' using '" + formatConfig + "'", e);
        }
    }

    @Override
    public Object javaToSqlArg(FieldType fieldType, Object obj) {
        DateFormat dateFormat = convertDateStringConfig(fieldType, dateFormatConfig).getDateFormat();
        return dateFormat.format((Date) obj);
    }

    @Override
    public Object makeConfigObject(FieldType fieldType) {
        String format = fieldType.getFormat();
        if (format == null) {
            return dateFormatConfig;
        } else {
            return new DateStringFormatConfig(format);
        }
    }

    @Override
    public int getDefaultWidth() {
        return DEFAULT_WIDTH;
    }

    @Override
    public Object resultStringToJava(FieldType fieldType, String stringValue, int columnPos) throws SQLException {
        return sqlArgToJava(fieldType, stringValue, columnPos);
    }

    public Class<?> getPrimaryClass() {
        return byte[].class;
    }
}
