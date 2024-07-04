package org.god.core;

import java.sql.Connection;

/**
 * @author 饺子！
 * @since 2024/7/3 14:24
 **/
public interface TransactionManager {

    /**
     * 提交事务
     */
    void commit();

    /**
     * 回滚事务
     */
    void rollback();

    /**
     * 关闭事务
     */
    void close();

    /**
     * 开启连接
     */
    void openConnection();

    /**
     * 获取连接对象
     * @return 连接对象
     */
    Connection getConnection();
}
