package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * 分页查询方法
     * @return
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工信息
     * @param emp
     */
    void save(Emp emp);


    /**
     * 删除员工根据id
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据员工Id查询员工信息
     * @param id
     * @return
     */
    Emp getInfo(Integer id);
    /**
     * 更新员工信息
     * @param emp
     */
    void update(Emp emp);
}
