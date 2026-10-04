package Services.Reservation;

import Services.Customer.Customer;
import Services.Hotels.Hotel;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class HotelReservation extends Reservation {
    private final Hotel hotel;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final int guests;
    private final int rooms;

    public HotelReservation(String confirmationNumber, Customer customer, Hotel hotel,
                            LocalDate checkIn, LocalDate checkOut, int guests, int rooms) {
        super(confirmationNumber, customer);
        this.hotel = hotel;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.guests = guests;
        this.rooms = rooms;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public int getGuests() {
        return guests;
    }

    public int getRooms() {
        return rooms;
    }

    public long getNights() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    @Override
    public String getType() {
        return "HOTEL";
    }

    @Override
    public double getTotalPrice() {
        return hotel.getPricePerNight() * getNights() * rooms;
    }

    @Override
    protected String getDetails() {
        return """
            Hotel            : %s (%s)
            Check-in         : %s
            Check-out        : %s (%d malam)
            Jumlah Tamu      : %d (%d kamar)
            """.formatted(
                hotel.getName(), hotel.getCity(),
                checkIn, checkOut, getNights(),
                guests, rooms);
    }
}
