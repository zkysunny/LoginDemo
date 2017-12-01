package com.neo.thymeleaf.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * Created by jason on 2017/12/1.
 */
@Mapper
public interface InfoMapper {

    // 通过Parameter新增
    @Insert(value="INSERT INTO loginfo(information, date) VALUES(#{information}, #{date})")
    int insertByParameter(@Param("information") String name, @Param("date") Date date);
}
