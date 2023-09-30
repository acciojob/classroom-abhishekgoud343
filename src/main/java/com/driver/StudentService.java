package com.driver;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StudentService {
    @Autowired
    StudentRepository studentRepositoryObj;

    public void addStudent(Student student) {
        studentRepositoryObj.addStudent(student);
    }

    public void addTeacher(Teacher teacher) {
        studentRepositoryObj.addTeacher(teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        studentRepositoryObj.addStudentTeacherPair(student, teacher);
    }

    public Student getStudentByName(String name) {
        return studentRepositoryObj.getStudent(name);
    }

    public Teacher getTeacherByName(String name) {
        return studentRepositoryObj.getTeacher(name);
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentRepositoryObj.getStudentsByTeacherName(teacher);
    }

    public List<String> getAllStudents() {
        return studentRepositoryObj.getAllStudents();
    }

    public void deleteTeacherByName(String teacher) {
        studentRepositoryObj.deleteTeacherByName(teacher);
    }

    public void deleteAllTeachers() {
        studentRepositoryObj.deleteAllTeachers();
    }
}