package Services.Reservation;

import Services.Customer.Customer;
import java.util.ArrayList;

public interface ReservationInterface {
    public Reservation reservation(Integer bookId, Customer customer);
    public Reservation cancelReservation(String reservationId);
    public ArrayList<Reservation> getAllReservations();
}