package com.github.yooryan.primarysecondary.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author linyunrui
 */
public class DBTypeHolder {
    private static final Logger log = LoggerFactory.getLogger(DBTypeHolder.class);

    private static final ThreadLocal<DBType> contextHolder = new ThreadLocal<>();
    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBType dbType){
        contextHolder.set(dbType);
    }

    public static DBType get() {
        return contextHolder.get();
    }

    public static void remove(){
        contextHolder.remove();
    }

    public static void primary(){
        set(DBType.PRIMARY);
        log.info("DataBase Type Primary");
    }

    public static void secondary(){
        DBType rotation = rotation();
        set(rotation);
        log.info("DataBase Type {}",rotation);
    }

    private static DBType rotation(){
        int index = counter.getAndIncrement() % 2;
        if (counter.get() > 9999){
            counter.set(-1);
        }
        return index == 0 ? DBType.SECONDARY : DBType.SECONDARY2;
    }

}
