package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    private HashMap<String, Student> studentDB = new HashMap<>();
    private HashMap<String,Teacher> teacherDB = new HashMap<>();
    private HashMap<String, List<String>> studentTeacherDB = new HashMap<>();

    public void addStudent(Student student) {
        studentDB.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDB.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        studentTeacherDB.get(teacher).add(student);
    }

    public Student getStudent(String name) {
        return studentDB.get(name);
    }

    public List<String> getAllStudents() {
        List<String> list = new ArrayList<>();
        for (Student student : studentDB.values())
            list.add(student.getName());

        return list;
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentTeacherDB.get(teacher);
    }

    public Teacher getTeacher(String name) {
        return teacherDB.get(name);
    }

    public void deleteTeacherByName(String teacher) {
        teacherDB.remove(teacher);
        studentTeacherDB.remove(teacher);
    }

    public void deleteAllTeachers() {
        teacherDB.clear();
        studentTeacherDB.clear();
    }
}