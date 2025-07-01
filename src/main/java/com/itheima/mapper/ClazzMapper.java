package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {

    /**
     * 条件查询班级
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增班级
     * @param clazz
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Select("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) " +
            "values (#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    /**
     * 根据ID查询班级信息
     * @param id
     * @return
     */
    Clazz getById(Integer id);

    /**
     * 根据ID更新班级信息
     * @param clazz
     */
    void updateById(Clazz clazz);

    /**
     * 返回所有班级信息
     * @return
     */
    List<Clazz> getAll();
}
