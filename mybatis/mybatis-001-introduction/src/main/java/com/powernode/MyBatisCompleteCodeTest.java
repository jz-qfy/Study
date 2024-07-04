package com.powernode;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author 饺子！
 * @since 2024/6/24 16:47
 **/
public class MyBatisCompleteCodeTest {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = null;
        try {
            //1. 创建sqlSessionFactoryBuilder对象
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            //2. 创建sqlSessionFactory对象
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
            //3. 创建SqlSession对象
            sqlSession = sqlSessionFactory.openSession();
            //4. 执行sql
            int count = sqlSession.insert("insertCar");
            System.out.println("更新了几条记录： " + count);
            //5. 提交
            sqlSession.commit();
        } catch (Exception e) {
            //回滚
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //6. 关闭
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
