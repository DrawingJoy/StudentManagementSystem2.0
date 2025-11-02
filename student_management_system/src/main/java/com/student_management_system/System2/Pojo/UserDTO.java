package com.student_management_system.System2.Pojo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String name;
    private String role;
    private LocalDate createdTime;
    private LocalDate updatedTime;
}
