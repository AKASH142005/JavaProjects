package BusReservationSystem.ui;

import BusReservationSystem.dao.BusDAO;
import BusReservationSystem.model.Bus;
import BusReservationSystem.model.User;
import BusReservationSystem.service.BusService;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    static Scanner input = new Scanner(System.in);
    BusDAO busDAO = new BusDAO();
    BusService busService = new BusService(busDAO);
    public void showAdminMenu(User user) throws Exception {
        System.out.println("Welcome to the Admin Page ");
        while (true){
            System.out.print(" Enter \n 1. Bus Details \n 2. Add/Remove Bus \n 3. Exit \n");
            int userOption = input.nextInt();
            switch (userOption){
                case 1 :
                    allBusList(busService.ListAllBus());
                    break;
                case 2 :
                    System.out.print("Enter Your Option \n 1. Add  \n 2. Remove ");
                    int Option = input.nextInt();
                    switch (Option){
                        case 1 :
                            addBus();
                            break;
                        case 2 :
                            remove();
                            break;
                        default:
                            System.out.print("Valid Option");
                    }
                    break;
                case 3:
                    return;
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
            newBus.setBusNo(busNo);
            newBus.setBusName(busName);
            newBus.setBusType(String.valueOf(busType));
            newBus.setTotalSeat(totalSeats);
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
    public static void allBusList(List <Bus> buses){
        for(Bus bus: buses){
            System.out.println("Bus no :" + bus.getBusNo()+ " Bus Name : "+ bus.getBusName() +" Bus Type : " +bus.getBusType() + " Total Seat : " + bus.getTotalSeat());
        }
    }
}
