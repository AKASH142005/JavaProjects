package BusReservationSystem;

public class User {
    String username = "";
    String password = "";
    int age ;
    char gender ;
    User(String username ,String password,int age,char gender){
        this.username =username;
        this.password = password;
        this.age =age;
        this.gender =gender;
    }
}
