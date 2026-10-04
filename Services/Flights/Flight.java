package Services.Flights;

import Services.Utils.CurrencyFormatter;
import java.time.LocalDate;

public class Flight {
    private int id;
    private String flightNumber;   // Nomor penerbangan, misal GA-401
    private String airline;        // Nama maskapai
    private String origin;         // Kota asal
    private String destination;    // Kota tujuan
    private LocalDate date;        // Tanggal penerbangan
    private String departureTime;  // Jam berangkat (HH:mm)
    private String arrivalTime;    // Jam tiba (HH:mm)
    private double price;          // Harga per penumpang
    private int availableSeats;    // Kursi tersedia

    public Flight(int id, String flightNumber, String airline, String origin, String destination,
                  LocalDate date, String departureTime, String arrivalTime,
                  double price, int availableSeats) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }

    public String getOrigin() { return origin; }
    public void setOrigin(String origin) { this.origin = origin; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    @Override
    public String toString() {
        return """
            ID Penerbangan    : %d
            No. Penerbangan   : %s
            Maskapai          : %s
            Rute              : %s -> %s
            Tanggal           : %s
            Jam               : %s - %s
            Harga/penumpang   : %s
            Kursi Tersedia    : %d
            """.formatted(
                id, flightNumber, airline, origin, destination,
                date, departureTime, arrivalTime,
                CurrencyFormatter.rupiah(price), availableSeats);
    }
}
