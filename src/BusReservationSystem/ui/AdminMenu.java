package BusReservationSystem.ui;

import BusReservationSystem.model.Bus;
import BusReservationSystem.model.Route;
import BusReservationSystem.model.Schedule;
import BusReservationSystem.model.User;
import BusReservationSystem.service.BusService;
import BusReservationSystem.service.RouteService;
import BusReservationSystem.service.ScheduleService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private final Scanner input;
    private final BusService busService = new BusService();
    private final RouteService routeService = new RouteService();
    private final ScheduleService scheduleService = new ScheduleService();

    public AdminMenu(Scanner input) {
        this.input = input;
    }

    public void showAdminMenu(User user) {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. View buses");
            System.out.println("2. Add bus");
            System.out.println("3. Remove bus");
            System.out.println("4. View routes");
            System.out.println("5. Add route");
            System.out.println("6. View schedules");
            System.out.println("7. Add schedule");
            System.out.println("8. Logout");
            System.out.print("Enter option: ");

            int option = readInt();
            try {
                switch (option) {
                    case 1:
                        showBuses();
                        break;
                    case 2:
                        addBus();
                        break;
                    case 3:
                        removeBus();
                        break;
                    case 4:
                        showRoutes();
                        break;
                    case 5:
                        addRoute();
                        break;
                    case 6:
                        showSchedules();
                        break;
                    case 7:
                        addSchedule();
                        break;
                    case 8:
                        return;
                    default:
                        System.out.println("Enter a valid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addBus() throws Exception {
        System.out.print("Enter bus number: ");
        int busNo = readInt();
        System.out.print("Enter bus name: ");
        String busName = input.nextLine();
        System.out.print("Enter bus type: ");
        String busType = input.nextLine();
        System.out.print("Enter total seats: ");
        int totalSeats = readInt();

        Bus bus = new Bus(busNo, busName, busType, totalSeats);
        if (busService.addBus(bus)) {
            System.out.println("Bus added successfully");
        }
    }

    private void removeBus() throws Exception {
        System.out.print("Enter bus number to deactivate: ");
        int busNo = readInt();
        if (busService.removeBus(busNo)) {
            System.out.println("Bus removed successfully");
        }
    }

    private void showBuses() throws Exception {
        List<Bus> buses = busService.ListAllBus();
        for (Bus bus : buses) {
            System.out.println("Id: " + bus.getId() +
                    ", Bus No: " + bus.getBusNo() +
                    ", Name: " + bus.getBusName() +
                    ", Type: " + bus.getBusType() +
                    ", Seats: " + bus.getTotalSeat() +
                    ", Status: " + bus.getStatus());
        }
    }

    private void addRoute() throws Exception {
        System.out.print("Enter source city: ");
        String source = input.nextLine();
        System.out.print("Enter destination city: ");
        String destination = input.nextLine();
        System.out.print("Enter distance in km: ");
        int distance = readInt();

        Route route = new Route();
        route.setSourceCity(source);
        route.setDestinationCity(destination);
        route.setDistance(distance);

        if (routeService.addRoute(route)) {
            System.out.println("Route added successfully");
        }
    }

    private void showRoutes() throws Exception {
        for (Route route : routeService.getAllRoutes()) {
            System.out.println("Id: " + route.getId() +
                    ", From: " + route.getSourceCity() +
                    ", To: " + route.getDestinationCity() +
                    ", Distance: " + route.getDistance());
        }
    }

    private void addSchedule() throws Exception {
        System.out.print("Enter bus id: ");
        int busId = readInt();
        System.out.print("Enter route id: ");
        int routeId = readInt();
        System.out.print("Enter travel date yyyy-MM-dd: ");
        LocalDate travelDate = LocalDate.parse(input.nextLine());
        System.out.print("Enter departure time HH:mm: ");
        LocalTime departureTime = LocalTime.parse(input.nextLine());
        System.out.print("Enter fare: ");
        BigDecimal fare = new BigDecimal(input.nextLine());

        Schedule schedule = new Schedule(0, busId, routeId, travelDate, departureTime, fare);
        if (scheduleService.addSchedule(schedule)) {
            System.out.println("Schedule added successfully");
        }
    }

    private void showSchedules() throws Exception {
        for (Schedule schedule : scheduleService.getAllSchedules()) {
            System.out.println("Id: " + schedule.getId() +
                    ", Bus Id: " + schedule.getBusId() +
                    ", Route Id: " + schedule.getRouteId() +
                    ", Date: " + schedule.getTravelDate() +
                    ", Time: " + schedule.getDepartureTime() +
                    ", Fare: " + schedule.getFare());
        }
    }

    private int readInt() {
        int value = input.nextInt();
        input.nextLine();
        return value;
    }
}
