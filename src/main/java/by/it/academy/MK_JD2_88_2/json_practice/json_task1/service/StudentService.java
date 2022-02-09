package by.it.academy.MK_JD2_88_2.json_practice.json_task1.service;


import by.it.academy.MK_JD2_88_2.json_practice.json_task1.dto.Student;
import by.it.academy.MK_JD2_88_2.json_practice.json_task1.service.api.IStudentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentService implements IStudentService {

    private final List<Student> studentList = new ArrayList<>();
    private static final IStudentService instance = new StudentService();

    private StudentService() {

    }

    @Override
    public void addStudentToList(Student student) {
        this.studentList.add(student);
    }

    @Override
    public List<Student> getStudentsList() {
        return Collections.unmodifiableList(this.studentList);
    }

    public static IStudentService getInstance() {
        return instance;
    }
}
