package BusReservationSystem.ui;

import BusReservationSystem.dao.BookingDAO;
import BusReservationSystem.model.Booking;
import BusReservationSystem.model.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class CustomerMenu {

    static Scanner input = new Scanner(System.in);
    public void showcustomerMenu(User user) throws SQLException, ParseException {
        System.out.print("Login SuccessFull Welcome , " + user.getUsername() + " .");
        int bookingOption = 1;
        while (bookingOption == 1) {
            System.out.println("Enter 1 to Book and 2 to Exit");
            bookingOption = input.nextInt();
            if (bookingOption == 1) {
                Booking booking = new Booking();
                if (booking.isAvailable()) {
                    BookingDAO bookingdao = new BookingDAO();
                    bookingdao.addBooking(booking);
                    System.out.println("Your booking is confirmed");
                } else {
                    System.out.println("Sorry. Buses is Full. Try another Bus or Date");
                }

            }
        }
    }
}
