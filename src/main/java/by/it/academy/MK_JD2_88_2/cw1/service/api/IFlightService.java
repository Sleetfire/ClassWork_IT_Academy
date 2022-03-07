package by.it.academy.MK_JD2_88_2.cw1.service.api;

import by.it.academy.MK_JD2_88_2.cw1.dto.Flight;
import by.it.academy.MK_JD2_88_2.cw1.dto.FlightFilter;
import by.it.academy.MK_JD2_88_2.cw1.dto.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IFlightService {

    List<Flight> get();
    List<Flight> get(FlightFilter filter, Pageable pageable);
    List<Flight> get(Pageable pageable);
    List<Flight> get(FlightFilter filter);

}
