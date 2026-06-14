package BusReservationSystem.dao;

import java.sql.*;

public class DBConnection  {
    private final static  String url ="jdbc:mysql://localhost:3306/BusReservation";
    private final static String userName ="root";
    private final static String passWord ="ac14042005";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,userName,passWord);
    }
}
