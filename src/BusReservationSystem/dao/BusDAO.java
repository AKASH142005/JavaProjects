package BusReservationSystem.dao;

import BusReservationSystem.model.Bus;
import BusReservationSystem.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDAO {
    public boolean insertBus(Bus bus) throws SQLException {
        String query = "INSERT INTO buses (bus_number, bus_name, bus_type, total_seats) VALUES ( ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1,bus.getBusNo());
            pst.setString(2,bus.getBusName());
            pst.setString(3,bus.getBusType());
            pst.setInt(4,bus.getTotalSeat());
            return pst.executeUpdate() > 0;
        }
    }

    public Bus getBusById(int id ) throws SQLException {
        String query ="select * from buses where id = ?";
        try(
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);){
        pst.setInt(1,id);
           try (ResultSet rs = pst.executeQuery();){
               while(rs.next()){
                   return mapRowToBus(rs);
               }
           }
        }
    return  null ;
    }

    public Bus getBusByBusNum(int busNum ) throws SQLException {
        String query ="select * from buses where bus_number = ?";
        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement pst = con.prepareStatement(query);){
            pst.setInt(1,busNum);
            try (ResultSet rs = pst.executeQuery();){
                while(rs.next()){
                    return mapRowToBus(rs);
                }
            }
        }
        return  null ;
    }


    public List<Bus> displayAllBusDetails() throws SQLException {
        List <Bus> buses = new ArrayList<>() ;
        String query = "Select * from buses";
        try (
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();){
           try  (ResultSet rs = st.executeQuery(query);){
               while(rs.next()){
                   buses.add(mapRowToBus(rs));
               }
           }
        }
        return buses ;
    }

    public boolean deactivateBus(int busNo) throws SQLException {
        String query = "UPDATE buses SET status = 'INACTIVE' WHERE bus_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, busNo);
            return pst.executeUpdate() > 0;
        }
    }

    public Bus mapRowToBus(ResultSet rs) throws SQLException {
        Bus bus = new Bus();
        bus.setId(rs.getInt("id"));
        bus.setBusNo(rs.getInt("bus_number"));
        bus.setBusName(rs.getString("bus_name"));
        bus.setBusType(rs.getString("bus_type"));
        bus.setStatus(Status.valueOf(rs.getString("status")));
        bus.setTotalSeat(rs.getInt("total_seats"));
        return bus ;
    }
}
