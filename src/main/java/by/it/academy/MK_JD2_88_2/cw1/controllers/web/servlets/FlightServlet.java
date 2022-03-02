package by.it.academy.MK_JD2_88_2.cw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.cw1.dto.Flight;
import by.it.academy.MK_JD2_88_2.cw1.service.FlightService;
import by.it.academy.MK_JD2_88_2.cw1.service.api.IFlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "FlightServlet", urlPatterns = "/flights")
public class FlightServlet extends HttpServlet {

    private final IFlightService service;
    private final ObjectMapper mapper;

    public FlightServlet() {
        this.service = FlightService.getInstance();
        this.mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        mapper.registerModule(module);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        String departureAirport = req.getParameter("departureAirport");
        String arrivalAirport = req.getParameter("arrivalAirport");
        String scheduledDeparture = req.getParameter("scheduledDeparture");

        List<Flight> flights = this.service.get(departureAirport, arrivalAirport, scheduledDeparture);
        this.mapper.writeValue(writer, flights);

    }
}
