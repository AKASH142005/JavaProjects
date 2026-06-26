package BusReservationSystem.service;
import BusReservationSystem.dao.UserDAO;
import BusReservationSystem.model.User;

public class UserService {
    private final UserDAO userDAO = new UserDAO() ;
    public boolean register_User(User newUser) {
        // handle error

        if(userDAO.getUserByUsername(newUser.getUsername())!= null) {
            System.out.print("username already exists !");
            return false;
        }
       return userDAO.registerUserAdding(newUser);
    }

    public User login(String username , String password){
        User user = userDAO.getUserByUsername(username);

        if(user != null && user.getPassword().equals(password) && "ACTIVE".equalsIgnoreCase(user.getStatus())){
            return user ;
        }
        return null ;
    }
}
