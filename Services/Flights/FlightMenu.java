package Services.Flights;

import Services.Customer.Customer;
import Services.Reservation.FlightReservation;
import Services.Utils.ConsoleInput;
import java.time.LocalDate;
import java.util.List;

/** Tampilan (view) menu penerbangan. */
public class FlightMenu {
    private final FlightService flightService;
    private final ConsoleInput input;

    public FlightMenu(FlightService flightService, ConsoleInput input) {
        this.flightService = flightService;
        this.input = input;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Menu Penerbangan ---");
            System.out.println("11. Cari Penerbangan");
            System.out.println("12. Pesan Penerbangan (langsung dengan ID)");
            System.out.println("13. Lihat Semua Penerbangan");
            System.out.println("0.  Kembali ke Menu Utama");

            int choice = input.readInt("Pilih opsi: ");
            switch (choice) {
                case 11 -> handleSearchFlight();
                case 12 -> handleBookFlight();
                case 13 -> handleViewAllFlights();
                case 0 -> { return; }
                default -> System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private void handleSearchFlight() {
        System.out.println("\n[ Pencarian Penerbangan ]");
        String origin = input.readNonEmpty("Masukkan kota asal: ");
        String destination = input.readNonEmpty("Masukkan kota tujuan: ");
        LocalDate date = input.readDate("Masukkan tanggal perjalanan (yyyy-MM-dd): ", LocalDate.now());
        int passengers = input.readPositiveInt("Masukkan jumlah penumpang: ");

        List<Flight> results = flightService.searchFlights(origin, destination, date, passengers);
        if (results.isEmpty()) {
            System.out.println("\n[!] Tidak ada penerbangan tersedia.");
            return;
        }

        System.out.println("\n[ Hasil Pencarian Penerbangan ]");
        results.forEach(System.out::println);

        // Pengguna memilih salah satu penerbangan dari daftar hasil
        while (true) {
            int id = input.readInt("Masukkan ID penerbangan untuk dipesan (0 = kembali): ");
            if (id == 0) {
                return;
            }
            if (results.stream().anyMatch(f -> f.getId() == id)) {
                processBooking(id, passengers);
                return;
            }
            System.out.println("[!] ID " + id + " tidak ada dalam hasil pencarian.");
        }
    }

    private void handleBookFlight() {
        System.out.println("\n[ Pemesanan Penerbangan ]");
        int id = input.readInt("Masukkan ID penerbangan yang ingin dipesan: ");
        try {
            System.out.println(flightService.getFlightById(id));
        } catch (IllegalArgumentException e) {
            System.out.println("\n[!] " + e.getMessage());
            return;
        }
        int passengers = input.readPositiveInt("Masukkan jumlah penumpang: ");
        processBooking(id, passengers);
    }

    private void processBooking(int flightId, int passengers) {
        Customer customer = input.readCustomer();
        try {
            FlightReservation reservation = flightService.bookFlight(flightId, customer, passengers);
            System.out.println("\n[OK] Pemesanan penerbangan berhasil!");
            System.out.println(reservation);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("\n[!] Pemesanan gagal: " + e.getMessage());
        }
    }

    private void handleViewAllFlights() {
        System.out.println("\n[ Daftar Semua Penerbangan ]");
        flightService.getAllFlights().forEach(System.out::println);
    }
}
