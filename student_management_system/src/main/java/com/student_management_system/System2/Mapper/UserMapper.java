package com.student_management_system.System2.Mapper;

import com.student_management_system.System2.Pojo.User;
import com.student_management_system.System2.Pojo.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    // 查看个人信息
    @Select("select * from user where id = #{id}")
    UserDTO showUserMessage(int id); // IDEA提示说修饰符对于接口成员是冗余的

    // 老师查看学生信息
    @Select("select * from user where role = 'student'")
    List<UserDTO> showStudentsMessage();

    // 老师创建学生信息
    @Insert("insert into user(username, password, name, role) " +
            "values (#{username}, #{password}, #{name}, #{role})")
    int insertStudentMessage(User user); // 返回此次操作影响的记录数

    // 用户登录
    @Select("select * from user where username = #{username} and password = #{password}")
    UserDTO login(User user);

}
