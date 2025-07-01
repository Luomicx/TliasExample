package com.itheima.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    /**
     * 根据班级ID删除学生，保证数据完整性
     * @param id
     */
    void deleteByClaId(Integer id);
}
