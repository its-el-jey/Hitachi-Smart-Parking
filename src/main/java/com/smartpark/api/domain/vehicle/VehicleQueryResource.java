package com.smartpark.api.domain.vehicle;

import com.smartpark.api.domain.parkinglot.ParkingLot;
import com.smartpark.api.domain.parkinglot.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleQueryResource {

    @Autowired
    VehicleRepository vehicleRepository;


    @GetMapping("/all")
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }


}
