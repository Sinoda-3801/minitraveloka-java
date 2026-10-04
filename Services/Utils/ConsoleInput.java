package Services.Utils;

import Services.Customer.Customer;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Pembaca input konsol yang aman: setiap metode terus meminta ulang
 * sampai pengguna memasukkan data yang valid (penanganan eksepsi + validasi).
 */
public final class ConsoleInput {
    private final Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                return line;
            }
            System.out.println("[!] Input tidak boleh kosong. Silakan coba lagi.");
        }
    }

    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Input harus berupa angka bulat! Silakan coba lagi.");
            }
        }
    }

    public int readPositiveInt(String prompt) {
        while (true) {
            int value = readInt(prompt);
            if (value > 0) {
                return value;
            }
            System.out.println("[!] Angka harus lebih besar dari 0.");
        }
    }

    /** Membaca tanggal format yyyy-MM-dd yang tidak boleh sebelum {@code min}. */
    public LocalDate readDate(String prompt, LocalDate min) {
        while (true) {
            System.out.print(prompt);
            try {
                LocalDate date = LocalDate.parse(scanner.nextLine().trim());
                if (date.isBefore(min)) {
                    System.out.println("[!] Tanggal tidak boleh sebelum " + min + ".");
                    continue;
                }
                return date;
            } catch (DateTimeParseException e) {
                System.out.println("[!] Format tanggal salah. Gunakan yyyy-MM-dd (contoh: 2026-10-20).");
            }
        }
    }

    /** Membaca data pelanggan (nama, nomor identitas, kontak) dengan validasi. */
    public Customer readCustomer() {
        String name = readNonEmpty("Masukkan Nama Anda: ");

        String identityNumber;
        while (true) {
            identityNumber = readNonEmpty("Masukkan Nomor Identitas Anda: ");
            if (identityNumber.length() >= 13) {
                break;
            }
            System.out.println("[!] Nomor identitas harus memiliki minimal 13 karakter.");
        }

        String contact;
        while (true) {
            contact = readNonEmpty("Masukkan Nomor Kontak (HP): ");
            if (contact.matches("\\+?\\d{8,15}")) {
                break;
            }
            System.out.println("[!] Nomor kontak harus 8-15 digit angka.");
        }

        return new Customer(name, identityNumber, contact);
    }
}
