package com.mingfei.mybatissamples.mapper;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

import java.io.IOException;
import java.io.Reader;

/**
 * 基础测试类
 */
public class BaseMapperTest {
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

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
