package com.mingfei.mybatissamples.mapper;

import com.mingfei.mybatissamples.model.SysRole;
import com.mingfei.mybatissamples.model.SysUser;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.util.*;

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

    @Test
    public void testSelectByUser() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            // 只查询用户名时
            SysUser user = new SysUser();
            user.setUserName("ad");
            List<SysUser> userList = userMapper.selectByUser(user);
            Assert.assertTrue(userList.size() > 0);

            // 只查询用户邮箱时
            user = new SysUser();
            user.setUserEmail("test@126.com");
            userList = userMapper.selectByUser(user);
            Assert.assertTrue(userList.size() > 0);

            // 同时查询用户名和邮箱时
            user = new SysUser();
            user.setUserName("ad");
            user.setUserEmail("test@126.com");
            userList = userMapper.selectByUser(user);
            Assert.assertTrue(userList.size() == 0);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUserIf() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ad");
            List<SysUser> result = userMapper.selectByUserIf(user);
            Assert.assertEquals("admin", result.get(0).getUserName());

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByIdSelective() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setId(1L);
            user.setUserEmail("admin2@126.com");
            int result = userMapper.updateByIdSelective(user);
            // 只更新一条记录
            Assert.assertEquals(1, result);

            user = userMapper.selectById(1L);
            // 名字不变，邮箱已更新
            Assert.assertEquals("admin", user.getUserName());
            Assert.assertEquals("admin2@126.com", user.getUserEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testInsert4() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test-selective");
            user.setUserPassword("123123");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setUserInfo("test if");
            user.setCreateTime(new Date());
            int result = userMapper.insert4(user);

            user = userMapper.selectById(user.getId());
            Assert.assertEquals(1, result);
            Assert.assertEquals("test-selective", user.getUserName());
            Assert.assertEquals("test@126.com", user.getUserEmail());
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdOrUserName() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            // 优先使用id
            SysUser user = new SysUser();
            user.setId(1L);
            user.setUserName("admin");
            SysUser result = userMapper.selectByIdOrUserName(user);
            Assert.assertNotNull(result);

            // 没有id
            user.setId(null);
            result = userMapper.selectByIdOrUserName(user);
            Assert.assertNotNull(result);

            // 没有id和userName
            user.setUserName(null);
            result = userMapper.selectByIdOrUserName(user);
            Assert.assertNull(result);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByUser2() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("ad");
            List<SysUser> result = userMapper.selectByUser2(user);
            Assert.assertNotNull(result);
            Assert.assertTrue(result.size() == 1);

            user.setUserEmail("mybatis@126.com");
            result = userMapper.selectByUser2(user);
            Assert.assertTrue(result.size() == 0);

            user.setUserName(null);
            user.setUserEmail(null);
            result = userMapper.selectByUser2(user);
            Assert.assertTrue(result.size() > 1);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdList2() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<>();
            idList.add(1L);
            idList.add(2L);
            idList.add(3L);
            List<SysUser> userList = userMapper.selectByIdList2(idList);
            Assert.assertTrue(userList.size() == 3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdArray() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Long[] idArray = new Long[]{1L, 2L, 3L};
            List<SysUser> userList = userMapper.selectByIdArray(idArray);
            Assert.assertTrue(userList.size() == 3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdMap() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<>();
            idList.add(1L);
            idList.add(2L);
            idList.add(3L);

            Map<String, List<Long>> idMap = new HashMap<>();
            idMap.put("ids", idList);

            List<SysUser> userList = userMapper.selectByIdMap(idMap);
            Assert.assertTrue(userList.size() == 3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByIdMap2() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            Map<String, Long> idMap = new HashMap<>();
            idMap.put("id1", 1L);
            idMap.put("id2", 2L);
            idMap.put("id3", 3L);

            List<SysUser> userList = userMapper.selectByIdMap2(idMap);
            Assert.assertTrue(userList.size() == 3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByObect() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> ids = new ArrayList<>();
            ids.add(1L);
            ids.add(2L);
            ids.add(3L);
            SysUser sysUser = new SysUser();
            sysUser.setIds(ids);

            List<SysUser> userList = userMapper.selectByObject(sysUser);
            Assert.assertTrue(userList.size() == 3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectByObect2() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> ids = new ArrayList<>();
            ids.add(1L);
            ids.add(2L);
            ids.add(4L);
            SysUser sysUser = new SysUser();
            sysUser.setIds(ids);

            SysRole sysRole = new SysRole();
            sysRole.setUser(sysUser);

            List<SysUser> userList = userMapper.selectByObject2(sysRole);
            Assert.assertTrue(userList.size() == 3);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            List<SysUser> userList = new ArrayList<>();
            // 创建一个user对象
            SysUser user = new SysUser();
            user.setUserName("test2");
            user.setUserPassword("123456");
            user.setUserEmail("test@126.com");
            user.setUserInfo("test info");
            // 正常情况下读取一张图片保存到byte数组中
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());

            SysUser user2 = new SysUser();
            user2.setUserName("test3");
            user2.setUserPassword("123456");
            user2.setUserEmail("test@126.com");
            user2.setUserInfo("test info");
            // 正常情况下读取一张图片保存到byte数组中
            user2.setHeadImg(new byte[]{1, 2, 3});
            user2.setCreateTime(new Date());

            userList.add(user);
            userList.add(user2);

            // 将新建的对象插入到数据库中，返回执行SQL影响的行数
            int result = userMapper.insertList(userList);
            Assert.assertEquals(2, result);


            for (SysUser sysUser : userList) {
                System.out.println(sysUser.getId());
            }
            // 只插入一条数据

        } finally {
            // 默认sqlSessionFactory.openSession()是不自动提交的
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdateByMap() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", 2L);
            userMap.put("user_name", "test2");

            int result = userMapper.updateByMap(userMap);
            Assert.assertEquals(1, result);

            SysUser sysUser = userMapper.selectById(2L);
            Assert.assertEquals("test2", sysUser.getUserName());

        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = userMapper.selectUserAndUserById(1001L);
            Assert.assertNotNull(sysUser);
            Assert.assertNotNull(sysUser.getSysRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById2() {
        SqlSession sqlSession = getSqlSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser sysUser = userMapper.selectUserAndUserById2(1001L);
            Assert.assertNotNull(sysUser);
            Assert.assertNotNull(sysUser.getSysRole());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            SysUser user = userMapper.selectUserAndRoleBySelect(1001L);
            Assert.assertNotNull(user);
            System.out.println("调用user.equals(null)");
            user.equals(null);
            System.out.println("调用user.getRole()");
            Assert.assertNotNull(user.getSysRole());
        } finally {
            sqlSession.close();

        }
    }

}
