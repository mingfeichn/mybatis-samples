package com.mingfei.mybatissamples.mapper;

import com.mingfei.mybatissamples.model.SysRole;
import com.mingfei.mybatissamples.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.Date;
import java.util.List;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectById() {
        // 获取SqlSession
        SqlSession sqlSession = getSqlSession();

        try {
            //获取UserMapper接口
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectById方法查询id=1的用户
            SysUser user = userMapper.selectById(1L);
            // user不为空
            Assert.assertNotNull(user);
            //userName = admin
            Assert.assertEquals("admin", user.getUserName());
        } finally {
            // 关闭sqlSession
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 调用selectAll方法查询所有用户
            List<SysUser> userList = userMapper.selectAll();
            // 结果不为空
            Assert.assertNotNull(userList);
            // 用户数量大于0个
            Assert.assertTrue(userList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserId() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserId(1L);
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@126.com");
            user.setUserInfo("test info");
            // 正常情况下读取一张图片保存到byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            // 将新建的对象插入到数据库中，返回执行SQL影响的行数
            int result = userMapper.insert(user);
            // 只插入一条数据
            Assert.assertEquals(1, result);
            // id为null,没有给id赋值，且没有配置返回写id的值
            Assert.assertNull(user.getId());
        } finally {
            // 默认sqlSessionFactory.openSession()是不自动提交的
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert2() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@126.com");
            user.setUserInfo("test info");
            // 正常情况下读取一张图片保存到byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            // 将新建的对象插入到数据库中，返回执行SQL影响的行数
            int result = userMapper.insert2(user);
            // 只插入一条数据
            Assert.assertEquals(1, result);
            // id为null,没有给id赋值，且没有配置返回写id的值
            Assert.assertNotNull(user.getId());
        } finally {
            // 默认sqlSessionFactory.openSession()是不自动提交的
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert3() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test1");
            user.setUserPassword("123456");
            user.setUserEmail("test@126.com");
            user.setUserInfo("test info");
            // 正常情况下读取一张图片保存到byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            // 将新建的对象插入到数据库中，返回执行SQL影响的行数
            int result = userMapper.insert3(user);
            // 只插入一条数据
            Assert.assertEquals(1, result);
            // id为null,没有给id赋值，且没有配置返回写id的值
            Assert.assertNotNull(user.getId());
        } finally {
            // 默认sqlSessionFactory.openSession()是不自动提交的
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateById() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 从数据库中查询一个user对象
            SysUser user = userMapper.selectById(1L);
            // 当前userName为admin
            Assert.assertEquals("admin", user.getUserName());
            // 修改用户名
            user.setUserName("admin_test");
            // 修改邮箱
            user.setUserEmail("admin_test@126.com");
            // 更新数据，返回SQL执行影响的行数
            int result = userMapper.updateById(user);

            // 只更新一条数据
            Assert.assertEquals(1, result);
            // 根据当前id查询修改后的数据
            user = userMapper.selectById(user.getId());
            // 修改后的名字为admin_test
            Assert.assertEquals("admin_test", user.getUserName());

        } finally {
            // 默认sqlSessionFactory.openSession()是不自动提交的
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteById() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 从数据库中查询一个user对象
            SysUser user1 = userMapper.selectById(1L);
            Assert.assertNotNull(user1);
            // 调用删除方法
            Assert.assertEquals(1, userMapper.deleteById(1L));
            Assert.assertNull(userMapper.selectById(1L));

            // 从数据库中查询一个user对象
            SysUser user2 = userMapper.selectById(1001L);
            Assert.assertNotNull(user2);
            // 调用删除方法
            Assert.assertEquals(1, userMapper.deleteById(user2));
            Assert.assertNull(userMapper.selectById(1001L));

        } finally {
            // 默认sqlSessionFactory.openSession()是不自动提交的
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled(1L, 1);
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled2() {
        SqlSession sqlSession = getSqlSession();

        try {
            SysUser user = new SysUser();
            user.setId(1L);

            SysRole role = new SysRole();
            role.setEnabled(1);

            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<SysRole> roleList = userMapper.selectRolesByUserIdAndRoleEnabled2(user, role);
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testMyMapperProxy() {
        // 获取SqlSession
        SqlSession sqlSession = getSqlSession();
        // 获取UserMapper接口
        MyMapperProxy userMapperProxy = new MyMapperProxy(UserMapper.class, sqlSession);
        UserMapper userMapper = (UserMapper) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{UserMapper.class},
                userMapperProxy);
        // 调用selectAll方法
        List<SysUser> userList = userMapper.selectAll();
        Assert.assertNotNull(userList);
    }
}
