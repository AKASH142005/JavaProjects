package BusReservationSystem.model;

import java.math.BigDecimal;

public class Booking {
    private int id;
    private String pnr;
    private int userId;
    private int scheduleId;
    private String passengerName ;
    private int seatNo;
    private String bookingStatus;
    private BigDecimal totalAmount;

    public Booking() {
    }

    public Booking(int userId, int scheduleId, String passengerName, int seatNo) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.passengerName = passengerName;
        this.seatNo = seatNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPassengerName (){
        return passengerName ;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", pnr='" + pnr + '\'' +
                ", userId=" + userId +
                ", scheduleId=" + scheduleId +
                ", passengerName='" + passengerName + '\'' +
                ", seatNo=" + seatNo +
                ", bookingStatus='" + bookingStatus + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
