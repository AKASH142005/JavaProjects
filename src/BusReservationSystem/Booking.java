package BusReservationSystem;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat ;
public class Booking {
    String passengerName ;
    int busNo ;
    Date date ;

    Booking() throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of passenger : ");
        passengerName = sc.next() ;
        System.out.print("Enter Bus no : ");
        busNo = sc.nextInt() ;
        System.out.print("Enter date dd-mm-yyyy : ");
        String dateInput= sc.next();
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dataFormat.parse(dateInput);
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
    public boolean isAvailable() throws SQLException {
        int capacity = 0;
            BusDAO busdao = new BusDAO();
           capacity =  busdao.getCapacity(busNo);
        int booked = 0;

        BookingDAO bookingdao = new BookingDAO();
        booked = bookingdao.getBookedCount(busNo , date);
        return booked < capacity ;
    }
}
