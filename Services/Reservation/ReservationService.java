package Services.Reservation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Pusat penyimpanan seluruh reservasi (penerbangan + hotel).
 * Disimpan sebagai List<Reservation> sehingga diproses secara polimorfik.
 */
public class ReservationService implements ReservationInterface {
    private final List<Reservation> reservations = new ArrayList<>();

    @Override
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Optional<Reservation> findByConfirmation(String confirmationNumber) {
        return reservations.stream()
                .filter(r -> r.getConfirmationNumber().equals(confirmationNumber))
                .findFirst();
    }

    public boolean isConfirmationUsed(String confirmationNumber) {
        return findByConfirmation(confirmationNumber).isPresent();
    }

    /** Membuat nomor konfirmasi 6 digit yang belum dipakai reservasi lain. */
    public String nextConfirmationNumber() {
        return ConfirmationNumberGenerator.generate(this::isConfirmationUsed);
    }

    /**
     * Membatalkan reservasi lalu mengembalikan kursi/kamar ke inventori.
     * Pengecekan tipe memakai pattern matching instanceof (tanpa casting manual).
     */
    @Override
    public Reservation cancelReservation(String confirmationNumber) throws ReservationNotFoundException {
        Reservation reservation = findByConfirmation(confirmationNumber)
                .orElseThrow(() -> new ReservationNotFoundException(confirmationNumber));

        if (reservation instanceof FlightReservation fr) {
            var flight = fr.getFlight();
            flight.setAvailableSeats(flight.getAvailableSeats() + fr.getPassengers());
        } else if (reservation instanceof HotelReservation hr) {
            var hotel = hr.getHotel();
            hotel.setAvailableRooms(hotel.getAvailableRooms() + hr.getRooms());
        }

        reservations.remove(reservation);
        return reservation;
    }

    /** Daftar reservasi, diurutkan berdasarkan nomor konfirmasi. */
    @Override
    public List<Reservation> getAllReservations() {
        return reservations.stream()
                .sorted(Comparator.comparing(Reservation::getConfirmationNumber))
                .toList();
    }

    public double getTotalValue() {
        return reservations.stream().mapToDouble(Reservation::getTotalPrice).sum();
    }
}
