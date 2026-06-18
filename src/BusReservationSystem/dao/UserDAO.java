package BusReservationSystem.dao;

import BusReservationSystem.model.User;

import java.sql.*;

public class UserDAO {

    public boolean registerUserAdding(User newUser) {
        String query ="Insert into users(username,password_hash,full_name,role,email,phone) VALUES (?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, newUser.getUsername());
            pst.setString(2, newUser.getPassword());
            pst.setString(3, newUser.getFullName());
            pst.setString(4, newUser.getRole());
            pst.setString(5, newUser.getEmail());
            pst.setLong(6, newUser.getPhoneNum());
            int rows = pst.executeUpdate();
            return rows > 0;
        } catch (SQLException e){
        System.out.print("DataBase Error : " + e.getMessage());
        return false ;
        }
    }

    public User getUserByUsername(String username){
        String query ="select*from users where username = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)){
              pst.setString(1 ,username);
              ResultSet rs = pst.executeQuery();
              if(rs.next()){
                  User user = new User();
                  user.setUsername(rs.getString("username"));
                  user.setPassword(rs.getString("password_hash"));
                  user.setFullName(rs.getString("full_name"));
                  user.setEmail(rs.getString("email"));
                  user.setRole(rs.getString("role"));
                  user.setPhoneNum(rs.getLong("phone"));
                  return user ;
              }
        } catch ( SQLException e){
            System.out.print("DataBase Error : " + e.getMessage());
        }
        return null ;
    }
}
