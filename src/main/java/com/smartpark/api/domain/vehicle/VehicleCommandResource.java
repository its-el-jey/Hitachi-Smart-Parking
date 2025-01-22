package com.smartpark.api.domain.vehicle;

import com.smartpark.api.config.RestMessage;
import com.smartpark.api.domain.parkinglot.ParkingLot;
import com.smartpark.api.domain.parkinglot.ParkingLotDto;
import com.smartpark.api.domain.parkinglot.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Loida Jane Anfone <anfoneloidajane@gmail.com>
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleCommandResource {


    @Autowired
    VehicleRepository vehicleRepository;

    @PostMapping("/register")
    public ResponseEntity<RestMessage> registerVehicle(@RequestBody VehicleDto data) {

        Vehicle newVehicle = new Vehicle();
        newVehicle.setLicensePlate(data.getLicensePlate());
        newVehicle.setType(data.getType());
        newVehicle.setOwnerName(data.getOwnerName());

        Vehicle savedNewVehicle = vehicleRepository.save(newVehicle);

        RestMessage restMessage = new RestMessage("New Vehicle register successfully", newVehicle.markAsNew(), HttpStatus.OK);

        return ResponseEntity.status(HttpStatus.CREATED).body(restMessage);
    }

}
