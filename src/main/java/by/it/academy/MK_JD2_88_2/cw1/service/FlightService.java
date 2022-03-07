package by.it.academy.MK_JD2_88_2.cw1.service;

import by.it.academy.MK_JD2_88_2.cw1.dto.Flight;
import by.it.academy.MK_JD2_88_2.cw1.dto.FlightFilter;
import by.it.academy.MK_JD2_88_2.cw1.dto.Pageable;
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
    public List<Flight> get() {
        return this.storage.get(null, null);
    }

    @Override
    public List<Flight> get(FlightFilter filter, Pageable pageable) {
        return this.storage.get(filter, pageable);
    }

    @Override
    public List<Flight> get(Pageable pageable) {
        return this.storage.get(null, pageable);
    }

    @Override
    public List<Flight> get(FlightFilter filter) {
        return this.storage.get(filter, null);
    }

    public static IFlightService getInstance() {
        return instance;
    }
}
