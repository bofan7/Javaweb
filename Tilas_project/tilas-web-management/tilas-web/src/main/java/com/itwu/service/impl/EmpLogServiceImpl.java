package com.itwu.service.impl;

import com.itwu.mapper.EmpLogMapper;
import com.itwu.pojo.EmpLog;
import com.itwu.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)//不会被propagation rollback影响
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
