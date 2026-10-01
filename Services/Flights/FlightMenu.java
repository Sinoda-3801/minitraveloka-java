package Services.Flights;

import Services.Customer.Customer;
import Services.Reservation.Reservation;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class FlightMenu {
    private final FlightService FlightService;
    private final Scanner scanner;

    public FlightMenu(FlightService flightService, Scanner scanner) {
        this.FlightService = flightService;
        this.scanner = scanner;
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n--- Menu Penerbangan ---");
            System.out.println("11. Cari Penerbangan");
            System.out.println("12. Pesan Penerbangan");
            System.out.println("13. Batalkan Pemesanan Penerbangan");
            System.out.println("14. Lihat Semua Pemesanan Penerbangan");
            System.out.println("0.  Kembali ke Menu Utama");

            int choice = getUserChoice();

            if (choice == 0) {
                break; // Kembali ke Main Menu
            }

            switch (choice) {
                case 11:
                    handleSearchFlight();
                    break;
                case 12:
                    handleBookFlight();
                    break;
                case 13:
                    handleCancelReservation();
                    break;
                case 14:
                    handleViewAllReservations();
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private void handleBookFlight() {
        while (true) {
            try {

                System.out.println("\n[ Pemesanan Penerbangan ]");

                System.out.print("Masukkan Nama Anda: ");
                String name = scanner.nextLine();
                if (name.trim().isEmpty()) {
                    System.out.println("[!] Nama tidak boleh kosong. Silakan coba lagi.");
                    continue;
                }

                System.out.print("Masukkan Nomor Identitas Anda: ");
                String identityNumber = scanner.nextLine();
                if (identityNumber.trim().isEmpty()) {
                  throw new IllegalArgumentException("[!] Nomor identitas tidak boleh kosong.");
                }
                if (identityNumber.length() < 13) {
                    throw new IllegalArgumentException("[!] Nomor identitas harus memiliki minimal 13 karakter.");
                }

                System.out.print("Masukkan ID Penerbangan yang ingin dipesan: ");
                int bookId = getIntInput();
                Flight selectedFlight = FlightService.getFlightById(bookId);
                if (selectedFlight == null) {
                    throw new IllegalArgumentException("[!] Penerbangan dengan ID " + bookId + " tidak ditemukan.");
                }

                Customer customer = new Customer(name, identityNumber);
                Reservation bookedFlight = FlightService.reservation(bookId, customer);

                System.out.println("\n[!] Pemesanan berhasil!");
                System.out.println(bookedFlight);

                break;

            } catch (IllegalArgumentException | IllegalStateException e) {

                System.out.println("\n[!] Terjadi kesalahan: " + e.getMessage());

                System.out.println("Silakan masukkan data kembali.");
            }
        }
    }

    private void handleSearchFlight() {
        ArrayList<Flight> flights = FlightService.getAllFlights();
        if (flights.isEmpty()) {
            System.out.println("\n[!] Tidak ada penerbangan yang tersedia.");
            return;
        }

        System.out.println("\n[ Daftar Semua Penerbangan ]");
        flights.forEach(flight -> {
            System.out.println(flight.toString());
        });

        System.out.print("\nMasukkan kata kunci untuk mencari penerbangan: ");
        System.out.print("Contoh: Nama Maskapai, Kota Asal, atau Kota Tujuan: ");

        String searchQuery = scanner.nextLine();
        if (searchQuery.trim().isEmpty()) {
            System.out.println("[!] Kata kunci pencarian tidak boleh kosong.");
            return;
        }

        ArrayList<Flight> foundFlight = FlightService.searchFlight(searchQuery);

        if (foundFlight.isEmpty()) {
            System.out.println("\n[!] Tidak ada penerbangan yang ditemukan dengan kata kunci: " + searchQuery);
            return;
        }

        System.out.println("\n[ Hasil Pencarian Penerbangan ]");
        foundFlight.forEach(flight -> {
            System.out.println(flight.toString());
        });
    }

    private void handleCancelReservation() {
        System.out.print("\nMasukkan ID Pemesanan yang ingin dibatalkan: ");
        String reservationId = scanner.nextLine();
        if (reservationId.trim().isEmpty()) {
            System.out.println("[!] ID Pemesanan tidak boleh kosong.");
            return;
        }

        try {
            Reservation canceledReservation = FlightService.cancelReservation(reservationId);
            System.out.println("\n[!] Pemesanan berhasil dibatalkan:");
            System.out.println(canceledReservation);
        } catch (IllegalArgumentException e) {
            System.out.println("\n[!] Terjadi kesalahan: " + e.getMessage());
        }
    }

    private void handleViewAllReservations() {
        System.out.println("\n[ Daftar Semua Pemesanan Penerbangan ]");
        ArrayList<Reservation> reservations = FlightService.getAllReservations();

        if (reservations.isEmpty()) {
            System.out.println("[!] Tidak ada pemesanan penerbangan.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }

    private int getUserChoice() {
        System.out.print("Pilih opsi: ");
        return getIntInput();
    }

    private int getIntInput() {
        while (true) {
            try {
                int val = scanner.nextInt();
                scanner.nextLine(); // Konsumsi newline
                return val;
            } catch (InputMismatchException e) {
                System.out.println("[!] Input harus berupa angka bulat! Silakan coba lagi.");
                scanner.nextLine(); // Bersihkan buffer yang error
                System.out.print("Input angka: ");
            }
        }
    }
}
