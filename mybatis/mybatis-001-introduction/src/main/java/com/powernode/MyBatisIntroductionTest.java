package com.powernode;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author 饺子！
 * @since 2024/6/24 16:07
 **/
public class MyBatisIntroductionTest {
    public static void main(String[] args) {
        //1. 创建SqlSessionFactoryBuilder对象
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //2. 创建SqlSessionFactory对象
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //3. 创建SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4. 执行sql
        int count = sqlSession.insert("insertCar");
        System.out.println("插入几条数据" + count);
        //5. 提交
        sqlSession.commit();
        //6. 关闭资源
        sqlSession.close();
    }
}
