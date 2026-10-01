import java.util.Scanner;
import Services.Flights.FlightService;
import Services.Flights.FlightMenu;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Inisialisasi Services
    private static final FlightService flightService = new FlightService();

    // Inisialisasi Menus (Views)
    private static final FlightMenu flightMenu = new FlightMenu(flightService, scanner);

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   SELAMAT DATANG DI MINITRAVELOKA   ");
        System.out.println("==================================================");

        while (true) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    flightMenu.displayMenu();
                    break;
                case 99:
                    System.out.println("\nTerima kasih telah menggunakan sistem ini. Sampai jumpa!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n========== MENU UTAMA ==========");
        System.out.println("1. Pesan Ticket Pesawat");
        System.out.println("2. Pesan Hotel (Coming Soon)");
        System.out.println("99. Keluar");
        System.out.println("================================");
    }

    private static int getUserChoice() {
        System.out.print("Pilih opsi menu: ");
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Konsumsi newline
                return choice;
            } catch (java.util.InputMismatchException e) {
                System.out.println("[!] Input harus berupa angka bulat! Silakan coba lagi.");
                scanner.nextLine();
                System.out.print("Pilih opsi menu: ");
            }
        }
    }
}