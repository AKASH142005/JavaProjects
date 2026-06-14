package BusReservationSystem.service;
import BusReservationSystem.dao.UserDAO;
import BusReservationSystem.model.User;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserService {
    private UserDAO userDAO = new UserDAO() ;
    public boolean register_User(User newUser) throws SQLException {
        // handle error

        if(userDAO.getUserByUsername(newUser.getUsername())!= null) {
            System.out.print("username already exists !");
            return false;
        }
       return userDAO.registerUserAdding(newUser);
    }

    public User login(String username , String password){
        User user = userDAO.getUserByUsername(username);

        if(user != null && user.getPassword().equals(password)){
            return user ;
        }
        return null ;
    }
}
