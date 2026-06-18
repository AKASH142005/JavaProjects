package BusReservationSystem.model;

public class User {
    private int id ;
    private String username;
    private String password;
    private String fullName;
    private String Email;
    private long phoneNum ;
    private String role ;
    public User(){
    }
    public String getUsername(){
        return username ;
    }
    public String getPassword(){
        return password ;
    }
    public String getFullName(){
        return fullName ;
    }
    public String getRole(){
        return role ;
    }
    public int getId(){
        return id ;
    }
    public String getEmail(){
        return Email ;
    }
    public long getPhoneNum(){
        return phoneNum ;
    }
    public void setUsername(String username){
        this.username = username ;
    }
    public void setPassword(String password){
        this.password = password ;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public void setRole(String role){
        this.role =role ;
    }
    public void setEmail(String Email){
        this.Email =Email ;
    }
    public void setPhoneNum(long phoneNum){
        this.phoneNum= phoneNum ;
    }
}
