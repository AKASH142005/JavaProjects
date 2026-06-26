package BusReservationSystem.ui;

import BusReservationSystem.model.Booking;
import BusReservationSystem.model.Schedule;
import BusReservationSystem.model.User;
import BusReservationSystem.service.BookingService;
import BusReservationSystem.service.ScheduleService;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private final Scanner input;
    private final BookingService bookingService = new BookingService();
    private final ScheduleService scheduleService = new ScheduleService();

    public CustomerMenu(Scanner input) {
        this.input = input;
    }

    public void showcustomerMenu(User user) {
        while (true) {
            System.out.println("\nCustomer Menu");
            System.out.println("1. View schedules");
            System.out.println("2. Check available seats");
            System.out.println("3. Book ticket");
            System.out.println("4. My bookings");
            System.out.println("5. Cancel booking");
            System.out.println("6. Logout");
            System.out.print("Enter option: ");

            int option = readInt();
            try {
                switch (option) {
                    case 1:
                        showSchedules();
                        break;
                    case 2:
                        checkAvailableSeats();
                        break;
                    case 3:
                        bookTicket(user);
                        break;
                    case 4:
                        showMyBookings(user);
                        break;
                    case 5:
                        cancelBooking();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Enter a valid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showSchedules() throws Exception {
        for (Schedule schedule : scheduleService.getAllSchedules()) {
            System.out.println("Schedule Id: " + schedule.getId() +
                    ", Bus Id: " + schedule.getBusId() +
                    ", Route Id: " + schedule.getRouteId() +
                    ", Date: " + schedule.getTravelDate() +
                    ", Time: " + schedule.getDepartureTime() +
                    ", Fare: " + schedule.getFare());
        }
    }

    private void checkAvailableSeats() throws Exception {
        System.out.print("Enter schedule id: ");
        int scheduleId = readInt();
        System.out.println("Available seats: " + bookingService.getAvailableSeats(scheduleId));
    }

    private void bookTicket(User user) throws Exception {
        System.out.print("Enter schedule id: ");
        int scheduleId = readInt();
        System.out.print("Enter seat number: ");
        int seatNo = readInt();
        System.out.print("Enter passenger name: ");
        String passengerName = input.nextLine();

        Booking booking = new Booking(user.getId(), scheduleId, passengerName, seatNo);
        if (bookingService.bookTicket(booking)) {
            System.out.println("Booking successful. PNR: " + booking.getPnr());
        }
    }

    private void showMyBookings(User user) throws Exception {
        List<Booking> bookings = bookingService.getBookingsByUserId(user.getId());
        for (Booking booking : bookings) {
            System.out.println("PNR: " + booking.getPnr() +
                    ", Schedule Id: " + booking.getScheduleId() +
                    ", Seat: " + booking.getSeatNo() +
                    ", Passenger: " + booking.getPassengerName() +
                    ", Status: " + booking.getBookingStatus() +
                    ", Amount: " + booking.getTotalAmount());
        }
    }

    private void cancelBooking() throws Exception {
        System.out.print("Enter PNR: ");
        String pnr = input.nextLine();
        if (bookingService.cancelBooking(pnr)) {
            System.out.println("Booking cancelled");
        } else {
            System.out.println("PNR not found");
        }
    }

    private int readInt() {
        int value = input.nextInt();
        input.nextLine();
        return value;
    }
}
