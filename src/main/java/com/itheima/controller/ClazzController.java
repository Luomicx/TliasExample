/**
 * @author Wiretender
 * @version 1.0
 */
package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("班级分页查询：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 根据ID删除班级
     */
    @Log
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("删除班级：{}", id);
        // 调用Service 层
        clazzService.deleteById(id);
        return Result.success();
    }

    /**
     * 添加班级
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("新增班级：{}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }


    /**
     * 根据ID查询班级信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据ID查询班级信息：{}", id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级的数据信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级信息:{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 返回所有班级信息
     */
    @GetMapping("/list")
    public Result getAll() {
        log.info("查询所有班级信息");
        List<Clazz> clazzList =  clazzService.getAll();
        return Result.success(clazzList);
    }
}
