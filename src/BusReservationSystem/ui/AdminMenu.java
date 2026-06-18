package BusReservationSystem.ui;

import BusReservationSystem.dao.BusDAO;
import BusReservationSystem.model.Bus;
import BusReservationSystem.model.User;
import BusReservationSystem.service.BusService;

import java.lang.classfile.instruction.SwitchCase;
import java.util.Scanner;

public class AdminMenu {
    static Scanner input = new Scanner(System.in);
    BusDAO busDAO = new BusDAO();
    BusService busService = new BusService(busDAO);

    public void showAdminMenu(User user) throws Exception {
        AdminMenu admin= new AdminMenu();
        BusService busService = new BusService();


        while (true){
            System.out.print("Welcome to the Admin Page \n Enter \n 1. Bus Details \n 2. Add/Remove Bus 3. Exit");
            int userOption = input.nextInt();
            switch (userOption){
                case 1 :
                    busService.ListAllBus();
                case 2 :
                    System.out.print("Enter Your Option \n 1. Add  \n 2. Remove ");
                    int Option = input.nextInt();
                    switch (Option){
                        case 1 :
                            admin.addBus();
                            break;
                        case 2 :
                            admin.remove();
                            break;
                        default:
                            System.out.print("Valid Option");
                    }
                    break;
                case 3:
                    break ;
                default:
                    System.out.println("Enter Valid Input");
            }


        }
    }

    private void remove() {
    }

    public void addBus(){

        System.out.print("Enter the BusNo :");
        int busNo  = input.nextInt() ;
        System.out.print("Enter the busName :");
        String busName  = input.next() ;
        System.out.print("Enter the busType :");
        int busType  = input.nextInt() ;
        System.out.print("Enter the totalSeats :");
        int totalSeats  = input.nextInt() ;
        // Add Bus Flow
        try {
            Bus newBus = new Bus();
            newBus.setBusNo(12);
            newBus.setBusName("Akkkkk");
            newBus.setBusType("AC Sleeper");
            newBus.setTotalSeat(3);
            boolean success = false;
            try {
                success = busService.addBus(newBus);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            if(success) System.out.println("Bus added successfully!");
        } catch (Exception e) {
            System.out.println("Failed to add bus: " + e.getMessage()); // Prints your business rule validation errors
        }
    }
    // Inside your Admin Menu loop structure
}
