package com.mingfei.mybatissamples.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * 该类必须有空构造方法
 */
public class PrivilegeProvider {

    /**
     * method的返回值必须是String类
     * @param id
     * @return
     */
    public String selectById(final Long id) {
        // 调用new SQL()方法
        return new SQL() {
            {
                SELECT("id,privilege_name,privilege_url");
                FROM("sys_privilege");
                WHERE("id = #{id}");
            }
        }.toString();
    }

}
