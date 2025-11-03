package com.student_management_system.System2.Exception;

import com.student_management_system.System2.Pojo.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExcepetionHandler {

    @ExceptionHandler(Exception.class) // 捕获所有异常
    public Response ex(Exception ex) {
        ex.printStackTrace(); // 输出异常堆栈信息
        return Response.error("对不起 操作失败 请联系管理员");
    }

}
