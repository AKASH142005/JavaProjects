package BusReservationSystem;
import java.sql.*;

public class BusDAO {
    public static void displayBusDetails() throws SQLException {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        String query = "Select * from Bus";
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            System.out.print("ID : "+ rs.getInt(1));
            if(rs.getBoolean(2)){
                System.out.print(" AC : Yes ");
            } else {
                System.out.print(" AC  : No");
            }
            System.out.println(" Capacity : " + rs.getInt(3));
        }

    }
    public static int getCapacity(int id) throws SQLException {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        String query = "Select capacity from Bus where id = " +id;
        ResultSet rs = st.executeQuery(query);
        rs.next();
        return rs.getInt(1);
    }
}
