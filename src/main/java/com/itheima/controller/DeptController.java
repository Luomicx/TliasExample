/**
 * @author Wiretender
 * @version 1.0
 */
package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
        System.out.println("删除部门，ID为： " + deptId);
        deptService.deleteById(deptId);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        System.out.println("添加部门 " + dept);
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        System.out.println("根据ID查询部门数据： " + id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    /**
     * 根据ID来更新部门数据
     */
    @Log
    @PutMapping
    public Result updateInfo(@RequestBody Dept dept) {
        System.out.println("修改部门：" + dept);
        deptService.updateById(dept);
        return Result.success();
    }
}
