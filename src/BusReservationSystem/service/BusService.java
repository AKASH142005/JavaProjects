package BusReservationSystem.service;

import BusReservationSystem.dao.BusDAO;
import BusReservationSystem.model.Bus;

import java.util.List;

public class BusService {
    private BusDAO busDAO ;

    public BusService(BusDAO busDAO) {
        this.busDAO = busDAO;
    }
    public BusService(){
        this.busDAO = new BusDAO();
    }

    public boolean addBus(Bus bus) throws Exception {
        if(bus.getBusNo() == 0 || bus.getBusName() == null || bus.getBusName().trim().isEmpty()){
            throw new Exception("Bus details cannot be Blank");
        }
        if(bus.getTotalSeat() <= 0){
            throw new Exception("Bus total seat greater then 0");
        }

        Bus existingBus = busDAO.getBusByBusNum(bus.getBusNo());

        if(existingBus != null){
            throw new Exception("Bus with number "+bus.getBusNo()+ "is already exist");
        }
    return busDAO.insertBus(bus);
    }
    public List<Bus> ListAllBus() throws Exception{

        return busDAO.displayAllBusDetails() ;
    }


}
