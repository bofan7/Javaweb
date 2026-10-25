package com.itwu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itwu.mapper.EmpExprMapper;
import com.itwu.mapper.EmpMapper;
import com.itwu.pojo.*;
import com.itwu.service.EmpLogService;
import com.itwu.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
// import java.util.Arrays; removed duplicate

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    /**
     * 分页查询员工信息
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        /*
         * Long total= empMapper.count();
         * Integer start=(page-1)*pageSize;
         * List<Emp> list=empMapper.list(start,pageSize);
         * return new PageResult<Emp>(total,list);
         * 以上的代码，是原始的分页查询代码，现在使用MyBatis的分页插件进行分页查询
         */

        // 1. 设置分页参数 (开启自动驾驶)
        // 这一行代码必须紧贴在执行 SQL 查询之前！
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        // 2. 执行查询
        // 这里的 list() 虽然 SQL 里没写 limit，但 PageHelper 会自动加上
        List<Emp> emplist = empMapper.list(empQueryParam);

        // 3. 封装结果
        // PageInfo 是 PageHelper 提供的强大的结果封装类
        // 它不仅包含 list，还包含了 total, pages(总页数), prePage(上一页) 等一堆信息
        PageInfo<Emp> pageInfo = new PageInfo<>(emplist);

        // 4. 为了配合你之前的接口定义，我们将 PageInfo 的数据取出来放入你的 PageResult
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 新增员工
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        try {
            // 1.补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            // 2.保存员工基本信息
            empMapper.insert(emp);
            // 3.保存员工工作信息
            List<EmpExpr> exprlist = emp.getExprList();
            if (exprlist != null) {
                // 遍历,填充员工ID
                for (EmpExpr expr : exprlist) {
                    expr.setEmpId(emp.getId());
                }
                empExprMapper.insertBatch(exprlist);
            }

        } finally {

            // 记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "添加员工" + emp);
            empLogService.insertLog(empLog);
        }

    }

    /**
     * 批量删除员工
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        // 1.批量删除员工基本信息
        empMapper.delete(ids);
        // 2.批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /**
     * 查询回显
     */
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改员工信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        // 1. 根据ID修改员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        // 2. 根据ID修改员工的工作经历信息
        // 2.1 先根据员工ID删除原有的工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        // 2.2 再添加这个员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        Integer id = emp.getId();

        // 检测是否为空且集合内有元素
        if (exprList != null && !exprList.isEmpty()) {
            for (EmpExpr expr : exprList) {
                expr.setEmpId(id);
            }
            // 执行批量插入逻辑
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        // 1.调用mapper接口查询
        Emp e = empMapper.getByUsernameAndPassword(emp);

        // 2.判断
        if (e != null) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.setId(e.getId());
            loginInfo.setUsername(e.getUsername());
            loginInfo.setName(e.getName());
            return loginInfo;
        }

        return null;
    }
}
