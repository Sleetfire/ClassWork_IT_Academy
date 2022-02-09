package by.it.academy.MK_JD2_88_2.json_practice.json_task1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.json_practice.json_task1.dto.Student;
import by.it.academy.MK_JD2_88_2.json_practice.json_task1.service.StudentService;
import by.it.academy.MK_JD2_88_2.json_practice.json_task1.service.api.IStudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private IStudentService service;
    private ObjectMapper mapper;

    public StudentServlet() {
        this.service = StudentService.getInstance();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        List<Student> studentsList = this.service.getStudentsList();

        this.mapper.writeValue(writer, studentsList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Student student = this.mapper.readValue(req.getInputStream(), Student.class);

        this.service.addStudentToList(student);

    }
}
