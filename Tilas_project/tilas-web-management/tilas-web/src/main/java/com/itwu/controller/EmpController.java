package com.itwu.controller;

import com.itwu.pojo.*;
import com.itwu.service.EmpService;
import com.itwu.anno.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 用于快速导入日志功能 就是那个log.debug() .info()之类的
@RequestMapping("/emps") // 这个在类上加了后，后面的函数就无需再写“/emps”
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     * 
     * @param empQueryParam
     * @return
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        // 由于参数过多，不用@RequestParam，而是手动写一个类，把page,pageSize,name,gender,begin,end全部包进去
        log.info("分页查询:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加员工
     */

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("添加员工:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 批量删除员工
     */
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("批量删除员工 ids:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 查询员工
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer EmpId) {// 使用 @PathVariable 注解提取路径中的参数
        // ("id") 指定了要提取 URL 中名为 {id} 的那一部分
        // Integer deptId 表示将提取到的值绑定给这个方法参数deptId
        log.info("根据ID查询: " + EmpId);

        Emp emp = empService.getById(EmpId);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工: {}", emp);
        empService.update(emp);
        return Result.success();
    }
}
