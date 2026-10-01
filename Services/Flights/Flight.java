package Services.Flights;

public class Flight {
    private int id;
    private String airline; // Nama maskapai penerbangan
    private String origin; // Kota asal penerbangan
    private String destination; // Kota tujuan penerbangan
    private String departureTime; // Waktu keberangkatan
    private String arrivalTime; // Waktu kedatangan
    private double price; // Harga tiket penerbangan
    private int availableSeats; // Jumlah kursi yang tersedia

    public Flight(int id, String airline, String origin, String destination, String departureTime, String arrivalTime, double price, int availableSeats) {
      this.id = id;
      this.airline = airline;
      this.origin = origin;
      this.destination = destination;
      this.departureTime = departureTime;
      this.arrivalTime = arrivalTime;
      this.price = price;
      this.availableSeats = availableSeats;
    }

    public int getId() {
      return this.id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getAirline() {
      return this.airline;
    }

    public void setAirline(String airline) {
      this.airline = airline;
    }

    public String getOrigin() {
      return this.origin;
    }

    public void setOrigin(String origin) {
      this.origin = origin;
    }

    public String getDestination() {
      return this.destination;
    }

    public void setDestination(String destination) {
      this.destination = destination;
    }

    public String getDepartureTime() {
      return this.departureTime;
    }

    public void setDepartureTime(String departureTime) {
      this.departureTime = departureTime;
    }

    public String getArrivalTime() {
      return this.arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
      this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
      return this.price;
    }

    public void setPrice(double price) {
      this.price = price;
    }

    public int getAvailableSeats() {
      return this.availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
      this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return String.format(
            """
            Kode Penerbangan  : %d
            Maskapai          : %s
            Asal              : %s
            Tujuan            : %s
            Keberangkatan     : %s
            Kedatangan        : %s
            Harga             : Rp%.2f
            Kursi Tersedia    : %d
            """,
            id,
            airline,
            origin,
            destination,
            departureTime,
            arrivalTime,
            price,
            availableSeats
        );
    }
}