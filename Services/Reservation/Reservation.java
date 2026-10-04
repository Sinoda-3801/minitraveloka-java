package Services.Reservation;

import Services.Customer.Customer;
import Services.Utils.CurrencyFormatter;

/**
 * Kelas dasar reservasi. Dibuat sealed + abstract: hanya FlightReservation
 * dan HotelReservation yang boleh mewarisinya.
 * (Pada unnamed module, subclass sealed harus berada di package yang sama.)
 */
public abstract sealed class Reservation permits FlightReservation, HotelReservation {
    private final String confirmationNumber;
    private Customer customer;

    protected Reservation(String confirmationNumber, Customer customer) {
        this.confirmationNumber = confirmationNumber;
        this.customer = customer;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /** Jenis reservasi, misalnya "PENERBANGAN" atau "HOTEL". */
    public abstract String getType();

    /** Total harga seluruh reservasi. */
    public abstract double getTotalPrice();

    /** Detail khusus tiap jenis reservasi (di-override subclass -> polimorfisme). */
    protected abstract String getDetails();

    @Override
    public String toString() {
        return """
            ---------- RESERVASI %s ----------
            No. Konfirmasi   : %s
            Nama Pelanggan   : %s
            Nomor Identitas  : %s
            Kontak           : %s
            %sTotal Harga      : %s
            """.formatted(
                getType(),
                confirmationNumber,
                customer.getName(),
                customer.getIdentityNumber(),
                customer.getContact(),
                getDetails(),
                CurrencyFormatter.rupiah(getTotalPrice()));
    }
}
