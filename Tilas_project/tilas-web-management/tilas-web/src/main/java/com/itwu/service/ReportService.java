package com.itwu.service;

import com.itwu.pojo.ClazzCountOption;
import com.itwu.pojo.JobStatisticsVO;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工职位人数
     * 
     * @return
     */
    JobStatisticsVO getEmpJobData();

    /**
     * 统计员工性别人数
     * 
     * @return
     */
    List<Map<String, Object>> getEmpGenderData();

    /**
     * 统计学员学历人数
     * 
     * @return
     */
    List<Map> getStudentDegreeData();

    /**
     * 统计班级人数
     * 
     * @return
     */
    ClazzCountOption getStudentCountData();
}
