package BusReservationSystem.dao;

import BusReservationSystem.model.Route;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {
    public boolean insertRoute(Route route) throws SQLException {
        String query = "INSERT INTO routes (source_city, destination_city, distance_km) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, route.getSourceCity());
            pst.setString(2, route.getDestinationCity());
            pst.setInt(3, route.getDistance());
            return pst.executeUpdate() > 0;
        }
    }

    public boolean isRouteActive(int routeId) throws SQLException {
        String query = "SELECT id FROM routes WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, routeId);

            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }

    public List<Route> getAllRoutes() throws SQLException {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT id, source_city, destination_city, distance_km FROM routes";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                routes.add(mapRowToRoute(rs));
            }
        }
        return routes;
    }

    private Route mapRowToRoute(ResultSet rs) throws SQLException {
        Route route = new Route();
        route.setId(rs.getInt("id"));
        route.setSourceCity(rs.getString("source_city"));
        route.setDestinationCity(rs.getString("destination_city"));
        route.setDistance(rs.getInt("distance_km"));
        return route;
    }
}
