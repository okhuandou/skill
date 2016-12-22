import com.mybatis.mapper.LongTimeMapper;
import com.mybatis.model.LongTimeExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by lk on 2016/12/22.
 */
public class LongTimeMapperTest {
    private static SqlSessionFactory sessionFactory;

  /*  public static Object getMapper(Class cls){
        MapperProxy
        return MapperProxy.newMapperProxy(cls, getSqlSession());
    }*/

    public static void main(String[] args) {
        String resource = "spring-mybatis-test.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSession sqlSession = sessionFactory.openSession();
        LongTimeMapper mapper = sqlSession.getMapper(LongTimeMapper.class);

        LongTimeExample ex = new LongTimeExample();

        ex.setLimitStart(1);
        ex.setLimitEnd(10);
        // set count,up to you
        mapper.countByExample(ex);
        int row = mapper.selectByExample(ex).size();
        System.out.println("============row:" + row + "================");
    }


}