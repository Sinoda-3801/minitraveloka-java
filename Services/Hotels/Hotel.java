package Services.Hotels;

import Services.Utils.CurrencyFormatter;

public class Hotel {
    private int id;
    private String name;            // Nama hotel
    private String city;            // Lokasi (kota)
    private double pricePerNight;   // Harga per kamar per malam
    private int availableRooms;     // Kamar tersedia
    private int maxGuestsPerRoom;   // Kapasitas tamu per kamar

    public Hotel(int id, String name, String city, double pricePerNight,
                 int availableRooms, int maxGuestsPerRoom) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.pricePerNight = pricePerNight;
        this.availableRooms = availableRooms;
        this.maxGuestsPerRoom = maxGuestsPerRoom;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) { this.pricePerNight = pricePerNight; }

    public int getAvailableRooms() { return availableRooms; }
    public void setAvailableRooms(int availableRooms) { this.availableRooms = availableRooms; }

    public int getMaxGuestsPerRoom() { return maxGuestsPerRoom; }
    public void setMaxGuestsPerRoom(int maxGuestsPerRoom) { this.maxGuestsPerRoom = maxGuestsPerRoom; }

    /** Jumlah kamar yang dibutuhkan untuk sejumlah tamu (pembulatan ke atas). */
    public int roomsNeeded(int guests) {
        return (guests + maxGuestsPerRoom - 1) / maxGuestsPerRoom;
    }

    @Override
    public String toString() {
        return """
            ID Hotel          : %d
            Nama Hotel        : %s
            Lokasi            : %s
            Harga/malam/kamar : %s
            Kamar Tersedia    : %d
            Kapasitas/kamar   : %d tamu
            """.formatted(id, name, city, CurrencyFormatter.rupiah(pricePerNight),
                availableRooms, maxGuestsPerRoom);
    }
}
