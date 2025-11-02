package com.student_management_system.System2.Controller;

import com.student_management_system.System2.Pojo.Course;
import com.student_management_system.System2.Pojo.Response;
import com.student_management_system.System2.Pojo.User;
import com.student_management_system.System2.Service.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 老师创建课程
    @PostMapping("/api/courses")
    public Response insertCourse(HttpServletRequest request, @RequestBody Course course) {
        // 验证身份 如果不是老师不能操作
        if (!request.getAttribute("role").equals("teacher")) {
            return Response.error("没有操作权限");
        }
        if (courseService.insertCourse(course) > 0) return Response.success(course);
        else return Response.error("该课程已存在"); // 实际上并不能返回错误信息 只会报错 我后面研究研究怎么插入一个异常处理
    }

    // 老师修改课程
    // 呃呃 我不太知道怎么用上{id} 先拿个简单的方法写一下
    @PutMapping("/api/courses/{id}") // 等等我没有用到这个{id}?!
    public Response updateCourse(HttpServletRequest request, @PathVariable int id, @RequestBody Course course) {
        // 验证身份 如果不是老师不能操作
        if (!request.getAttribute("role").equals("teacher")) {
            return Response.error("没有操作权限");
        }
        course.setId(id);
        courseService.updateCourse(course);
        return Response.success(course);
    }

    // 老师删除课程
    @DeleteMapping("/api/courses/{id}")
    public Response deleteCourse(HttpServletRequest request, @PathVariable int id) {
        // 验证身份 如果不是老师不能操作
        if (!request.getAttribute("role").equals("teacher")) {
            return Response.error("没有操作权限");
        }
        // 课程是否存在 能不能被删除
        if (courseService.deleteCourse(id) > 0) return Response.success();
        else return Response.error("不存在该课程 删除失败");
    }

    // 老师查看全部课程
    @GetMapping("/api/courses")
    public Response courseOfTeacher(HttpServletRequest request) {
        // 验证身份 如果不是老师不能操作
        if (!request.getAttribute("role").equals("teacher")) {
            return Response.error("没有操作权限");
        }
        return Response.success(courseService.courseOfTeacher((Integer)request.getAttribute("id")));
    }

}
