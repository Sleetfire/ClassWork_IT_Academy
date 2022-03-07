package by.it.academy.MK_JD2_88_2.cw1.dto;

import java.time.LocalDateTime;

public class FlightFilter {

    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime scheduledDeparture;

    private FlightFilter(String departureAirport, String arrivalAirport, LocalDateTime scheduledDeparture) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.scheduledDeparture = scheduledDeparture;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(LocalDateTime scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public static class Builder {
        private String departureAirport;
        private String arrivalAirport;
        private LocalDateTime scheduledDeparture;

        private Builder() {
        }

        public static Builder builder(){
            return new Builder();
        }

        public Builder setDepartureAirport(String departureAirport) {
            this.departureAirport = departureAirport;
            return this;
        }

        public Builder setArrivalAirport(String arrivalAirport) {
            this.arrivalAirport = arrivalAirport;
            return this;
        }

        public Builder setScheduledDeparture(LocalDateTime scheduledDeparture) {
            this.scheduledDeparture = scheduledDeparture;
            return this;
        }

        public FlightFilter build() {
            return new FlightFilter(departureAirport, arrivalAirport, scheduledDeparture);
        }
    }

}


