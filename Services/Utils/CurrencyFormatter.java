package Services.Utils;

import java.util.Locale;

/**
 * Kelas utilitas (final, hanya metode statis) untuk memformat harga ke Rupiah.
 * Dibuat final agar tidak dapat diwarisi.
 */
public final class CurrencyFormatter {
    private static final Locale INDONESIA = Locale.forLanguageTag("id-ID");

    private CurrencyFormatter() {
        // Mencegah instansiasi
    }

    public static String rupiah(double amount) {
        return String.format(INDONESIA, "Rp%,.0f", amount);
    }
}
