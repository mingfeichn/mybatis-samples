package com.mingfei.mybatissamples.mapper;

import com.mingfei.mybatissamples.model.SysPrivilege;
import com.mingfei.mybatissamples.provider.PrivilegeProvider;
import org.apache.ibatis.annotations.SelectProvider;

public interface PrivilegeMapper {

    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);
}
