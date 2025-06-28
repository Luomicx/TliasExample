package com.itheima.service;

import com.itheima.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {
    /**
     * 查询所有部门数据
     */
    List<Dept> findAll();

    /**
     * 根据部门ID删除部门
     * @param deptId
     */
    void deleteById(Integer deptId);

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据ID返回部门数据
     * @param id
     * @return
     */
    Dept getInfo(Integer id);

    void updateById(Dept dept);
}
