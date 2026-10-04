package Services.Reservation;

import Services.Customer.Customer;
import Services.Flights.Flight;

public final class FlightReservation extends Reservation {
    private final Flight flight;
    private final int passengers;

    public FlightReservation(String confirmationNumber, Customer customer, Flight flight, int passengers) {
        super(confirmationNumber, customer);
        this.flight = flight;
        this.passengers = passengers;
    }

    public Flight getFlight() {
        return flight;
    }

    public int getPassengers() {
        return passengers;
    }

    @Override
    public String getType() {
        return "PENERBANGAN";
    }

    @Override
    public double getTotalPrice() {
        return flight.getPrice() * passengers;
    }

    @Override
    protected String getDetails() {
        return """
            Penerbangan      : %s (%s)
            Rute             : %s -> %s
            Tanggal          : %s, %s - %s
            Jumlah Penumpang : %d
            """.formatted(
                flight.getFlightNumber(), flight.getAirline(),
                flight.getOrigin(), flight.getDestination(),
                flight.getDate(), flight.getDepartureTime(), flight.getArrivalTime(),
                passengers);
    }
}
