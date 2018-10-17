package com.mingfei.mybatissamples.mapper;

import com.mingfei.mybatissamples.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 使用注解方式
 * 数据库字段和对象属性值映射方式：
 * 1. 通过别名方式实现字段与属性的映射
 * 2. 在mybatis配置文件中配置mapUnderscoreToCamelCase
 * 3. 使用resultMap方式
 */
public interface RoleMapper {
    /**
     * 使用resultMap方式
     * 定义一个公共的resultMap
     */
    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true), //id=true对XML中<id>元素
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })

    /**
     * 根据ID查询角色
     * 使用resultMap方式，以下方式需要每个语句写一次
     *
     * @param id
     * @return
     */
//    @Results({
//            @Result(property = "id", column = "id", id = true), //id=true对XML中<id>元素
//            @Result(property = "roleName", column = "role_name"),
//            @Result(property = "enabled", column = "enabled"),
//            @Result(property = "createBy", column = "create_by"),
//            @Result(property = "createTime", column = "create_time")
//    })
    @Select("select id,role_name,enabled,create_by,create_time from sys_role where id=#{id}")
    SysRole selectById3(Long id);

    /**
     * 根据ID查询角色
     * 通过别名方式实现字段与属性的映射
     *
     * @param id
     * @return
     */
    @Select({"select id,role_name roleName,enabled,create_by createBy,create_time createTime",
            "from sys_role where id=#{id}"})
//    @Select({"select id,role_name roleName,enabled,create_by createBy,create_time createTime " +
//            "from sys_role where id=#{id}"})
    SysRole selectById(Long id);

    /**
     * 根据ID查询角色
     * 通过别名方式实现字段与属性的映射
     *
     * @param id
     * @return
     */
    @Select({"select id,role_name,enabled,create_by,create_time from sys_role where id=#{id}"})
    SysRole selectById2(Long id);


    /**
     * 查询所有角色
     * 使用公共的resultMap
     */
    @ResultMap("roleResultMap")
    @Select("select * from sys_role")
    List<SysRole> selectAll();

    /**
     * 使用注解插入角色，不返回主键
     * @param role
     * @return
     */
    @Insert({"insert into sys_role (id,role_name,enabled,create_by,create_time)",
            "values(#{id},#{roleName},#{enabled},#{createBy},#{createTime, jdbcType=TIMESTAMP})"})
    int insertRole(SysRole role);

    /**
     * 使用注解插入角色，返回自增主键
     * @param role
     * @return
     */
    @Insert({"insert into sys_role (role_name,enabled,create_by,create_time)",
            "values(#{roleName},#{enabled},#{createBy},#{createTime, jdbcType=TIMESTAMP})"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertRole2(SysRole role);

    /**
     * 使用注解插入角色，返回非自增主键
     * @param role
     * @return
     */
    @Insert({"insert into sys_role (role_name,enabled,create_by,create_time)",
            "values(#{roleName},#{enabled},#{createBy},#{createTime, jdbcType=TIMESTAMP})"})
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",keyProperty = "id",resultType = Long.class,before = false)
    int insertRole3(SysRole role);

    @Update({"update sys_role",
            "set role_name = #{roleName},",
            "enabled = #{enabled},",
            "create_by = #{createBy},",
            "create_time = #{createTime}",
            "where id = #{id}"
    })
    int updateById(SysRole role);

    @Delete({"delete from sys_role where id = #{id}"})
    int deleteById(Long id);
}
