package BusReservationSystem.model;
public class Bus {
    private int id ;
    private int busNo;
    private String busName ;
    private String busType ;
    private int totalSeat ;
    private Status status ;
    public Bus(int busNo, String busName, String busType, int totalSeat) {
        this.busNo = busNo;
        this.busName = busName;
        this.busType = busType;
        this.totalSeat = totalSeat;
    }
    public Bus() {

    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getTotalSeat(){
        return this.totalSeat ;
    }
    public void setTotalSeat(int totalSeat){
        this.totalSeat = totalSeat ;
    }

    public int getBusNo(){
        return this.busNo ;
    }
    public void setBusNo(int busNo) {
        this.busNo = busNo;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType){
        this.busType = busType ;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

