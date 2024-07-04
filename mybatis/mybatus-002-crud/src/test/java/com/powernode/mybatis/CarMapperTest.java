package com.powernode.mybatis;

import com.powernode.mybatis.pojo.Car;
import com.powernode.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author 饺子！
 * @since 2024/6/25 14:10
 **/
public class CarMapperTest {

    @Test
    public void testInsert() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        //4. 执行sql
        HashMap<String, Object> map = new HashMap<>();
        map.put("carNum", "103");
        map.put("brand", "奔驰E300L");
        map.put("guidePrice", 50.3);
        map.put("produceTime", "2020-10-01");
        map.put("carType", "燃油车");
        int count = sqlSession.insert("insertCar", map);
        System.out.println("更新了几条记录： " + count);
        //5. 提交
        sqlSession.close();
    }

    @Test
    public void testInsertByPOJO(){
        Car car = new Car();
        car.setCarNum("103");
        car.setBrand("奔驰C200");
        car.setGuidePrice(33.23);
        car.setProduceTime("2020-10-11");
        car.setCarType("燃油车");
        // 获取SqlSession对象
        SqlSession sqlSession = SqlSessionUtil.openSession();
        // 执行SQL，传数据
        int count = sqlSession.insert("insertCarByPOJO", car);
        System.out.println("插入了几条记录" + count);
    }

    @Test
    public void testDeleteByCarNum(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.delete("deleteByCarNum", "104");
        System.out.println("删除了几条记录：" + count);
    }

    @Test
    public void testUpdateByPOJO(){
        Car car = new Car();
        car.setId(3L);
        car.setCarNum("104");
        car.setBrand("比亚迪汉");
        car.setGuidePrice(30.23);
        car.setProduceTime("2018-09-10");
        car.setCarType("电车");
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.update("updateCarByPOJO", car);
        System.out.println("更新了几条记录： " + count);
    }

    @Test
    public void testSelectCarById(){
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = sqlSession.selectOne("selectCarById", 1);
        System.out.println(car);
    }
}
