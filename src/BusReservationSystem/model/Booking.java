package BusReservationSystem.model;
import BusReservationSystem.dao.BookingDAO;
import BusReservationSystem.dao.BusDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat ;
public class Booking {
    private String passengerName ;
    private int busNo ;
    private Date date ;

    public Booking() throws ParseException {

    }
    public String getPassengerName (){
        return passengerName ;
    }
    public int getBusNo(){
        return busNo ;
    }
    public Date getDate(){
        return date ;
    }

}
