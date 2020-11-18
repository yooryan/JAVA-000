package com.github.yooryan.spring.jdbc.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 结果集处理
 * @author linyunrui
 */
public interface ResultHandler<T> {

    T handler(ResultSet set) throws Exception;
}
