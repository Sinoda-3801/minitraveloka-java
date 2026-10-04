package Services.Reservation;

import java.util.List;

/** Kontrak pengelolaan reservasi (penyimpanan, pembatalan, dan daftar). */
public interface ReservationInterface {
    void addReservation(Reservation reservation);

    Reservation cancelReservation(String confirmationNumber) throws ReservationNotFoundException;

    List<Reservation> getAllReservations();
}
