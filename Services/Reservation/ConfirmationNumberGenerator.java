package Services.Reservation;

import java.util.Random;
import java.util.function.Predicate;

/**
 * Kelas pembantu (final) untuk membuat nomor konfirmasi acak 6 digit.
 * Menerima lambda {@code isUsed} agar nomor yang dihasilkan selalu unik.
 */
public final class ConfirmationNumberGenerator {
    private static final Random RANDOM = new Random();

    private ConfirmationNumberGenerator() {
        // Mencegah instansiasi
    }

    public static String generate(Predicate<String> isUsed) {
        String number;
        do {
            number = String.valueOf(100000 + RANDOM.nextInt(900000)); // 100000 - 999999
        } while (isUsed.test(number));
        return number;
    }
}
