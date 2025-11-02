CREATE DATABASE student_management_system;

USE student_management_system;

CREATE TABLE USER (
	id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名',
	PASSWORD INT NOT NULL COMMENT '密码',
	NAME VARCHAR(50) NOT NULL COMMENT '姓名',
	ROLE ENUM('student', 'teacher') NOT NULL COMMENT '身份',
	created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

CREATE TABLE course (
	id INT AUTO_INCREMENT PRIMARY KEY,
	course_name VARCHAR(50) NOT NULL COMMENT '课程名称',
	teacher_id INT NOT NULL COMMENT '授课教师',
	max_students INT DEFAULT 50 COMMENT '最大选课人数',
	current_students INT DEFAULT 0 COMMENT '当前选课人数',
	created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	FOREIGN KEY (teacher_id) REFERENCES USER(id) ON UPDATE CASCADE ON DELETE CASCADE -- 搜了一下百度说不允许简写
);

CREATE TABLE enrollment (
	id INT AUTO_INCREMENT PRIMARY KEY,
	student_id INT NOT NULL COMMENT '学生ID',
	course_id INT NOT NULL COMMENT '课程ID',
	FOREIGN KEY (student_id) REFERENCES USER(id) ON UPDATE CASCADE ON DELETE CASCADE,
	FOREIGN KEY (course_id) REFERENCES course(id) ON UPDATE CASCADE ON DELETE CASCADE,
	enroll_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '选课时间',
	UNIQUE KEY (student_id, course_id) -- 不允许重复选课
);

CREATE INDEX idx_users_username ON USER(username);
CREATE INDEX idx_users_role ON USER(ROLE);
CREATE INDEX idx_courses_teacher_id ON course(teacher_id);
CREATE INDEX idx_enrollments_student_id ON enrollment(student_id);
CREATE INDEX idx_enrollments_course_id ON enrollment(course_id);

INSERT INTO USER (username, PASSWORD, ROLE, NAME) VALUES
-- 老师
('teacher1', '123456', 'TEACHER', 'Aiba老师'),
('teacher2', '123456', 'TEACHER', 'Aina老师'),

-- 学生
('student1', '123456', 'STUDENT', 'Amita'),
('student2', '1232456', 'STUDENT', 'Nakashima'),
('student3', '123456', 'STUDENT', 'Aimi');

INSERT INTO course (course_name, teacher_id, max_students) VALUES
('一辈子用不到的Roselia外语课堂①', 1, 30),
('一辈子用不到的Roselia外语课堂②', 1, 25);

INSERT INTO enrollment (student_id, course_id) VALUES
(1, 1),
(2, 2),
(3, 1);

-- update course set current_students = (select count(*) from enrollment where id = 1); 
-- 这样会有个问题 课程表里有两份课程怎么办

-- 那我们起个别名~
UPDATE course c SET current_students = (SELECT COUNT(*) FROM enrollment e WHERE e.course_id = c.id); 

SELECT * FROM USER;
SELECT * FROM course; 
SELECT * FROM enrollment;

