package com.student_management_system.System2.Pojo;

import lombok.Data;

@Data
public class Response {
    private int code;
    private String message;
    private Object data;

    // 响应成功
    public static Response success(Object data) {
        Response response = new Response();
        response.setCode(200);
        response.setMessage("操作成功");
        response.setData(data);
        return response;
    }

    public static Response success() {
        Response response = new Response();
        response.setCode(200);
        response.setMessage("操作成功");
        response.setData(null);
        return response;
    }

    // 响应失败
    public static Response error() {
        Response response = new Response();
        response.setCode(401);
        response.setMessage("操作失败");
        response.setData(null);
        return response;
    }

    public static Response error(String message) {
        Response response = new Response();
        response.setCode(401);
        response.setMessage(message);
        response.setData(null);
        return response;
    }

    public static Response error(String message, Object data) {
        Response response = new Response();
        response.setCode(401);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

}
