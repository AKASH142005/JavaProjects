package BusReservationSystem.ui;
import BusReservationSystem.dao.UserDAO;
import BusReservationSystem.model.User;
import BusReservationSystem.service.UserService;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;


public class MainMenu {
    private final UserService userService = new UserService();
    private final Scanner input = new Scanner(System.in) ;
    public void start() throws Exception {
        while (true) {
            System.out.println("Welcome to Bus Booking \n 1. Sign Up \n 2. Login In \n 3.Exist");
            Scanner input = new Scanner(System.in);
            int userOption = input.nextInt();
            switch(userOption){
                case 1:
                    handleRegisteration();
                    break;

                case 2:
                    handleLogin();
                    break;
            }

        }
    }
    public void handleRegisteration() throws SQLException {

        System.out.print("Enter Username : ");
        String username = input.next();
        System.out.print("Enter Password : ");
        String password = input.next();
        System.out.print("Enter Full Name : ");
        String fullName = input.next();
        System.out.print("Enter Role : Customer / admin ");
        String role = input.next();
        System.out.print("Enter Email : ");
        String Email = input.next();
        System.out.print("Enter Phone number ");
        long phoneNum = input.nextLong();

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setFullName(fullName);
        newUser.setRole(role);
        newUser.setEmail(Email);
        newUser.setPhoneNum(phoneNum);

        UserService service = new UserService();
        if(userService.register_User(newUser)){
            System.out.println("User Added SuccessFully");
        } else {
            System.out.println("user Already exist");
        }
    }
    private void handleLogin() throws Exception {
        UserDAO userDAO = new UserDAO();
        System.out.print("Enter username : ");
        String username = input.nextLine().trim();
        System.out.print("Enter password : ");
        String password = input.nextLine().trim();
        User user = userService.login(username, password);

        if(user != null){
            System.out.println("\n✅ Login successful! Welcome back, " + user.getFullName() + ".");
            redirectUserMenu(user);
        }
        else {
            System.out.println("❌ Invalid username or password!");
        }
    }

    private void redirectUserMenu(User user) throws Exception {
        AdminMenu admin = new AdminMenu();
        CustomerMenu customer = new CustomerMenu() ;
        if("ADMIN".equalsIgnoreCase(user.getRole())){
            admin.showAdminMenu(user);
        } else {
            customer.showcustomerMenu(user);
        }
    }

}


