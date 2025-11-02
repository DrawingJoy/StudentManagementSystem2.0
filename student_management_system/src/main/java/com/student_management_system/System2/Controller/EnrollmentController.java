package com.student_management_system.System2.Controller;

import com.student_management_system.System2.Pojo.*;
import com.student_management_system.System2.Service.EnrollmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EnrollmentController {

    @Autowired
    EnrollmentService enrollmentService;

    // 学生查看可选课程
    @GetMapping("/api/courses/available")
    public Response getCourseAvailable() {
        return Response.success(enrollmentService.getCourseAvailable());
    }

    // 学生选课
    @PostMapping("/api/enrollments")
    public Response studentEnroll(HttpServletRequest request, @RequestBody Enrollment enrollment) {
        // 传入学生ID
        enrollment.setStudentId((Integer)request.getAttribute("id"));
        if (enrollmentService.studentEnroll(enrollment) > 0)
            return Response.success(enrollment);
        return Response.error();
    }

    // 学生退课
    @DeleteMapping("/api/enrollments/{id}")
    public Response studentEnrollDelete(HttpServletRequest request, @PathVariable int id) {
        if (enrollmentService.studentEnrollDelete((Integer) request.getAttribute("id"), id) > 0) return Response.success();
        return Response.error();
    }

    // 学生查看已选课程
    @GetMapping("/api/enrollments/student")
    public Response getCourseEnrolled(HttpServletRequest request) {
        int studentId = (Integer)request.getAttribute("id");
        return Response.success(enrollmentService.getCourseEnrolled(studentId));
    }

    // 老师查看课程学生
    @GetMapping("/api/courses/{courseId}/students")
    public Response studentOfCourse(HttpServletRequest request, @PathVariable int courseId) {
        if (!request.getAttribute("role").equals("teacher")) {
            return Response.error("没有操作权限");
        }
        return Response.success(enrollmentService.studentOfCourse(courseId));
    }

}
