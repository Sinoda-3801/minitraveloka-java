package Services.Hotels;

import Services.Customer.Customer;
import Services.Reservation.HotelReservation;
import Services.Reservation.ReservationService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/** Logika bisnis hotel: inventori, pencarian, dan pemesanan. */
public class HotelService {
    private final List<Hotel> hotels = new ArrayList<>();
    private final ReservationService reservationService;

    public HotelService(ReservationService reservationService) {
        this.reservationService = reservationService;
        initSampleData();
    }

    private void initSampleData() {
        hotels.add(new Hotel(1, "Grand Indonesia Hotel", "Jakarta", 1800000, 20, 2));
        hotels.add(new Hotel(2, "Cikini Budget Inn", "Jakarta", 450000, 30, 2));
        hotels.add(new Hotel(3, "Kuta Beach Resort", "Bali", 1500000, 15, 3));
        hotels.add(new Hotel(4, "Ubud Garden Villa", "Bali", 950000, 2, 2));
        hotels.add(new Hotel(5, "Malioboro Heritage", "Yogyakarta", 600000, 25, 2));
        hotels.add(new Hotel(6, "Tunjungan Plaza Hotel", "Surabaya", 750000, 18, 2));
        hotels.add(new Hotel(7, "Dago Highland", "Bandung", 700000, 12, 4));
        hotels.add(new Hotel(8, "Medan City Suites", "Medan", 550000, 22, 2));
    }

    public List<Hotel> getAllHotels() {
        return hotels;
    }

    public Hotel getHotelById(int id) {
        return hotels.stream()
                .filter(hotel -> hotel.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Hotel dengan ID " + id + " tidak ditemukan."));
    }

    /** Mencari hotel pada kota tertentu yang kamarnya cukup untuk jumlah tamu; termurah dahulu. */
    public List<Hotel> searchHotels(String city, LocalDate checkIn, LocalDate checkOut, int guests) {
        validateStay(checkIn, checkOut, guests);
        return hotels.stream()
                .filter(h -> h.getCity().equalsIgnoreCase(city.trim()))
                .filter(h -> h.getAvailableRooms() >= h.roomsNeeded(guests))
                .sorted(Comparator.comparingDouble(Hotel::getPricePerNight))
                .toList();
    }

    public HotelReservation bookHotel(int hotelId, Customer customer,
                                      LocalDate checkIn, LocalDate checkOut, int guests) {
        validateStay(checkIn, checkOut, guests);

        Hotel hotel = getHotelById(hotelId);
        int rooms = hotel.roomsNeeded(guests);
        if (hotel.getAvailableRooms() < rooms) {
            throw new IllegalStateException("Kamar tidak cukup. Tersisa " + hotel.getAvailableRooms()
                    + " kamar, dibutuhkan " + rooms + ".");
        }

        hotel.setAvailableRooms(hotel.getAvailableRooms() - rooms);

        HotelReservation reservation = new HotelReservation(
                reservationService.nextConfirmationNumber(), customer, hotel, checkIn, checkOut, guests, rooms);
        reservationService.addReservation(reservation);
        return reservation;
    }

    private void validateStay(LocalDate checkIn, LocalDate checkOut, int guests) {
        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Tanggal check-out harus setelah check-in.");
        }
        if (guests <= 0) {
            throw new IllegalArgumentException("Jumlah tamu harus lebih dari 0.");
        }
    }
}
