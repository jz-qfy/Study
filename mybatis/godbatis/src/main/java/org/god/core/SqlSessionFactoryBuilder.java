package org.god.core;

import org.god.core.SqlSessionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 饺子！
 * @since 2024/7/3 14:17
 **/
public class SqlSessionFactoryBuilder {

    /**
     * 创建构建器对象
     */
    public SqlSessionFactoryBuilder(){}

    /**
     * 获取SqlSessionFactory对象
     * 该方法的主要功能是：读取godbatis核心配置文件，并构建SqlSessionFactory对象
     * @param inputStream 指向核心配置文件的输入流
     * @return SqlSessionFactory对象
     */
    public SqlSessionFactory build(InputStream inputStream) throws DocumentException {
        //解析配置文件 创建数据源对象
        //解析配置文件 创建事务管理器对象
        //解析配置文件 获取所有的SQL映射对象
        //将以上信息封装到SqlSessionFactory对象中
        //返回
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        Element enviromentsElt = (Element)document.selectSingleNode("/configuration/environments");
        String defaultEnv = enviromentsElt.attributeValue("default");
        Element enviromentElt = (Element)document.selectSingleNode("/configuration/environments/environment[@id='" + defaultEnv + "']");
        //解析配置文件 创建数据源对象
        Element dataSourceElt = enviromentElt.element("dataSource");
        DataSource dataSource = getDataSource(dataSourceElt);
        //解析配置文件 创建事务管理器对象
        Element transactionManagerElt = enviromentElt.element("transactionManager");
        TransactionManager transactionManager = getTransactionManager(transactionManagerElt, dataSource);
        //解析配置文件 获取所有的SQL映射对象
        Element mappers = enviromentElt.element("mappers");
        Map<String, GodMappedStatement> mappedStatements = getMappedStatements(mappers);
        //将以上信息封装到SqlSessionFactory对象中
        org.god.core.SqlSessionFactory sqlSessionFactory = new org.god.core.SqlSessionFactory(transactionManager, mappedStatements);
        return sqlSessionFactory;
    }

    private Map<String, GodMappedStatement> getMappedStatements(Element mappers) {
        Map<String, GodMappedStatement> mappedStatements = new HashMap<>();
        mappers.elements().forEach(mapperElt -> {
            try {
                String resource = mapperElt.attributeValue("resource");
                SAXReader saxReader = new SAXReader();
                Document document = saxReader.read(Resources.getResourcesAsStream(resource));
                Element mapper = (Element) document.selectSingleNode("/mapper");
                String namespace = mapper.attributeValue("namespace");

                mapper.elements().forEach(sqlMapper -> {
                    String sqlId = sqlMapper.attributeValue("id");
                    String sql = sqlMapper.getTextTrim();
                    String parameterType = sqlMapper.attributeValue("parameterType");
                    String resultType = sqlMapper.attributeValue("resultType");
                    String sqlType = sqlMapper.getName().toLowerCase();
                    // 封装GodMappedStatement对象
                    GodMappedStatement godMappedStatement = new GodMappedStatement(sqlId, resultType, sql, parameterType, sqlType);
                    mappedStatements.put(namespace + "." + sqlId, godMappedStatement);
                });

            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        });
        return mappedStatements;
    }

    private TransactionManager getTransactionManager(Element transactionManagerElt, DataSource dataSource) {
        String type = transactionManagerElt.attributeValue("type").toUpperCase();
        TransactionManager transactionManager = null;
        if("JDBC".equals(type)){
            transactionManager = new GodJDBCTransaction(dataSource, false);
        } else if ("MANAGED".equals(type)) {

        }
        return transactionManager;
    }

    private DataSource getDataSource(Element dataSourceElt) {
        //获取所有数据源的属性配置
        HashMap<String, String> dataSourceMap = new HashMap<>();
        dataSourceElt.elements().forEach(mapperElt -> {
            dataSourceMap.put(mapperElt.attributeValue("name"), mapperElt.attributeValue("value"));
        });

        String dataSourceType = dataSourceElt.attributeValue("type").toUpperCase();
        DataSource dataSource = null;
        if("POOLED".equals(dataSourceType)){
            dataSource = new GodUNPOOLEDDataSource(dataSourceMap.get("driver"), dataSourceMap.get("url"), dataSourceMap.get("username"), dataSourceMap.get("password"));
        } else if ("JDNI".equals(dataSourceType)) {

        }
        return dataSource;
    }
}
