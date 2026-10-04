package Services.Flights;

import Services.Customer.Customer;
import Services.Reservation.FlightReservation;
import Services.Reservation.ReservationService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Logika bisnis penerbangan: inventori, pencarian, dan pemesanan. */
public class FlightService {
    private final List<Flight> flights = new ArrayList<>();
    private final ReservationService reservationService;

    public FlightService(ReservationService reservationService) {
        this.reservationService = reservationService;
        initSampleData();
    }

    /** Data contoh memakai tanggal relatif terhadap hari ini agar selalu relevan. */
    private void initSampleData() {
        add(1, "GA-401", "Garuda Indonesia", "Jakarta", "Bali", 1, "08:00", "10:50", 1500000, 50);
        add(2, "JT-610", "Lion Air", "Jakarta", "Bali", 1, "11:30", "14:20", 950000, 100);
        add(3, "QG-880", "Citilink", "Jakarta", "Bali", 1, "17:15", "20:05", 1100000, 3);
        add(4, "GA-402", "Garuda Indonesia", "Jakarta", "Bali", 2, "08:00", "10:50", 1650000, 40);
        add(5, "JT-312", "Lion Air", "Jakarta", "Surabaya", 1, "09:00", "10:30", 800000, 100);
        add(6, "QZ-7510", "AirAsia", "Jakarta", "Kuala Lumpur", 2, "10:00", "12:50", 1200000, 75);
        add(7, "SJ-250", "Sriwijaya Air", "Jakarta", "Medan", 3, "11:00", "13:30", 900000, 80);
        add(8, "QG-150", "Citilink", "Jakarta", "Yogyakarta", 1, "12:00", "13:15", 700000, 60);
        add(9, "GA-403", "Garuda Indonesia", "Bali", "Jakarta", 3, "13:00", "13:55", 1550000, 45);
        add(10, "JT-611", "Lion Air", "Surabaya", "Jakarta", 2, "15:00", "16:30", 820000, 90);
    }

    private void add(int id, String number, String airline, String origin, String destination,
                     int dayOffset, String dep, String arr, double price, int seats) {
        flights.add(new Flight(id, number, airline, origin, destination,
                LocalDate.now().plusDays(dayOffset), dep, arr, price, seats));
    }

    public List<Flight> getAllFlights() {
        return flights;
    }

    public Flight getFlightById(int id) {
        return flights.stream()
                .filter(flight -> flight.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Penerbangan dengan ID " + id + " tidak ditemukan."));
    }

    /**
     * Mencari penerbangan berdasarkan kota asal, tujuan, tanggal, dan jumlah penumpang.
     * Hasil diurutkan dari harga termurah.
     */
    public List<Flight> searchFlights(String origin, String destination, LocalDate date, int passengers) {
        return flights.stream()
                .filter(f -> f.getOrigin().equalsIgnoreCase(origin.trim()))
                .filter(f -> f.getDestination().equalsIgnoreCase(destination.trim()))
                .filter(f -> f.getDate().equals(date))
                .filter(f -> f.getAvailableSeats() >= passengers)
                .sorted(Comparator.comparingDouble(Flight::getPrice))
                .toList();
    }

    public FlightReservation bookFlight(int flightId, Customer customer, int passengers) {
        if (passengers <= 0) {
            throw new IllegalArgumentException("Jumlah penumpang harus lebih dari 0.");
        }

        Flight flight = getFlightById(flightId);
        if (flight.getAvailableSeats() < passengers) {
            throw new IllegalStateException("Kursi tidak cukup. Tersisa " + flight.getAvailableSeats()
                    + " kursi, diminta " + passengers + ".");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - passengers);

        FlightReservation reservation = new FlightReservation(
                reservationService.nextConfirmationNumber(), customer, flight, passengers);
        reservationService.addReservation(reservation);
        return reservation;
    }
}
