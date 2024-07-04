package org.god.core;

import java.util.Map;

/**
 * SqlSession工厂对象，使用SqlSessionFactory可以获取会话对象
 * @author 饺子！
 * @since 2024/7/3 15:49
 **/
public class SqlSessionFactory {
    private TransactionManager transactionManager;
    private Map<String, GodMappedStatement> mappedStatementMap;

    public SqlSessionFactory(TransactionManager transactionManager, Map<String, GodMappedStatement> mappedStatementMap) {
        this.transactionManager = transactionManager;
        this.mappedStatementMap = mappedStatementMap;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Map<String, GodMappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, GodMappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }
}
