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
}
