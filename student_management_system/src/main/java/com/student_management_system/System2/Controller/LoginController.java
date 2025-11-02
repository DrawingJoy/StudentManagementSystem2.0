package com.student_management_system.System2.Controller;

import com.student_management_system.System2.Pojo.Response;
import com.student_management_system.System2.Pojo.User;
import com.student_management_system.System2.Pojo.UserDTO;
import com.student_management_system.System2.Service.UserService;
import com.student_management_system.System2.Utils.JwtUtils;
// import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// @Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    // 用户登录
    @PostMapping("/api/auth/login")
    public Response login(@RequestBody User user) {
        //log.info("用户登录:", user); // 日志
        UserDTO user1 = userService.login(user);
        // 登录成功
        if (user1 != null) {
            // 创建秘钥
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user1.getId());
            claims.put("name", user1.getName());
            claims.put("role", user1.getRole());

            String jwt = JwtUtils.generateJwt(claims); //生成令牌
            return Response.success(jwt);
        }

        // 登录失败
        return Response.error();
    }
}
