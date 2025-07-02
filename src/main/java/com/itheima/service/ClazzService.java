/**
 * @author Wiretender
 * @version 1.0
 */
package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 分页查询
     * @param clazzQueryParam
     * @return
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 根据ID删除班级
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 新增班级
     * @param clazz
     */
    void save(Clazz clazz);


    /**
     * 根据ID查询班级信息
     * @param id
     * @return
     */
    Clazz getInfo(Integer id);


    /**
     * 修改班级信息
     * @param clazz
     */
    void update(Clazz clazz);

    /**
     * 返回所有班级信息
     * @return
     */
    List<Clazz> getAll();
}
