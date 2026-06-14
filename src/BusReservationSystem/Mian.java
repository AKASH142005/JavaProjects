package BusReservationSystem;
import BusReservationSystem.ui.MainMenu;

import java.sql.SQLException;
import java.text.ParseException;

public class Mian {
   public static void main() throws SQLException, ParseException {
        MainMenu menu = new MainMenu();
        menu.start() ;
    }
}
