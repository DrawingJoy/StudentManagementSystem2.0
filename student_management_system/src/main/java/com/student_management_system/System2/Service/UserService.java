package com.student_management_system.System2.Service;

import com.student_management_system.System2.Mapper.UserMapper;
import com.student_management_system.System2.Pojo.User;
import com.student_management_system.System2.Pojo.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 用户查看个人信息
    public UserDTO getUserMessage(int id) {
        return userMapper.showUserMessage(id);
    }

    // 老师查看学生信息
    public List<UserDTO> getStudentMessage() {
        return userMapper.showStudentsMessage();
    }

    // 老师创建学生信息
    public int insertStudentMessage(User user) {
        return userMapper.insertStudentMessage(user);
    }

    // 用户登录
    public UserDTO login(User user) {
        return userMapper.login(user);
    }

}
