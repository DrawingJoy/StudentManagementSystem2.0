package com.student_management_system.System2.Mapper;

import com.student_management_system.System2.Pojo.Course;
import com.student_management_system.System2.Pojo.Enrollment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EnrollmentMapper {

    // 学生查看可选课程
    @Select("select * from course where current_students < max_students")
    List<Course> courseAvailable();

    // 学生选课
    // 这里有一个问题 学生单方面选课完毕 但是课程那张表的数据也就是现在选课人数并没有实时更新
    @Insert("insert into enrollment(student_id, course_id) " +
            "values(#{studentId}, #{courseId})")
    int studentEnroll(Enrollment enrollment);

    // 更新选课人数
    // 呃虽然加了这个但是没有实现更新 我要研究一下怎么回事
    @Update("update course c set current_students = (select COUNT(*) from enrollment where course_id = c.id)")
    void updateCurrentStudents();

    // 学生退课
    @Delete("delete from enrollment where student_id = #{studentId} and course_id = #{courseId}")
    int studentEnrollDelete(int studentId, int courseId);

    // 学生查看已选课程
    @Select("select * from enrollment where student_id = #{studentId}")
    List<Enrollment> courseEnrolled(int studentId);

    // 老师查看课程学生
    // 本来我想返回User类型的 但是我一时半会想不到怎么写 上面那个同理 我初步想法是在Enrollment里面补加内容（还未实践并且认为这也太没有技术含量了） 不过我感觉会有更好的办法 待我摸索摸索
    // 诶是不是可以可以起个别名 我要研究一下
    @Select("select * from enrollment where course_id = #{courseId}")
    List<Enrollment> studentOfCourse(int courseId);

}
