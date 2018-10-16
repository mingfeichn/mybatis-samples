package com.mingfei.mybatissamples.mapper;

import com.mingfei.mybatissamples.model.Country;

import java.util.List;

public interface CountryMapper {

    List<Country> selectAll();
}
