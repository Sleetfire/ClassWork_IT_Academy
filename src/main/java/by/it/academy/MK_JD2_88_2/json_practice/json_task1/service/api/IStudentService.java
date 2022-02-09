package by.it.academy.MK_JD2_88_2.json_practice.json_task1.service.api;

import by.it.academy.MK_JD2_88_2.json_practice.json_task1.dto.Student;

import java.util.List;

public interface IStudentService {

    void addStudentToList(Student student);

    List<Student> getStudentsList();
    
}
