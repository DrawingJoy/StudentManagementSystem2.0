package com.student_management_system.System2.Pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private LocalDate enrollTime;
}
