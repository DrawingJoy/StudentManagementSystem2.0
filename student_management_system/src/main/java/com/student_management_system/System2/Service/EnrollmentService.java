package com.student_management_system.System2.Service;

import com.student_management_system.System2.Mapper.EnrollmentMapper;
import com.student_management_system.System2.Pojo.Course;
import com.student_management_system.System2.Pojo.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    // 学生查看可选课程
    public List<Course> getCourseAvailable() {
        return enrollmentMapper.courseAvailable();
    }

    // 学生选课
    public int studentEnroll(Enrollment enrollment) {
        enrollmentMapper.updateCurrentStudents();
        return enrollmentMapper.studentEnroll(enrollment);
    }

    // 学生退课
    public int studentEnrollDelete(int studentId, int courseId) {
        enrollmentMapper.updateCurrentStudents();
        return enrollmentMapper.studentEnrollDelete(studentId, courseId);
    }

    // 学生查看已选课程
    public List<Enrollment> getCourseEnrolled(int studentId) {
        return enrollmentMapper.courseEnrolled(studentId);
    }

    // 老师查看课程学生
    public List<Enrollment> studentOfCourse(int courseId) {
        return enrollmentMapper.studentOfCourse(courseId);
    }

}
