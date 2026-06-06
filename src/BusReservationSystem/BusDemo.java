package BusReservationSystem;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList ;
public class BusDemo {
    public static void main(String[] args) throws ParseException, SQLException {
        BusDAO busDAO = new BusDAO();
        busDAO.displayBusDetails();


        int userOption = 1;
        Scanner sc = new Scanner(System.in);
        while (userOption == 1) {
            System.out.println("Enter 1 to Book and 2 to Exit");
            userOption = sc.nextInt();
            if (userOption == 1) {
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

