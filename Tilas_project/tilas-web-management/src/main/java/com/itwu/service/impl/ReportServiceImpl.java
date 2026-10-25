package com.itwu.service.impl;

import com.itwu.mapper.EmpMapper;
import com.itwu.pojo.JobStatisticsVO;
import com.itwu.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobStatisticsVO getEmpJobData() {
        // 1. 调用 mapper 接口获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        // 2. 组装结果
        List<Object> jobList = new ArrayList<>();
        List<Object> dataList = new ArrayList<>();

        for (Map<String, Object> map : list) {
            jobList.add(map.get("pos"));
            dataList.add(map.get("num"));
        }

        return new JobStatisticsVO(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
