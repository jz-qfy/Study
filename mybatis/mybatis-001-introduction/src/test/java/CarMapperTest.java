import com.powernode.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author 饺子！
 * @since 2024/6/24 17:03
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
    public void testUpdate() {}
}
