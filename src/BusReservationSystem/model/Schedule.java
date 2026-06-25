package BusReservationSystem.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private int id ;
    private final int busId ;
    private final int routeId ;
    private final LocalDate travelDate ;
    private final LocalTime departureTime ;
    private final BigDecimal fare ;


    public Schedule(int id ,int busId , int routeId ,LocalDate travelDate , LocalTime departureTime , BigDecimal fare){
        this.id = id ;
        this.busId = busId ;
        this.routeId = routeId ;
        this.travelDate = travelDate ;
        this.departureTime = departureTime ;
        this.fare = fare ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusId() {
        return busId;
    }


    public BigDecimal getFare() {
        return fare;
    }

    public int getRouteId() {
        return routeId;
    }


    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

}
