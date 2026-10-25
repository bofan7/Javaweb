package com.itwu.service;

import com.itwu.pojo.*;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /**
     * 分页查询员工信息
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 保存员工信息
     */
    void save(Emp emp);

    /**
     * 批量删除员工
     */
    void delete(List<Integer> ids);

    /**
     * 获取员工信息
     */
    Emp getById(Integer Id);

    /**
     * 更新员工信息
     */
    void update(Emp emp);

    /**
     * 员工登录
     * 
     * @param emp
     * @return
     */
    LoginInfo login(Emp emp);
}
