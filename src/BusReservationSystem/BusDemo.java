package BusReservationSystem;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList ;
public class BusDemo {
    public static void main(String[] args) throws ParseException, SQLException {
        BusDAO busDAO = new BusDAO();
        busDAO.displayBusDetails();
        String username ;
        String password ;
        int userOption = 1;
        while (userOption == 1 || userOption == 2) {
            System.out.println("Welcome to Bus Booking \n 1. Sign Up \n 2. Login In \n 3.Exist");
            Scanner input = new Scanner(System.in);
            userOption = input.nextInt();
            switch(userOption){
                case 1:
                    System.out.print("Enter username : ");
                    username = input.next();
                    System.out.print("Enter password : ");
                    password = input.next();
                    System.out.print("Enter age : ");
                    int age = input.nextInt();
                    System.out.print("Enter Gender Male -> M Female -> F: ");
                    char gender = input.next().charAt(0);
                    User newUser = new User(username,password,age,gender);
                    UserService service = new UserService();
                    if(service.insertUser(newUser)){
                        System.out.println("User Added SuccessFully");
                    } else {
                        System.out.println("user Already exist");
                    }
                    break;

                case 2:
                    UserDAO user = new UserDAO();
                    System.out.print("Enter username : ");
                    username = input.next();
                    System.out.print("Enter password : ");
                    password = input.next();
                    if (user.authenticateUser(username, password)) {
                        System.out.print("Login SuccessFull Welcome , " + username + " .");
                        int bookingOption = 1;
                        while (bookingOption == 1) {
                            System.out.println("Enter 1 to Book and 2 to Exit");
                            bookingOption = input.nextInt();
                            if (bookingOption == 1) {
                                Booking booking = new Booking();
                                if (booking.isAvailable()) {
                                    BookingDAO bookingdao = new BookingDAO();
                                    bookingdao.addBooking(booking);
                                    System.out.println("Your booking is confirmed");
                                } else {
                                    System.out.println("Sorry. Buses is Full. Try another Bus or Date");
                                }

                            }
                        }

                    }
                    else {
                        System.out.print("Invalid username or password");
                    }
                    break;
            }

        }
    }
}


