package com.itwu.controller;

import com.itwu.pojo.Result;
import com.itwu.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位人数");
        // 调用 Service 层获取封装好的 JobOption(或JobStatsVO) 对象
        return Result.success(reportService.getEmpJobData());
    }

    /**
     * 性别
     */
    @GetMapping("/empGenderData")
    public Result getEmpSexData() {
        log.info("统计员工性别人数");
        return Result.success(reportService.getEmpGenderData());
    }
}
