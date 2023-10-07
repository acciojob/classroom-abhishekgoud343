package com.driver;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

@Repository
public class StudentRepository {

    private ConcurrentHashMap<String, Student> studentDB = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Teacher> teacherDB = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, List<String>> studentTeacherDB = new ConcurrentHashMap<>();

    public void addStudent(Student student) {
        studentDB.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher) {
        teacherDB.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        List<String> list = studentTeacherDB.getOrDefault(teacher, new ArrayList<>());
        list.add(student);
        studentTeacherDB.put(teacher, list);
    }

    public Student getStudent(String name) {
        return studentDB.get(name);
    }

    public List<String> getAllStudents() {
        return new ArrayList<>(studentDB.keySet());
    }

    public List<String> getStudentsByTeacherName(String teacher) {
        return studentTeacherDB.get(teacher);
    }

    public Teacher getTeacher(String name) {
        return teacherDB.get(name);
    }

    public void deleteTeacherByName(String teacher) {
        teacherDB.remove(teacher);

        List<String> students = getStudentsByTeacherName(teacher);
        for (String student : students)
            studentDB.remove(student);

        studentTeacherDB.remove(teacher);
    }

    public void deleteAllTeachers() {
        for (String teacher : teacherDB.keySet())
            deleteTeacherByName(teacher);
    }
}