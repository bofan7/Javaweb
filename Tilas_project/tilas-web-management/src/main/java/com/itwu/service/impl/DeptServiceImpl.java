package com.itwu.service.impl;

import com.itwu.mapper.DeptMapper;
import com.itwu.pojo.Dept;
import com.itwu.pojo.Result;
import com.itwu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptMapper deptMapper;
    /**
     * 查询所有部门
     */
    public List<Dept> findAll(){
        return deptMapper.findAll();
    }
    /**
     * delete departments
     */
    public void deleteById(Integer id){
        deptMapper.deleteById(id);
    }
    public void add(Dept dept){
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);

    }

    public Dept getById(Integer id){
        return deptMapper.getById(id);
    }

    public void update(Dept dept){
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }

}
