package com.mingfei.mybatissamples.mapper;

import com.mingfei.mybatissamples.model.Country;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class CountryMapperTest extends BaseMapperTest {

    @Test
    public void testSelectAll() {
        //通过SqlSessionFactory工厂对象获取一个SqlSession
        SqlSession sqlSession = getSqlSession();
        try {
            //通过SqlSession的selectList方法查找到CountryMapper.xml中id="selectAll"方法，执行SQL查询
            List<Country> countryList = sqlSession
                    .selectList("com.mingfei.mybatissamples.mapper.CountryMapper.selectAll");
            printCountryList(countryList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭数据库连接
            sqlSession.close();
        }
    }

    private void printCountryList(List<Country> countryList) {
        for (Country country : countryList) {
            System.out.printf("%-4d%4s%4s\n", country.getId(), country.getCountryname(), country.getCountrycode());
        }
    }
}
