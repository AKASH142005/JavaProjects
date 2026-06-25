package BusReservationSystem.model;

public class Route {
    private int id ;
    private String sourceCity ;
    private String destinationCity;
    private int distance ;

    public Route(){
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public int getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public String getSourceCity() {
        return sourceCity;
    }
}
