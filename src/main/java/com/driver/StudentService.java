package com.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepositoryObj;

    public void addStudent(Student student) {
        if (student != null) {
            if (studentRepositoryObj.getStudent(student.getName()) == null) {
                studentRepositoryObj.addStudent(student);
                logger.info("Student added");
            } else {
                throw new IllegalArgumentException("Student with the same name already exists");
            }
        } else {
            throw new IllegalArgumentException("Student cannot be null");
        }
    }

    public void addTeacher(Teacher teacher) {
        if (teacher != null) {
            if (studentRepositoryObj.getTeacher(teacher.getName()) == null) {
                studentRepositoryObj.addTeacher(teacher);
                logger.info("Teacher added");
            } else {
                throw new IllegalArgumentException("Teacher with the same name already exists");
            }
        } else {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if (student != null && teacher != null)
            studentRepositoryObj.addStudentTeacherPair(student, teacher);
        else
            throw new IllegalArgumentException("Student and Teacher names cannot be null");
    }

    public Student getStudentByName(String name) {
        Student student = studentRepositoryObj.getStudent(name);
        if (student == null)
            throw new IllegalArgumentException("Student not found in the database");

        return student;
    }

    public Teacher getTeacherByName(String name) {
        return studentRepositoryObj.getTeacher(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentRepositoryObj.getStudentsByTeacherName(teacher);
    }

    public List<String> getAllStudents() {
        logger.info("Getting all students");

        return studentRepositoryObj.getAllStudents();
    }

    public void deleteTeacherByName(String teacher) {
        studentRepositoryObj.deleteTeacherByName(teacher);
    }

    public void deleteAllTeachers() {
        studentRepositoryObj.deleteAllTeachers();
        logger.info("All teachers deleted");
    }
}