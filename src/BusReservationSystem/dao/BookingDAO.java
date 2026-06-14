package BusReservationSystem.dao;

import BusReservationSystem.model.Booking;
import BusReservationSystem.DBConnection;

import java.util.Date;
import java.sql.*;


public class BookingDAO  {
    public static int getBookedCount(int id ,Date date) throws SQLException {
        Connection con = DBConnection.getConnection();
        String query = "SELECT count(passenger_name) from booking where bus_no =? and travel_date = ?";
        PreparedStatement pst = con.prepareStatement(query);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        pst.setInt(1,id);
        pst.setDate(2,sqlDate);

        ResultSet rs = pst.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    public static void addBooking(Booking booking) throws SQLException{
        Connection con = DBConnection.getConnection();
        String query = "Insert into Booking values(?,?,?)";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, booking.getPassengerName());
        pst.setInt(2,booking.getBusNo());
        // sql and java data problem
        java.sql.Date sqlDate = new java.sql.Date(booking.getDate().getTime());
        pst.setDate(3 ,sqlDate);
        pst.executeUpdate();
    }
}
