package com.itwu.exception;

import com.itwu.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex) {
        ex.printStackTrace();
        return Result.error("对不起,操作失败,请联系管理员");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错啦~", e);

        String message = e.getMessage();
        // 寻找错误信息中 "Duplicate entry" 的位置
        int i = message.indexOf("Duplicate entry");

        // 截取从该位置开始的字符串
        String errMsg = message.substring(i);

        // 按空格切分，提取具体的冲突值
        String[] arr = errMsg.split(" ");

        // 返回格式化的错误提示，例如："张三 已存在"
        return Result.error(arr[2] + " 已存在");
    }

    /**
     * 处理业务异常
     */
    @ExceptionHandler
    public Result handleBusinessException(BusinessException e) {
        log.error("业务异常: {}", e.getMessage());
        return Result.error(e.getMessage());
    }
}
