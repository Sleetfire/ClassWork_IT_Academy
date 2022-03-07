package by.it.academy.MK_JD2_88_2.cw1.controllers.web.servlets;

import by.it.academy.MK_JD2_88_2.cw1.service.DatesFromDBService;
import by.it.academy.MK_JD2_88_2.cw1.service.StringFromDBService;
import by.it.academy.MK_JD2_88_2.cw1.service.api.IParameterFromDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "FlightMainServlet", urlPatterns = "/flights_main")
public class FlightMainServlet extends HttpServlet {

    private final IParameterFromDBService<String> stringFromDBService;
    private final IParameterFromDBService<LocalDateTime> dateFromDBService;

    public FlightMainServlet() {
        this.stringFromDBService = StringFromDBService.getInstance();
        this.dateFromDBService = DatesFromDBService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> departureAirports = this.stringFromDBService.getByColumnName("departure_airport", "bookings.flights");
        List<String> arrivalAirports = this.stringFromDBService.getByColumnName("arrival_airport", "bookings.flights");
        List<LocalDateTime> scheduledDepartures = this.dateFromDBService.getByColumnName("scheduled_departure", "bookings.flights");

        req.setAttribute("departureAirports", departureAirports);
        req.setAttribute("arrivalAirports", arrivalAirports);
        req.setAttribute("scheduledDepartures", scheduledDepartures);
        req.getRequestDispatcher("/views/flights.jsp").forward(req, resp);
    }
}
