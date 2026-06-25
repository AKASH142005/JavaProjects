package BusReservationSystem.dao;

import BusReservationSystem.model.Schedule;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO {

    public List<Schedule> getAllSchedules() throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String query = "SELECT id, bus_id, route_id, tavel_date, department_time, fare FROM schedules";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                schedules.add(mapRowToSchedule(rs));
            }
        }
        return schedules;
    }

    public boolean insertSchedule(Schedule schedule) throws SQLException {
        String query = "INSERT INTO schedules (bus_id, route_id, tavel_date, department_time, fare) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, schedule.getBusId());
            pst.setInt(2, schedule.getRouteId());
            pst.setDate(3, Date.valueOf(schedule.getTravelDate()));
            pst.setTime(4, Time.valueOf(schedule.getDepartureTime()));
            pst.setBigDecimal(5, schedule.getFare());
            return pst.executeUpdate() > 0;
        }
    }

    private Schedule mapRowToSchedule(ResultSet rs) throws SQLException {
        return new Schedule(
                rs.getInt("id"),
                rs.getInt("bus_id"),
                rs.getInt("route_id"),
                rs.getDate("tavel_date").toLocalDate(),
                rs.getTime("department_time").toLocalTime(),
                rs.getBigDecimal("fare")
        );
    }
}
