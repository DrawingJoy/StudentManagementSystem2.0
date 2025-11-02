package com.student_management_system.System2.Service;

import com.student_management_system.System2.Mapper.CourseMapper;
import com.student_management_system.System2.Pojo.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    // 老师创建课程
    public int insertCourse(Course course) {

        return courseMapper.insertCourse(course);
    }

    // 老师修改课程
    public int updateCourse(Course course) {
        return courseMapper.updateCourse(course);
    }

    // 老师删除课程
    public int deleteCourse(int id) {
        return courseMapper.deleteCourse(id);
    }

    // 老师查看全部课程
    public List<Course> courseOfTeacher(int teacherId) {
        return courseMapper.courseOfTeacher(teacherId);
    }

}
