package com.itwu.mapper;

import com.itwu.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工工作经历Mapper
 */
@Mapper
public interface EmpExprMapper {

    /**
     * 批量插入员工工作经历
     */
    void insertBatch(List<EmpExpr> exprlist);

    /**
     * 根据员工ID批量删除员工工作经历
     */
    void deleteByEmpIds(@Param("empIds") List<Integer> empIds);

}
