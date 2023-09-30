package com.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    private ConcurrentHashMap<String, Student> studentDB = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Teacher> teacherDB = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, List<String>> studentTeacherDB = new ConcurrentHashMap<>();

    public void addStudent(Student student) {
        if (!studentDB.containsKey(student.getName())) {
            studentDB.put(student.getName(), student);
        } else {
            throw new IllegalArgumentException("Student with the same name already exists");
        }
    }

    public void addTeacher(Teacher teacher) {
        if (!teacherDB.containsKey(teacher.getName())) {
            teacherDB.put(teacher.getName(), teacher);
        } else {
            throw new IllegalArgumentException("Teacher with the same name already exists");
        }
    }

    public void addStudentTeacherPair(String student, String teacher) {
        List<String> list = studentTeacherDB.getOrDefault(teacher, new ArrayList<>());
        list.add(student);
        studentTeacherDB.put(teacher, list);
    }

    public Student getStudent(String name) {
        Student student = studentDB.get(name);
        if (student == null) {
            throw new IllegalArgumentException("Student not found in the database");
        }
        return student;
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
        Teacher teacher = teacherDB.get(name);
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher not found in the database");
        }
        return teacher;
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