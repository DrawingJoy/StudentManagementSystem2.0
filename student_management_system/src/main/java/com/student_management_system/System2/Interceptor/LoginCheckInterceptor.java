package com.student_management_system.System2.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.student_management_system.System2.Pojo.Response;
import com.student_management_system.System2.Utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

// 拦截器
@Component
public class LoginCheckInterceptor implements HandlerInterceptor{

    // Ctrl+O 快速重写方法

    @Override // 目标资源方法运行前运行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI(); // 获取路径

        // 登录路径直接放行
        if (url.contains("/api/auth/login")) {
            return true;
        }

        // 获取请求头中的令牌
        String jwt = request.getHeader("Authorization");

        // 如果JWT令牌没有长度 也不存在令牌 未登录
        if (!StringUtils.hasLength(jwt)) {
            Response error = Response.error("NOT_LOGIN"); // 给前端看的
            // 手动转换对象->JSON 引入fastJSON依赖 返回JSON格式对象
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            // 不放行
            System.out.println("没有令牌？？");
            return false;
        }

        // 如果token错误
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
//            // 下面这个应该是打印错误信息用的
//            e.printStackTrace();
            Response error = Response.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            // 不放行
            System.out.println("JTW令牌解析失败");
            return false;
        }

        System.out.println("登录成功~解析JWT令牌成功");
        // 令牌合法gogogo
        int id = JwtUtils.getUserIdFromJWT(jwt);
        String name = JwtUtils.getUserNameFromJWT(jwt);
        String role = JwtUtils.getUserRoleFromJWT(jwt);
        request.setAttribute("id", id);
        request.setAttribute("name", name);
        request.setAttribute("role", role);
        return true;

    }

    @Override // 目标方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 我也不知道下面这行是做什么用的 重写时候自动附上的
        // HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

//    @Override // 视图渲染完毕后运行 最后运行（？)
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }

}
