package com.student_management_system.System2.Mapper;

import com.student_management_system.System2.Pojo.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseMapper {

    // 教师创建课程
    // SQL方言问题 MySQL和Generic SQL
    @Insert("insert into course(course_name, teacher_id, max_students)" +
    "values (#{courseName}, #{teacherId}, #{maxStudents})")
    int insertCourse(Course course);

    // 教师修改课程
    // 感觉这里应该用动态SQL的 因为我不可能每次都要改这么多东西 后期我会修改的
    @Update("update course set course_name = #{courseName}, max_students = #{maxStudents} where id = #{id}")
    int updateCourse(Course course);

    // 教师删除课程
    @Delete("delete from course where id = #{id}")
    int deleteCourse(int id);

    // 老师查看所有课程
    @Select("select * from course where (teacher_id = #{teacherId})")
    List<Course> courseOfTeacher(int teacherId);

}
