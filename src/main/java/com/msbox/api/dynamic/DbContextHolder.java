package com.msbox.api.dynamic;

/**
 * Created by root on 15-3-31.
 */
public class DbContextHolder {
    //线程安全的ThreadLocal
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }

    public static String getDbType() {
        return ((String)contextHolder.get());
    }
    public static void clearDbType() {
        contextHolder.remove();
    }
}
