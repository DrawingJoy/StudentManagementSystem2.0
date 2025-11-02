package com.student_management_system.System2.Pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Course {
    private int id;
    private String courseName;
    private int teacherId;
    private int maxStudents;
    private int currentStudents;
    private LocalDate createdTime;
    private LocalDate updatedTime;
}
