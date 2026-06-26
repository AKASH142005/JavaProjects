package BusReservationSystem.ui;

import BusReservationSystem.model.User;
import BusReservationSystem.service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    private final UserService userService = new UserService();
    private final Scanner input = new Scanner(System.in);

    public void start() throws Exception {
        while (true) {
            System.out.println("\nWelcome to Bus Booking");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");

            int userOption = readInt();
            switch(userOption){
                case 1:
                    handleRegistration();
                    break;
                case 2:
                    handleLogin();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Enter a valid option");
            }
        }
    }

    public void handleRegistration() throws SQLException {
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        System.out.print("Enter full name: ");
        String fullName = input.nextLine();
        System.out.print("Enter role ADMIN/CUSTOMER: ");
        String role = input.nextLine().toUpperCase();
        System.out.print("Enter email: ");
        String email = input.nextLine();
        System.out.print("Enter phone number: ");
        long phoneNum = Long.parseLong(input.nextLine());

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFullName(fullName);
        newUser.setRole(role);
        newUser.setEmail(email);
        newUser.setPhoneNum(phoneNum);

        if(userService.register_User(newUser)){
            System.out.println("User added successfully");
        } else {
            System.out.println("User already exists or could not be added");
        }
    }

    private void handleLogin() throws Exception {
        System.out.print("Enter username: ");
        String username = input.nextLine().trim();
        System.out.print("Enter password: ");
        String password = input.nextLine().trim();

        User user = userService.login(username, password);
        if(user != null){
            System.out.println("Login successful. Welcome, " + user.getFullName());
            redirectUserMenu(user);
        } else {
            System.out.println("Invalid username, password, or inactive account");
        }
    }

    private void redirectUserMenu(User user) throws Exception {
        if("ADMIN".equalsIgnoreCase(user.getRole())){
            new AdminMenu(input).showAdminMenu(user);
        } else {
            new CustomerMenu(input).showcustomerMenu(user);
        }
    }

    private int readInt() {
        int value = input.nextInt();
        input.nextLine();
        return value;
    }
}
