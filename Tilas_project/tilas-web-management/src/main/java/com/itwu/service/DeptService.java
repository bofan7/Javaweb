package com.itwu.service;

import com.itwu.pojo.Dept;
import com.itwu.pojo.Result;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface DeptService {
/**
 * 查询所有部门
 */
    List<Dept> findAll();
    void deleteById(Integer id);
    void add(Dept dept);
    Dept getById(Integer id);
    void update(Dept dept);
}
