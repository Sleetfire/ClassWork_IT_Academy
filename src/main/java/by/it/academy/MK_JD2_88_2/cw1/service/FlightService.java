package by.it.academy.MK_JD2_88_2.cw1.service;

import by.it.academy.MK_JD2_88_2.cw1.dto.Flight;
import by.it.academy.MK_JD2_88_2.cw1.service.api.IFlightService;
import by.it.academy.MK_JD2_88_2.cw1.storage.FlightStorage;
import by.it.academy.MK_JD2_88_2.cw1.storage.api.IFlightStorage;

import java.util.List;

public class FlightService implements IFlightService {

    private static IFlightService instance = new FlightService();
    private final IFlightStorage storage = FlightStorage.getInstance();

    private FlightService() {

    }

    @Override
    public List<Flight> get(String departureAirport, String arrivalAirport, String scheduledDeparture) {
        return this.storage.get(departureAirport, arrivalAirport, scheduledDeparture);
    }

    public static IFlightService getInstance() {
        return instance;
    }
}
