/**
 * @author Wiretender
 * @version 1.0
 */
package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl  implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        // 执行查询操作
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        // 强制类型转换封装查询结果
        Page<Clazz> p = (Page<Clazz>) clazzList;
        // 返回查询的结果
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    /**
     * 根据ID删除班级
     * @param id
     */
    @Override
    public void delete(Integer id) {
        clazzMapper.deleteById(id);
        // 删除对应班级的学生，保证数据完整性
        studentMapper.deleteByClaId(id);
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }



    @Override
    public Clazz getInfo(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        // 根据ID修改班级的基本信息
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    @Override
    public List<Clazz> getAll() {
        return clazzMapper.getAll();
    }
}
