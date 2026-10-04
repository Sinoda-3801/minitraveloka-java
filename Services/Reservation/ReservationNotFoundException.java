package Services.Reservation;

/** Custom exception: dilempar saat nomor konfirmasi tidak ditemukan. */
public class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(String confirmationNumber) {
        super("Reservasi dengan nomor konfirmasi " + confirmationNumber + " tidak ditemukan.");
    }
}
