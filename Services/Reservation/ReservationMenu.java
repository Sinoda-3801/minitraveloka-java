package Services.Reservation;

import Services.Utils.ConsoleInput;
import Services.Utils.CurrencyFormatter;
import java.util.List;

/** Menu lintas-jenis: batalkan reservasi dan lihat semua pemesanan. */
public class ReservationMenu {
    private final ReservationService reservationService;
    private final ConsoleInput input;

    public ReservationMenu(ReservationService reservationService, ConsoleInput input) {
        this.reservationService = reservationService;
        this.input = input;
    }

    public void handleCancelReservation() {
        System.out.println("\n[ Pembatalan Reservasi ]");
        String number = input.readNonEmpty("Masukkan Nomor Konfirmasi yang ingin dibatalkan: ");

        try {
            Reservation cancelled = reservationService.cancelReservation(number);
            System.out.println("\n[OK] Reservasi berhasil dibatalkan:");
            System.out.println(cancelled);
        } catch (ReservationNotFoundException e) {
            System.out.println("\n[!] " + e.getMessage());
        }
    }

    public void handleViewAllReservations() {
        System.out.println("\n[ Daftar Semua Pemesanan ]");
        List<Reservation> all = reservationService.getAllReservations();

        if (all.isEmpty()) {
            System.out.println("[!] Belum ada pemesanan.");
            return;
        }

        all.forEach(System.out::println); // toString() polimorfik per jenis reservasi
        System.out.println("Jumlah pemesanan : " + all.size());
        System.out.println("Total nilai      : " + CurrencyFormatter.rupiah(reservationService.getTotalValue()));
    }
}
