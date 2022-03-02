package by.it.academy.MK_JD2_88_2.cw1.service.api;

import by.it.academy.MK_JD2_88_2.cw1.dto.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface IFlightService {

    List<Flight> get(String departureAirport, String arrivalAirport, String scheduledDeparture);

}
