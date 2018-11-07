package com.mingfei.mybatissamples.mapper;

import com.mingfei.mybatissamples.model.Country;
import com.mingfei.mybatissamples.model.CountryExample;
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

    @Test
    public void testExample() {
        SqlSession sqlSession = getSqlSession();

        try {
            // 获取Mapper接口
            // CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            // 创建Example对象
            CountryExample example = new CountryExample();
            // 设置排序规则
            example.setOrderByClause("id desc, countryname asc");
            // 设置是否distinct去重
            example.setDistinct(true);
            // 创建条件
            CountryExample.Criteria criteria = example.createCriteria();
            // id >= 1
            criteria.andIdGreaterThanOrEqualTo(1);
            // id < 4
            criteria.andIdLessThan(4);
            // countrycode like '%U%'
            // 最容易出错的地方，注意like必须自己写上通配符的位置
            criteria.andCountrycodeLike("%U%");
            // or的情况
            CountryExample.Criteria or = example.or();
            // countryname = 中国
            or.andCountrynameEqualTo("中国");
            // 执行查询
            List<Country> countryList = sqlSession.selectList("com.mingfei.mybatissamples.mapper.CountryMapper.selectByExample");
            printCountryList(countryList);
        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByExampleSelective(){
        SqlSession sqlSession = getSqlSession();

        try {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            CountryExample example = new CountryExample();
            CountryExample.Criteria criteria = example.createCriteria();
            criteria.andIdGreaterThan(2);
            Country country = new Country();
            country.setCountryname("China");
            countryMapper.updateByExampleSelective(country, example);
            printCountryList(countryMapper.selectByExample(example));
        } finally {
            sqlSession.close();
        }
    }
}
