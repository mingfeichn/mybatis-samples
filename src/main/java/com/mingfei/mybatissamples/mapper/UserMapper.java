package com.mingfei.mybatissamples.mapper;

import com.mingfei.mybatissamples.model.SysRole;
import com.mingfei.mybatissamples.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    SysUser selectById(Long id);

    List<SysUser> selectAll();

    /**
     * 根据用户ID获取用户角色
     *
     * @param userId
     * @return
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    int insert(SysUser sysUser);

    /**
     * 新增用户
     * 通过userGenerateKeys和keyProperty获取数据库内部生成的id
     * 仅适用于支持自增主键的数据库
     *
     * @param sysUser
     * @return
     */
    int insert2(SysUser sysUser);

    /**
     * 新增用户
     * 使用selectKey获取主键返回的值
     * 适用支持自增和不支持自增主键的数据库
     *
     * @param sysUser
     * @return
     */
    int insert3(SysUser sysUser);

    /**
     * 根据主键更新
     *
     * @param sysUser
     * @return
     */
    int updateById(SysUser sysUser);

    /**
     * 根据ID删除用户
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据ID删除用户
     *
     * @param sysUser
     * @return
     */
    int deleteById(SysUser sysUser);

    /**
     * 根据用户ID和enabled状态获取用户角色
     *
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    /**
     * 根据用户ID和enabled状态获取用户角色
     *
     * @param sysUser
     * @param sysRole
     * @return
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled2(@Param("sysUser") SysUser sysUser, @Param("sysRole") SysRole sysRole);

    /**
     * 根据用户名和邮箱查询用户
     * 使用<if></if>
     * <p>
     * 需求：
     * 当只输入用户名时，根据用户名进行模糊查询；
     * 当只输入邮箱时，根据邮箱进行完全匹配；
     * 当同时输入用户名和密码时，用这两个条件查询匹配的用户
     *
     * @param user
     * @return
     */
    List<SysUser> selectByUser(SysUser user);

    /**
     * 根据用户名和邮箱查询用户
     * 使用<where></where>
     * <p>
     * 需求：
     * 当只输入用户名时，根据用户名进行模糊查询；
     * 当只输入邮箱时，根据邮箱进行完全匹配；
     * 当同时输入用户名和密码时，用这两个条件查询匹配的用户
     *
     * @param user
     * @return
     */
    List<SysUser> selectByUser2(SysUser user);

    /**
     * 根据主键更新
     * 使用<if></if>实现动态列更新，一般选择性更新的方法名以Selective作为后缀
     * <p>
     * 需求：
     * 只更新有变化的字段
     *
     * @param sysUser
     * @return
     */
    int updateByIdSelective(SysUser sysUser);

    /**
     * 根据主键更新
     * 使用<set></set>
     * <p>
     * 需求：
     * 只更新有变化的字段
     *
     * @param sysUser
     * @return
     */
    int updateByIdSelective2(SysUser sysUser);

    /**
     * 插入用户记录
     * 使用<if></if>实现动态插入列
     * <p>
     * 需求：
     * 如果某一列的值不为空则使用传入的值，如果参数为空，则使用字段默认值
     *
     * @param user
     * @return
     */
    int insert4(SysUser user);

    /**
     * 根据ID或用户名查询
     * 使用<choose></choose>
     * <p>
     * 需求：
     * 当id参数有值时优先使用id查询
     * 当id没有值时判断用户名是否有值，若有就用用户名查询，若无则使SQL查询无结果
     *
     * @param sysUser
     * @return
     */
    SysUser selectByIdOrUserName(SysUser sysUser);

    /**
     * 根据用户ID集合查询
     * 使用in ${}
     *
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList(List<Long> idList);

    /**
     * 根据用户ID集合查询
     * 使用foreach实现in集合
     *
     * @param idList
     * @return
     */
    List<SysUser> selectByIdList2(List<Long> idList);


}
