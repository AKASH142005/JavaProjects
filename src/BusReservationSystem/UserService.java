package BusReservationSystem;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserService {
    public boolean insertUser(User newUser) throws SQLException {
        // handle error
        UserDAO userDAO = new UserDAO();
        try{
            userDAO.insert(newUser);
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Error : The username '"+newUser.username +"' Already Exists");
        }
       return true;
    }
}
