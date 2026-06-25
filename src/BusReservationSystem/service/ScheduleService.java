package BusReservationSystem.service;
import BusReservationSystem.dao.RouteDAO;
import BusReservationSystem.dao.ScheduleDAO;
import BusReservationSystem.dao.BusDAO;
import BusReservationSystem.model.Schedule;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ScheduleService {

        private final ScheduleDAO scheduleDAO = new ScheduleDAO();
        private final RouteDAO routeDAO = new RouteDAO();
        private final BusDAO busDAO = new BusDAO();

        public boolean addSchedule(Schedule newSchedule) throws Exception {
            if (newSchedule == null) {
                throw new Exception("Schedule details cannot be blank");
            }
            if (newSchedule.getBusId() <= 0 || busDAO.getBusById(newSchedule.getBusId()) == null) {
                throw new Exception("Valid bus id is required");
            }
            if (!routeDAO.isRouteActive(newSchedule.getRouteId())) {
                throw new Exception("Valid route id is required");
            }
            if (newSchedule.getTravelDate() == null || newSchedule.getTravelDate().isBefore(LocalDate.now())) {
                throw new Exception("Travel date cannot be blank or in the past");
            }
            if (newSchedule.getDepartureTime() == null) {
                throw new Exception("Departure time cannot be blank");
            }

            if (newSchedule.getFare() == null || newSchedule.getFare().compareTo(BigDecimal.ZERO) <= 0) {
                throw new Exception("Fare must be greater than 0");
            }

            return scheduleDAO.insertSchedule(newSchedule);
        }

        public List<Schedule> getAllSchedules() throws SQLException {
            return scheduleDAO.getAllSchedules();
        }
}

