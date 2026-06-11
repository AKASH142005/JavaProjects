package BusReservationSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    public boolean authenticateUser(String username ,String password) throws SQLException {
        String query = "select username from users Where username = ? and password  = ?";
        Connection con =DBConnection.getConnection();
        PreparedStatement pst  = con.prepareStatement(query) ;
        pst.setString(1,username);
        pst.setString(2,password);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }
    public boolean insert(User newUser) throws SQLException {
        String query ="Insert into users(username,password,age,gender) VALUES (?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1,newUser.username);
        pst.setString(2,newUser.password);
        pst.setInt(3,newUser.age);
        pst.setString(4,String.valueOf(newUser.gender));
        int rows = pst.executeUpdate();
        return rows == 1;
    }
}
