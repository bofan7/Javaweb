package com.itwu.controller;

import com.itwu.pojo.Dept;
import com.itwu.pojo.Result;
import com.itwu.service.DeptService;
import com.itwu.anno.Log;
import lombok.extern.slf4j.Slf4j;
/** import org.slf4j.Logger;
 * import org.slf4j.LoggerFactory;
 * 由于使用了@Slf4j注解，log对象会自动生成，无需再写log = LoggerFactory.getLogger(DeptController.class);
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j // 用于快速导入日志功能 就是那个log.debug() .info()之类的
@RestController
public class DeptController {
    // 由于使用了@Slf4j注解，log对象会自动生成，无需再写,private static final Logger log =
    // LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/depts", method = GET) // 这个是通用注解，默认是get请求，但也可以POST，delete， put
    public Result list() {
        // System.out.println("search all the departments");
        log.info("search all the departments");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 知识点：Spring Boot 参数接收方式对比
     * * 1. 【最常用】不加注解：Integer id
     * - 规则：前端参数名必须与变量名一致 (如 ?id=1)。
     * - 特性：参数是【可选的】，前端不传则为 null，程序不报错。
     * 
     * * 2. 【强制校验】仅加注解：@RequestParam Integer id
     * - 规则：前端参数名必须与变量名一致。
     * - 特性：参数是【必填的】，前端不传直接报 400 Bad Request 错误。
     * - 补救：若想加注解又允许为空，需写 @RequestParam(required = false)。
     * 
     * * 3. 【参数映射】指定别名：@RequestParam("uid") Integer id
     * - 规则：前端传 "uid"，后端用 "id" 接收。
     * - 场景：前后端命名规范不一致，或老接口维护时使用。
     */
    @Log
    @DeleteMapping(value = "/depts")
    // 直接把参数写在方法签名里，Spring会自动帮你把字符串 "1" 转成 int 1
    // depts?id=1 前端以这种方式传参
    public Result delete(@RequestParam Integer id) {
        log.info("删除的ID是: {}", id);
        // 调用Service删除...
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 知识点：@RequestBody 详解
     * * 1. 【核心作用】JSON -> Java对象 (反序列化)
     * - 告诉 Spring 不要去 URL 里找参数，而是去【请求体 Body】里找。
     * - 自动将前端传来的 JSON 字符串的 key，映射到 Java 对象的属性名上。
     * * 2. 【适用场景】
     * - 通常用于 POST (新增) 或 PUT (修改) 请求。
     * - 前端传递的数据是复杂结构 (如表单对象)，且 Header 是 application/json。
     * * 3. 与 @RequestParam对比
     * - @RequestParam: 处理 URL 上的简单参数 (如 ?id=1&name=张三)。
     * - @RequestBody: 处理 Body 里的复杂 JSON 数据 (如 {"name":"张三", "age":18})。
     * * 4. 【注意】
     * - 一个请求通常只能有一个 @RequestBody。
     * - Java 类属性名必须和 JSON 的 key 一致才能接收到值。
     */
    @Log
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        log.info("添加部门:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    // restful格式的 url，在URL路径中定义参数占位符，使用 { } 包裹
    // 这里的 {id} 就是一个路径参数，代表 URL 中这一段是动态的（例如：/depts/1, /depts/5）
    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId) {// 使用 @PathVariable 注解提取路径中的参数
                                                               // ("id") 指定了要提取 URL 中名为 {id} 的那一部分
                                                               // Integer deptId 表示将提取到的值绑定给这个方法参数deptId
        log.info("根据ID查询部门数据: " + deptId);

        // 此时 deptId 已经拿到了 URL 中的具体数值（比如 1），可以直接使用了
        Dept dept = deptService.getById(deptId);
        return Result.success(dept);
    }

    @Log
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门:" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
