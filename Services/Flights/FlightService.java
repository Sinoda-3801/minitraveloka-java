package Services.Flights;

import java.util.ArrayList;
import Services.Customer.Customer;
import Services.Reservation.ReservationInterface;
import Services.Reservation.Reservation;
import java.util.List;

public class FlightService implements ReservationInterface {
    private ArrayList<Flight> flights;
    private ArrayList<Reservation> reservations;

    private void initSampleData() {
        flights.add(new Flight(1, "Garuda Indonesia", "Jakarta", "Bali", "2024-07-01 08:00", "2024-07-01 10:00", 1500000.0, 50));
        flights.add(new Flight(2, "Lion Air", "Jakarta", "Surabaya", "2024-07-02 09:00", "2024-07-02 11:00", 800000.0, 100));
        flights.add(new Flight(3, "AirAsia", "Jakarta", "Kuala Lumpur", "2024-07-03 10:00", "2024-07-03 12:00", 1200000.0, 75));
        flights.add(new Flight(4, "Sriwijaya Air", "Jakarta", "Medan", "2024-07-04 11:00", "2024-07-04 13:00", 900000.0, 80));
        flights.add(new Flight(5, "Citilink", "Jakarta", "Yogyakarta", "2024-07-05 12:00", "2024-07-05 14:00", 700000.0, 60));
    }

    public FlightService() {
        this.flights = new ArrayList<Flight>();
        this.reservations = new ArrayList<Reservation>();
        
        initSampleData();
    }

    public ArrayList<Flight> getAllFlights() {
        return flights;
    }

    public Flight getFlightById(int id) {
        return flights.stream()
                .filter(flight -> flight.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Flight> searchFlight(String search) {
        String keyword = search.toLowerCase();

        return flights.stream()
                .filter(flight ->
                        flight.getAirline().toLowerCase().contains(keyword)
                        || flight.getOrigin().toLowerCase().contains(keyword)
                        || flight.getDestination().toLowerCase().contains(keyword)
                )
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public Reservation reservation(Integer bookId, Customer customer) {
        Flight selectedFlight = flights.stream()
                .filter(flight -> flight.getId() == bookId)
                .findFirst()
                .orElse(null);
        
        if (selectedFlight == null) {
            throw new IllegalArgumentException("Penerbangan dengan ID " + bookId + " tidak ditemukan.");
        }

        if (selectedFlight.getAvailableSeats() <= 0) {
            throw new IllegalStateException("Maaf, penerbangan ini sudah penuh.");
        }

        selectedFlight.setAvailableSeats(selectedFlight.getAvailableSeats() - 1);

        String generateBookingId = "R" + (reservations.size() + 1);
        reservations.add(
          new Reservation(generateBookingId, customer, selectedFlight)
        );

        return reservations.get(reservations.size() - 1);
    }

    public Reservation cancelReservation(String reservationId) {
        Reservation selectedReservation = reservations.stream()
                .filter(reservation -> reservation.bookingCode().equals(reservationId.toString()))
                .findFirst()
                .orElse(null);

        if (selectedReservation == null) {
            throw new IllegalArgumentException("Reservasi dengan ID " + reservationId + " tidak ditemukan.");
        }

        Flight flightToCancel = selectedReservation.getFlight();
        flightToCancel.setAvailableSeats(flightToCancel.getAvailableSeats() + 1);
        reservations.remove(selectedReservation);

        return selectedReservation;
    }

    public ArrayList<Reservation> getAllReservations() {
        return reservations;
    }
}