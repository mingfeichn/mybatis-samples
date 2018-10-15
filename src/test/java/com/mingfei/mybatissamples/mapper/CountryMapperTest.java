package com.mingfei.mybatissamples.mapper;

import com.mingfei.mybatissamples.model.Country;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CountryMapperTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            //通过Resources工具将读取mybatis配置
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder建造类使用配置创建sqlSessionFactory工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //关闭Reader
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectAll() {
        //通过SqlSessionFactory工厂对象获取一个SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            //通过SqlSession的selectList方法查找到CountryMapper.xml中id="selectAll"方法，执行SQL查询
            List<Country> countryList = sqlSession.selectList("selectAll");
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
