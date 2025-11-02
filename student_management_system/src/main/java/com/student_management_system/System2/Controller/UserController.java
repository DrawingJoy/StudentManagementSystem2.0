package com.student_management_system.System2.Controller;

import com.student_management_system.System2.Pojo.Response;
import com.student_management_system.System2.Pojo.User;
import com.student_management_system.System2.Pojo.UserDTO;
import com.student_management_system.System2.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // = @Controller+ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    // 查看个人信息
    @GetMapping("/api/users/profile")
    public Response getUserMessage(HttpServletRequest request) {
        UserDTO userDTO = userService.getUserMessage((Integer)request.getAttribute("id"));
        return Response.success(userDTO);
    }

    // 老师查看学生信息
    @GetMapping("/api/users")
    public Response getStudentMessage(HttpServletRequest request) {
        // 验证身份 如果不是老师不能操作
        if (!request.getAttribute("role").equals("teacher")) {
            return Response.error("没有操作权限");
        }
        List<UserDTO> userDTO = userService.getStudentMessage();
        return Response.success(userDTO);
    }

    // 老师创建学生信息
    @PostMapping("/api/users")
    public Response insertStudentMessage(HttpServletRequest request,
                                         @RequestBody User user) {
        // 验证身份 如果不是老师不能操作
        if (!request.getAttribute("role").equals("teacher")) {
            return Response.error("没有操作权限");
        }
        // 创建成功->影响的行数大于0
        if (userService.insertStudentMessage(user) > 0) return Response.success(user);
        return Response.error("该用户已存在");
    }

}
