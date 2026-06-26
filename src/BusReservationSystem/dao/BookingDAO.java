package BusReservationSystem.dao;

import BusReservationSystem.model.Booking;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    public boolean userExists(int userId) throws SQLException {
        String query = "SELECT id FROM users WHERE id = ? AND status = 'ACTIVE'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, userId);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean scheduleExists(int scheduleId) throws SQLException {
        String query = "SELECT id FROM schedules WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, scheduleId);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }

    public BigDecimal getScheduleFare(int scheduleId) throws SQLException {
        String query = "SELECT fare FROM schedules WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, scheduleId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal("fare");
                }
            }
        }
        return null;
    }

    public int getTotalSeatsByScheduleId(int scheduleId) throws SQLException {
        String query = "SELECT b.total_seats " +
                "FROM schedules s " +
                "JOIN buses b ON s.bus_id = b.id " +
                "WHERE s.id = ? AND b.status = 'ACTIVE'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, scheduleId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("total_seats");
                }
            }
        }
        return 0;
    }

    public int getBookedSeatCount(int scheduleId) throws SQLException {
        String query = "SELECT COUNT(*) " +
                "FROM booking_seats bs " +
                "JOIN booking b ON bs.booking_id = b.id " +
                "WHERE bs.schedule_id = ? AND b.booking_status = 'CONFIRMED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, scheduleId);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public boolean isSeatAvailable(int scheduleId, int seatNo) throws SQLException {
        String query = "SELECT bs.id " +
                "FROM booking_seats bs " +
                "JOIN booking b ON bs.booking_id = b.id " +
                "WHERE bs.schedule_id = ? AND bs.seat_number = ? AND b.booking_status = 'CONFIRMED'";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, scheduleId);
            pst.setInt(2, seatNo);

            try (ResultSet rs = pst.executeQuery()) {
                return !rs.next();
            }
        }
    }

    public boolean addBooking(Booking booking) throws SQLException {
        String bookingQuery = "INSERT INTO booking (pnr, user_id, schedule_id, booking_status, total_amoout) " +
                "VALUES (?, ?, ?, ?, ?)";
        String seatQuery = "INSERT INTO booking_seats (booking_id, schedule_id, seat_number, passenger_name) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement bookingPst = con.prepareStatement(bookingQuery, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement seatPst = con.prepareStatement(seatQuery)) {
                bookingPst.setString(1, booking.getPnr());
                bookingPst.setInt(2, booking.getUserId());
                bookingPst.setInt(3, booking.getScheduleId());
                bookingPst.setString(4, booking.getBookingStatus());
                bookingPst.setBigDecimal(5, booking.getTotalAmount());
                bookingPst.executeUpdate();

                try (ResultSet keys = bookingPst.getGeneratedKeys()) {
                    if (!keys.next()) {
                        con.rollback();
                        return false;
                    }
                    booking.setId(keys.getInt(1));
                }

                seatPst.setInt(1, booking.getId());
                seatPst.setInt(2, booking.getScheduleId());
                seatPst.setInt(3, booking.getSeatNo());
                seatPst.setString(4, booking.getPassengerName());
                seatPst.executeUpdate();

                con.commit();
                return true;
            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }

    public List<Booking> getBookingsByUserId(int userId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT b.id, b.pnr, b.user_id, b.schedule_id, b.booking_status, b.total_amoout, " +
                "bs.seat_number, bs.passenger_name " +
                "FROM booking b " +
                "JOIN booking_seats bs ON b.id = bs.booking_id " +
                "WHERE b.user_id = ? " +
                "ORDER BY b.id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, userId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    bookings.add(mapRowToBooking(rs));
                }
            }
        }
        return bookings;
    }


    public boolean cancelBooking(String pnr) throws SQLException {
        String query = "UPDATE booking SET booking_status = 'CANCELLED' WHERE pnr = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, pnr);
            return pst.executeUpdate() > 0;
        }
    }

    private Booking mapRowToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getInt("id"));
        booking.setPnr(rs.getString("pnr"));
        booking.setUserId(rs.getInt("user_id"));
        booking.setScheduleId(rs.getInt("schedule_id"));
        booking.setBookingStatus(rs.getString("booking_status"));
        booking.setTotalAmount(rs.getBigDecimal("total_amoout"));
        booking.setSeatNo(rs.getInt("seat_number"));
        booking.setPassengerName(rs.getString("passenger_name"));
        return booking;
    }
}
