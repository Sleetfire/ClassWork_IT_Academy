package by.it.academy.MK_JD2_88_2.cw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.cw1.dto.Flight;
import by.it.academy.MK_JD2_88_2.cw1.dto.FlightFilter;
import by.it.academy.MK_JD2_88_2.cw1.dto.Pageable;
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

        String reqPage = req.getParameter("page");
        String reqSize = req.getParameter("size");

        int page = 1;
        if (reqPage != null && !reqPage.isEmpty()) {
            page = Integer.parseInt(reqPage);
        }
        int size = 10;
        if (reqSize != null && !reqSize.isEmpty()) {
            size = Integer.parseInt(reqSize);
        }

        LocalDateTime scheduledDepartureDT = null;
        if (scheduledDeparture != null) {
            scheduledDepartureDT = LocalDateTime.parse(scheduledDeparture);
        }

        FlightFilter filter = FlightFilter.Builder.builder().setDepartureAirport(departureAirport)
                .setArrivalAirport(arrivalAirport)
                .setScheduledDeparture(scheduledDepartureDT)
                .build();

        Pageable pageable = new Pageable();
        pageable.setPage(page);
        pageable.setSize(size);

        List<Flight> flights = this.service.get(filter, pageable);
        this.mapper.writeValue(writer, flights);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String departureAirport = req.getParameter("departureAirport");
        String arrivalAirport = req.getParameter("arrivalAirport");
        String scheduledDeparture = req.getParameter("scheduledDeparture");

        String reqPage = req.getParameter("page");
        String reqSize = req.getParameter("size");

        int page = 1;
        if (reqPage != null && !reqPage.isEmpty()) {
            page = Integer.parseInt(reqPage);
        }
        int size = 10;
        if (reqSize != null && !reqSize.isEmpty()) {
            size = Integer.parseInt(reqSize);
        }

        LocalDateTime scheduledDepartureDT = null;
        if (scheduledDeparture != null) {
            scheduledDepartureDT = LocalDateTime.parse(scheduledDeparture);
        }

        FlightFilter filter = FlightFilter.Builder.builder().setDepartureAirport(departureAirport)
                .setArrivalAirport(arrivalAirport)
                .setScheduledDeparture(scheduledDepartureDT)
                .build();

        Pageable pageable = new Pageable();
        pageable.setPage(page);
        pageable.setSize(size);

        List<Flight> flights = this.service.get(filter, pageable);
        req.setAttribute("flights", flights);
        req.getRequestDispatcher("/views/flights.jsp").forward(req, resp);
    }
}
