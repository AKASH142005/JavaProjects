package BusReservationSystem;
public class Bus {
    private int busNo;
    private boolean ac ;
    private int capacity ;
    Bus(int no , boolean ac , int capacity){
        this.busNo = no ;
        this.ac = ac ;
        this.capacity = capacity ;
    }

    public int getCapacity(){
        return this.capacity ;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity ;
    }
    public void setAc(boolean ac){
        this.ac = ac ;
    }
    public int getBusNo(){
        return this.busNo ;
    }
    public void display(){
        System.out.println("Bus No: "+ busNo +" AC : " + ac + " Total Capcity :" +capacity);
    }
}
