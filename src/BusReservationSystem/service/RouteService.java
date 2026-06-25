package BusReservationSystem.service;

import BusReservationSystem.dao.RouteDAO;
import BusReservationSystem.model.Route;

import java.sql.SQLException;
import java.util.List;

public class RouteService {
    private final RouteDAO routeDAO = new RouteDAO();

    public boolean addRoute(Route route) throws Exception {
        if (route.getSourceCity() == null || route.getSourceCity().trim().isEmpty() ||
                route.getDestinationCity() == null || route.getDestinationCity().trim().isEmpty()) {
            throw new Exception("Source and destination cities cannot be blank");
        }
        if (route.getSourceCity().equalsIgnoreCase(route.getDestinationCity())) {
            throw new Exception("Source and destination cities cannot be same");
        }
        if (route.getDistance() <= 0) {
            throw new Exception("Distance must be greater than 0");
        }
        return routeDAO.insertRoute(route);
    }

    public List<Route> getAllRoutes() throws SQLException {
        return routeDAO.getAllRoutes();
    }
}
