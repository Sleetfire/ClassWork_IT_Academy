package by.it.academy.MK_JD2_88_2.json_practice.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.json_practice.dto.Citizen;
import by.it.academy.MK_JD2_88_2.json_practice.service.CitizenService;
import by.it.academy.MK_JD2_88_2.json_practice.service.api.IJsonObjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CitizenServlet", urlPatterns = "/citizens")
public class CitizenServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final IJsonObjectService<Citizen> service;

    public CitizenServlet() {
        this.mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        this.service = CitizenService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        List<Citizen> citizensList = this.service.getJsonObjectsList();

        this.mapper.writeValue(writer,citizensList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Citizen citizen = this.mapper.readValue(req.getInputStream(), Citizen.class);
        this.service.addJsonObjectToList(citizen);
    }
}
