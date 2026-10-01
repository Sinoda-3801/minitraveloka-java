package Services.Reservation;

import Services.Customer.Customer;
import Services.Flights.Flight;

public class Reservation {
    private String bookingCode;
    private Customer customer;
    private Flight flight;

    public Reservation(String bookingCode, Customer customer, Flight flight) {
        this.bookingCode = bookingCode;
        this.customer = customer;
        this.flight = flight;
    }

    public String bookingCode() {
        return bookingCode;
    }

    public void setId(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

      @Override
      public String toString() {
          return String.format(
              """
              ID Reservasi     : %s
              Nama Pelanggan   : %s
              Nomor Identitas  : %s
              Maskapai         : %s
              Asal             : %s
              Tujuan           : %s
              """,
              bookingCode,
              this.customer.getName(),
              this.customer.getIdentityNumber(),
              this.flight.getAirline(),
              this.flight.getOrigin(),
              this.flight.getDestination()
          );
      }
}